package com.doyatama.university.model;

import java.time.Instant; // Import for Instant type
import java.util.ArrayList; // Import for ArrayList
import java.util.Arrays;    // Import for Arrays.asList
import java.util.List;      // Import for List type

public class Causality {
    private String idCausality;
    private Instant dateCreated; // Type: Instant
    private String description;
    private String subject;
    private Integer semester;
    private String teamTeaching1;
    private String teamTeaching2;
    private String teamTeaching3;
    private String criteriaIdsRaw; // <-- PERUBAHAN UTAMA: Ganti List<String> menjadi String
    private String status;       // Type: String

    public Causality() {
        // Default constructor
    }

    // Constructor untuk inisialisasi semua field (termasuk criteriaIdsRaw)
    public Causality(String idCausality, Instant dateCreated, String description, String subject, Integer semester,
                     String teamTeaching1, String teamTeaching2, String teamTeaching3,
                     String criteriaIdsRaw, String status) {
        this.idCausality = idCausality;
        this.dateCreated = dateCreated;
        this.description = description;
        this.subject = subject;
        this.semester = semester;
        this.teamTeaching1 = teamTeaching1;
        this.teamTeaching2 = teamTeaching2;
        this.teamTeaching3 = teamTeaching3;
        this.criteriaIdsRaw = criteriaIdsRaw;
        this.status = status;
    }

    // --- Getters and Setters ---

    public String getIdCausality() {
        return idCausality;
    }

    public void setIdCausality(String idCausality) {
        this.idCausality = idCausality;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getTeamTeaching1() {
        return teamTeaching1;
    }

    public void setTeamTeaching1(String teamTeaching1) {
        this.teamTeaching1 = teamTeaching1;
    }

    public String getTeamTeaching2() {
        return teamTeaching2;
    }

    public void setTeamTeaching2(String teamTeaching2) {
        this.teamTeaching2 = teamTeaching2;
    }

    public String getTeamTeaching3() {
        return teamTeaching3;
    }

    public void setTeamTeaching3(String teamTeaching3) {
        this.teamTeaching3 = teamTeaching3;
    }

    /// GETTER INI MENGONVERSI RAW STRING DARI HBASE MENJADI LIST UNTUK KODE APLIKASI
    public List<String> getCriteriaIds() {
        if (this.criteriaIdsRaw == null || this.criteriaIdsRaw.isEmpty()) {
            return new ArrayList<>();
        }
        // Pastikan delimiter yang digunakan sama dengan saat menyimpan
        return Arrays.asList(this.criteriaIdsRaw.split(";"));
    }

    // SETTER INI MENGONVERSI LIST DARI SERVICE/CONTROLLER MENJADI RAW STRING UNTUK DISIMPAN KE HBASE
    public void setCriteriaIds(List<String> criteriaIds) {
        if (criteriaIds == null || criteriaIds.isEmpty()) {
            this.criteriaIdsRaw = null;
        } else {
            this.criteriaIdsRaw = String.join(";", criteriaIds);
        }
    }

    // GETTER/SETTER EKSPLISIT UNTUK FIELD RAW STRING, DIGUNAKAN OLEH HBASECUSTOMCLIENT
    public String getCriteriaIdsRaw() {
        return criteriaIdsRaw;
    }

    public void setCriteriaIdsRaw(String criteriaIdsRaw) {
        this.criteriaIdsRaw = criteriaIdsRaw;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Anda dapat menambahkan metode toString() jika diperlukan untuk debugging
    @Override
    public String toString() {
        return "Causality{" +
               "idCausality='" + idCausality + '\'' +
               ", dateCreated=" + dateCreated +
               ", description='" + description + '\'' +
               ", subject='" + subject + '\'' +
               ", semester=" + semester +
               ", teamTeaching1='" + teamTeaching1 + '\'' +
               ", teamTeaching2='" + teamTeaching2 + '\'' +
               ", teamTeaching3='" + teamTeaching3 + '\'' +
               ", criteriaIdsRaw='" + criteriaIdsRaw + '\'' + // Tampilkan string mentah
               ", status='" + status + '\'' +
               '}';
    }

    // Metode set(String fieldName, Object value) DIHAPUS.
    // Asumsikan data binding/mapping oleh framework menggunakan getter/setter spesifik.
}