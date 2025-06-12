package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient; // Pastikan package ini benar
import com.doyatama.university.model.DematelCriteriaWeight; // Model DematelCriteriaWeight Anda
import com.doyatama.university.model.Causality; // Mungkin diperlukan untuk mencari subjectId jika findBySubjectId perlu context Causality

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID; // Untuk generate ID/RowKey
import java.util.stream.Collectors; // Untuk Stream API

@Repository // Menandakan bahwa kelas ini adalah komponen Spring Repository
public class DematelCriteriaWeightRepository {

    private static final Logger logger = LoggerFactory.getLogger(DematelCriteriaWeightRepository.class);

    // Konfigurasi HBase. Diinisialisasi sama seperti di QuestionCriteriaRepository Anda.
    Configuration conf = HBaseConfiguration.create(); 
    
    String tableName = "dematel_weights"; // Nama tabel HBase untuk bobot DEMATEL

    // Constructor default
    public DematelCriteriaWeightRepository() {
        // Jika perlu inisialisasi tambahan untuk conf atau client, bisa di sini
    }

    /**
     * Menyimpan bobot DEMATEL baru atau memperbarui yang sudah ada.
     * Row Key akan dibentuk dari causalityId dan criterionId.
     *
     * @param weight Objek DematelCriteriaWeight yang akan disimpan.
     * @return Objek DematelCriteriaWeight yang sudah disimpan.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public DematelCriteriaWeight save(DematelCriteriaWeight weight) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf); // Membuat instance client

        String rowKey = weight.getCausalityId() + "_" + weight.getCriterionId(); // Row Key unik

        // Set ID model dari Row Key yang akan digunakan. Ini penting karena model tidak @GeneratedValue.
        weight.setId(rowKey); 

        TableName table = TableName.valueOf(tableName);

        // Insert data ke Column Family "main"
        // Pastikan nama qualifier (kolom) sesuai dengan yang Anda inginkan di HBase
        client.insertRecord(table, rowKey, "main", "id", rowKey); // Simpan ID model sebagai qualifier juga
        client.insertRecord(table, rowKey, "main", "causality_id", weight.getCausalityId());
        client.insertRecord(table, rowKey, "main", "subject_id", weight.getSubjectId());
        client.insertRecord(table, rowKey, "main", "criterion_id", weight.getCriterionId());
        client.insertRecord(table, rowKey, "main", "normalized_weight", String.valueOf(weight.getNormalizedWeight()));
        // Jika Anda menyimpan createdAt dan updatedAt di POJO DematelCriteriaWeight, tambahkan di sini:
        // client.insertRecord(table, rowKey, "main", "created_at", weight.getCreatedAt().toString());
        // client.insertRecord(table, rowKey, "main", "updated_at", LocalDateTime.now().toString());


        logger.info("DematelCriteriaWeightRepository - save: Saved weight for Causality {} and Criterion {}",
                weight.getCausalityId(), weight.getCriterionId());
        return weight; // Kembalikan objek yang disimpan
    }

    /**
     * Mengambil semua bobot DEMATEL berdasarkan ID tugas kausalitas.
     * Metode ini akan melakukan full scan dan filter di Java jika HBaseCustomClient tidak mendukung prefix scan.
     *
     * @param causalityId ID tugas kausalitas.
     * @return Daftar bobot kriteria untuk kausalitas tersebut.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public List<DematelCriteriaWeight> findByCausalityId(String causalityId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);

        // Map ini harus mencerminkan kolom-kolom yang ada di HBase
        // dan bagaimana Anda ingin memetakan ke field di model DematelCriteriaWeight.
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("id", "id"); 
        columnMapping.put("causalityId", "causality_id");
        columnMapping.put("subjectId", "subject_id");
        columnMapping.put("criterionId", "criterion_id");
        columnMapping.put("normalizedWeight", "normalized_weight");
        // Jika ada createdAt/updatedAt di model
        // columnMapping.put("createdAt", "created_at");
        // columnMapping.put("updatedAt", "updated_at");

        // --- PENTING: EFISIENSI ---
        // Seperti di CausalityRepository findByTeamTeaching, ini akan melakukan FULL SCAN
        // jika client.showListTable tidak mendukung prefix scan.
        // Jika ini inefisien, Anda perlu memperbarui HBaseCustomClient untuk mendukung `Scan.setRowPrefixFilter()`.
        List<DematelCriteriaWeight> allWeights = client.showListTable(table.toString(), columnMapping, DematelCriteriaWeight.class, Integer.MAX_VALUE);

        return allWeights.stream()
            .filter(w -> w.getCausalityId() != null && w.getCausalityId().equals(causalityId))
            .collect(Collectors.toList());
    }

    /**
     * Mengambil semua bobot DEMATEL berdasarkan ID mata kuliah.
     * Peringatan: Operasi ini sangat tidak efisien di HBase tanpa indeks sekunder.
     * Akan melakukan full table scan dan memfilter di aplikasi.
     *
     * @param subjectId ID mata kuliah.
     * @return Daftar bobot kriteria untuk mata kuliah tersebut.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public List<DematelCriteriaWeight> findBySubjectId(String subjectId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);
        
        // Map ini harus mencerminkan kolom-kolom yang ada di HBase
        // dan bagaimana Anda ingin memetakan ke field di model DematelCriteriaWeight.
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("id", "id"); 
        columnMapping.put("causalityId", "causality_id");
        columnMapping.put("subjectId", "subject_id");
        columnMapping.put("criterionId", "criterion_id");
        columnMapping.put("normalizedWeight", "normalized_weight");
        // Jika ada createdAt/updatedAt di model
        // columnMapping.put("createdAt", "created_at");
        // columnMapping.put("updatedAt", "updated_at");
        
        // Ini juga FULL SCAN.
        List<DematelCriteriaWeight> allWeights = client.showListTable(table.toString(), columnMapping, DematelCriteriaWeight.class, Integer.MAX_VALUE);

        return allWeights.stream()
                .filter(w -> w.getSubjectId() != null && w.getSubjectId().equals(subjectId))
                .collect(Collectors.toList());
    }

    /**
     * Menghapus semua bobot DEMATEL yang terkait dengan ID tugas kausalitas.
     * Ini dilakukan dengan mencari semua bobot yang terkait dan menghapusnya satu per satu berdasarkan Row Key.
     *
     * @param causalityId ID tugas kausalitas.
     * @return true jika operasi berhasil, false jika tidak ada yang dihapus atau error.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public boolean deleteByCausalityId(String causalityId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);

        // Cari semua bobot yang terkait dengan causalityId
        List<DematelCriteriaWeight> weightsToDelete = findByCausalityId(causalityId);
        
        if (!weightsToDelete.isEmpty()) {
            for (DematelCriteriaWeight record : weightsToDelete) {
                // Asumsi client.deleteRecord menghapus satu record berdasarkan RowKey (record.getId())
                client.deleteRecord(table.toString(), record.getId());
            }
            logger.info("DematelCriteriaWeightRepository - deleteByCausalityId: Deleted {} weights for causality {}",
                    weightsToDelete.size(), causalityId);
            return true;
        } else {
            logger.info("DematelCriteriaWeightRepository - deleteByCausalityId: No weights found to delete for causality {}", causalityId);
            return false; // Tidak ada yang dihapus
        }
    }
}