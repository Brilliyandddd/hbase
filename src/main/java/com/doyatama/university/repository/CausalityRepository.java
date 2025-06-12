package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient; // Pastikan package ini benar
import com.doyatama.university.model.Causality;
// import com.fasterxml.jackson.core.JsonProcessingException; // Tidak digunakan lagi untuk serialisasi criteriaIds
// import com.fasterxml.jackson.databind.ObjectMapper; // Tidak digunakan lagi untuk serialisasi criteriaIds
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.Instant; // Import Instant
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
// import java.util.Collections; // Tidak digunakan langsung
import java.util.stream.Collectors;

@Repository
public class CausalityRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "causality";

    private static final Logger logger = LoggerFactory.getLogger(CausalityRepository.class);
    // private final ObjectMapper objectMapper = new ObjectMapper(); // Hapus ini karena tidak lagi memproses JSON criteriaIds

    /**
     * Mengambil semua tugas kausalitas dari HBase.
     * @param size Batas jumlah hasil yang akan diambil.
     * @return Daftar objek Causality.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public List<Causality> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableCausalities = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idCausality", "idCausality");
        columnMapping.put("dateCreated", "dateCreated"); // Akan dipetakan ke Instant oleh HBaseCustomClient
        columnMapping.put("description", "description");
        columnMapping.put("subject", "subject");
        columnMapping.put("semester", "semester");
        columnMapping.put("teamTeaching1", "teamTeaching1");
        columnMapping.put("teamTeaching2", "teamTeaching2");
        columnMapping.put("teamTeaching3", "teamTeaching3");
        columnMapping.put("criteriaIds", "criteriaIdsRaw"); // PENTING: Map ke field 'criteriaIdsRaw' di model Causality
        columnMapping.put("status", "status");

        // HBaseCustomClient akan menangani konversi tipe data dari String HBase ke tipe Java yang benar (Instant, Integer, String)
        // dan juga List<String> yang dihasilkan dari String mentah oleh getter di model Causality.
        List<Causality> result = client.showListTable(tableCausalities.toString(), columnMapping, Causality.class, size);

        if (result != null) {
            // Logging untuk memverifikasi data yang diambil (termasuk ukuran criteriaIds setelah konversi)
            result.forEach(c -> logger.info("CausalityRepository - findAll: Retrieved ID {} with Semester = {}, Status = {}, Criteria Count = {}",
                    c.getIdCausality(), c.getSemester(), c.getStatus(), c.getCriteriaIds() != null ? c.getCriteriaIds().size() : 0));
        }
        return result;
    }

    /**
     * Menyimpan tugas kausalitas baru ke HBase.
     * @param causality Objek Causality yang akan disimpan.
     * @return Objek Causality yang sudah disimpan (dengan ID baru jika itu objek baru).
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public Causality save(Causality causality) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString();
        causality.setIdCausality(rowKey); // Set ID unik yang akan menjadi rowKey

        TableName tableCausality = TableName.valueOf(tableName);

        // Ambil string mentah dari model Causality (sudah dikonversi dari List<String> oleh setter)
        String criteriaIdsRawString = causality.getCriteriaIdsRaw(); // PENTING: Ambil raw string

        // Insert main data
        client.insertRecord(tableCausality, rowKey, "main", "idCausality", rowKey);
        // Simpan Instant sebagai string ISO 8601
        if (causality.getDateCreated() != null) {
            client.insertRecord(tableCausality, rowKey, "main", "dateCreated", causality.getDateCreated().toString());
        }
        client.insertRecord(tableCausality, rowKey, "main", "description", causality.getDescription());
        client.insertRecord(tableCausality, rowKey, "main", "subject", causality.getSubject());
        client.insertRecord(tableCausality, rowKey, "main", "semester", String.valueOf(causality.getSemester())); // Konversi Integer ke String
        client.insertRecord(tableCausality, rowKey, "main", "status", causality.getStatus());
        if (criteriaIdsRawString != null) {
            // Simpan string mentah ke kolom "criteriaIds" di HBase
            client.insertRecord(tableCausality, rowKey, "main", "criteriaIds", criteriaIdsRawString);
        } else {
            // Opsional: Jika criteriaIdsRawString null, Anda bisa menghapus kolomnya di HBase jika sudah ada
            // client.deleteColumn(tableCausality, rowKey, "main", "criteriaIds");
        }

        // Simpan team teaching members (dengan penanganan null)
        if (causality.getTeamTeaching1() != null) {
            client.insertRecord(tableCausality, rowKey, "main", "teamTeaching1", causality.getTeamTeaching1());
        }
        if (causality.getTeamTeaching2() != null) {
            client.insertRecord(tableCausality, rowKey, "main", "teamTeaching2", causality.getTeamTeaching2());
        }
        if (causality.getTeamTeaching3() != null) {
            client.insertRecord(tableCausality, rowKey, "main", "teamTeaching3", causality.getTeamTeaching3());
        }

        // Insert detail data (metadata creation timestamp)
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant(); // Gunakan Instant dari java.time

        client.insertRecord(tableCausality, rowKey, "detail", "created_by", "Doyatama");
        client.insertRecord(tableCausality, rowKey, "detail", "created_at", instant.toString());

        logger.info("CausalityRepository - save: Saved Causality with ID {} with Semester = {}, Status = {}",
                rowKey, causality.getSemester(), causality.getStatus());
        return causality;
    }

    /**
     * Mengambil tugas kausalitas berdasarkan ID.
     * @param causalityId ID tugas kausalitas.
     * @return Objek Causality, atau null jika tidak ditemukan.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public Causality findById(String causalityId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableCausalities = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idCausality", "idCausality");
        columnMapping.put("dateCreated", "dateCreated"); // Akan dipetakan ke Instant
        columnMapping.put("description", "description");
        columnMapping.put("subject", "subject");
        columnMapping.put("semester", "semester");
        columnMapping.put("teamTeaching1", "teamTeaching1");
        columnMapping.put("teamTeaching2", "teamTeaching2");
        columnMapping.put("teamTeaching3", "teamTeaching3");
        columnMapping.put("criteriaIds", "criteriaIdsRaw"); // PENTING: Map ke field 'criteriaIdsRaw'
        columnMapping.put("status", "status");

        // HBaseCustomClient akan menangani konversi tipe data dari String HBase ke tipe Java yang benar.
        Causality result = client.showDataTable(tableCausalities.toString(), columnMapping, causalityId, Causality.class);

        if (result != null) {
            logger.info("CausalityRepository - findById: Retrieved ID {} with Semester = {}, Status = {}, Criteria Count = {}",
                    result.getIdCausality(), result.getSemester(), result.getStatus(), result.getCriteriaIds() != null ? result.getCriteriaIds().size() : 0);
        } else {
            logger.warn("CausalityRepository - findById: Causality with ID {} not found.", causalityId);
        }
        return result;
    }

    /**
     * Memperbarui tugas kausalitas yang sudah ada di HBase.
     * @param causalityId ID tugas kausalitas yang akan diperbarui.
     * @param causality Objek Causality dengan data yang diperbarui.
     * @return Objek Causality yang sudah diperbarui.
     * @throws IOException Jika terjadi kesalahan I/O atau tugas tidak ditemukan.
     */
    public Causality update(String causalityId, Causality causality) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableCausality = TableName.valueOf(tableName);

        // Ambil data yang ada untuk mempertahankan field yang tidak diupdate (mis. dateCreated)
        // Note: findById() sudah diubah untuk mengembalikan Instant dan List<String> yang benar
        Causality existingCausality = findById(causalityId);
        if (existingCausality == null) {
            throw new IOException("Causality with ID " + causalityId + " not found for update.");
        }

        // Ambil string mentah dari model Causality yang diupdate
        String criteriaIdsRawString = causality.getCriteriaIdsRaw(); // PENTING: Ambil raw string

        // Update main data (tidak mengubah dateCreated)
        client.insertRecord(tableCausality, causalityId, "main", "description", causality.getDescription());
        client.insertRecord(tableCausality, causalityId, "main", "subject", causality.getSubject());
        client.insertRecord(tableCausality, causalityId, "main", "semester", String.valueOf(causality.getSemester()));
        client.insertRecord(tableCausality, causalityId, "main", "status", causality.getStatus());
        if (criteriaIdsRawString != null) {
            client.insertRecord(tableCausality, causalityId, "main", "criteriaIds", criteriaIdsRawString);
        } else {
            // Set kolom ke string kosong jika kriterianya menjadi null/kosong
            client.insertRecord(tableCausality, causalityId, "main", "criteriaIds", "");
        }

        // Update team teaching members (dengan penanganan null untuk set string kosong jika tidak ada)
        if (causality.getTeamTeaching1() != null) {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching1", causality.getTeamTeaching1());
        } else {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching1", "");
        }
        if (causality.getTeamTeaching2() != null) {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching2", causality.getTeamTeaching2());
        } else {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching2", "");
        }
        if (causality.getTeamTeaching3() != null) {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching3", causality.getTeamTeaching3());
        } else {
            client.insertRecord(tableCausality, causalityId, "main", "teamTeaching3", "");
        }

        // Update detail data (metadata modified timestamp)
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        client.insertRecord(tableCausality, causalityId, "detail", "modified_by", "Doyatama"); // Anda bisa menambahkan user yang memodifikasi
        client.insertRecord(tableCausality, causalityId, "detail", "modified_at", instant.toString());

        logger.info("CausalityRepository - update: Updated Causality with ID {} with Semester = {}, Status = {}",
                causalityId, causality.getSemester(), causality.getStatus());

        // Ambil objek Causality yang sudah diupdate dari DB untuk memastikan konsistensi
        return findById(causalityId);
    }

    /**
     * Menghapus tugas kausalitas berdasarkan ID.
     * @param causalityId ID tugas kausalitas yang akan dihapus.
     * @return true jika berhasil dihapus, false jika tidak.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public boolean deleteById(String causalityId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, causalityId);
        logger.info("CausalityRepository - deleteById: Deleted Causality with ID {}", causalityId);
        return true;
    }

    /**
     * Mencari tugas kausalitas yang ditugaskan kepada dosen tertentu.
     * Catatan: Operasi ini bisa sangat tidak efisien di HBase tanpa indeks sekunder.
     * @param teacherId ID dosen.
     * @return Daftar tugas kausalitas yang ditugaskan kepada dosen.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public List<Causality> findByTeamTeaching(String teacherId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableCausalities = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idCausality", "idCausality");
        columnMapping.put("dateCreated", "dateCreated");
        columnMapping.put("description", "description");
        columnMapping.put("subject", "subject");
        columnMapping.put("semester", "semester");
        columnMapping.put("teamTeaching1", "teamTeaching1");
        columnMapping.put("teamTeaching2", "teamTeaching2");
        columnMapping.put("teamTeaching3", "teamTeaching3");
        columnMapping.put("criteriaIds", "criteriaIdsRaw"); // PENTING: Map ke field 'criteriaIdsRaw'
        columnMapping.put("status", "status");

        // Mengambil semua data dan memfilter di memori (inefisien untuk dataset besar)
        List<Causality> allCausalities = client.showListTable(tableCausalities.toString(), columnMapping, Causality.class, Integer.MAX_VALUE);

        List<Causality> filteredCausalities = allCausalities.stream()
                .filter(c -> (c.getTeamTeaching1() != null && c.getTeamTeaching1().equals(teacherId)) ||
                             (c.getTeamTeaching2() != null && c.getTeamTeaching2().equals(teacherId)) ||
                             (c.getTeamTeaching3() != null && c.getTeamTeaching3().equals(teacherId)))
                .collect(Collectors.toList());

        logger.info("CausalityRepository - findByTeamTeaching: Retrieved {} tasks for teacher {}", filteredCausalities.size(), teacherId);
        return filteredCausalities;
    }
}