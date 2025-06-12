package com.doyatama.university.model; // Pastikan package ini sesuai dengan struktur proyek Anda


public class DematelCriteriaWeight {

    private String id; // ID unik untuk setiap baris bobot kriteria (UUID dari frontend atau digenerate di backend)
    private String causalityId;
    private String subjectId;
    private String criterionId;
    private Double normalizedWeight;

    // --- Constructors ---
    public DematelCriteriaWeight() {
        
    }

    // Constructor untuk memudahkan pembuatan objek baru dari service
    public DematelCriteriaWeight(String id, String causalityId, String subjectId, String criterionId, Double normalizedWeight) {
        this.id = id;
        this.causalityId = causalityId;
        this.subjectId = subjectId;
        this.criterionId = criterionId;
        this.normalizedWeight = normalizedWeight;
    }

    // --- Getters and Setters (Diperlukan oleh JPA dan untuk akses data) ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCausalityId() { return causalityId; }
    public void setCausalityId(String causalityId) { this.causalityId = causalityId; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public String getCriterionId() { return criterionId; }
    public void setCriterionId(String criterionId) { this.criterionId = criterionId; }

    public Double getNormalizedWeight() { return normalizedWeight; }
    public void setNormalizedWeight(Double normalizedWeight) { this.normalizedWeight = normalizedWeight; }


    
}