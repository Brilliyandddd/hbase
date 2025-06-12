package com.doyatama.university.model;

import java.time.Instant;

public class CausalityRating {
    private String idRating;
    private String causalityTaskId;
    private String reviewerId;
    private String influencingCriteriaId;
    private String influencedCriteriaId;
    private String ratingValue;
    private double numericRatingValue; // <-- PERUBAHAN UTAMA: Dari Double (objek) menjadi double (primitif)
    private Instant dateRated;

    public CausalityRating() {
        this.dateRated = Instant.now();
        this.numericRatingValue = 0.0; // Inisialisasi default untuk primitif double
    }

    public CausalityRating(String idRating, String causalityTaskId, String reviewerId,
                           String influencingCriteriaId, String influencedCriteriaId, String ratingValue, double numericRatingValue) {
        this.idRating = idRating;
        this.causalityTaskId = causalityTaskId;
        this.reviewerId = reviewerId;
        this.influencingCriteriaId = influencingCriteriaId;
        this.influencedCriteriaId = influencedCriteriaId;
        this.ratingValue = ratingValue;
        this.numericRatingValue = numericRatingValue;
        this.dateRated = Instant.now();
    }

    // --- Getters and Setters ---
    public String getIdRating() { return idRating; }
    public void setIdRating(String idRating) { this.idRating = idRating; }

    public String getCausalityTaskId() { return causalityTaskId; }
    public void setCausalityTaskId(String causalityTaskId) { this.causalityTaskId = causalityTaskId; }

    public String getReviewerId() { return reviewerId; }
    public void setReviewerId(String reviewerId) { this.reviewerId = reviewerId; }

    public String getInfluencingCriteriaId() { return influencingCriteriaId; }
    public void setInfluencingCriteriaId(String influencingCriteriaId) { this.influencingCriteriaId = influencingCriteriaId; }

    public String getInfluencedCriteriaId() { return influencedCriteriaId; }
    public void setInfluencedCriteriaId(String influencedCriteriaId) { this.influencedCriteriaId = influencedCriteriaId; }

    public String getRatingValue() { return ratingValue; }
    public void setRatingValue(String ratingValue) { this.ratingValue = ratingValue; }

    // Getter/Setter untuk nilai numerik rata-rata (sekarang double primitif)
    public double getNumericRatingValue() { return numericRatingValue; } // <-- Get double
    public void setNumericRatingValue(double numericRatingValue) { this.numericRatingValue = numericRatingValue; } // <-- Set double


    public Instant getDateRated() { return dateRated; }
    public void setDateRated(Instant dateRated) { this.dateRated = dateRated; }

    @Override
    public String toString() {
        return "CausalityRating{" +
               "idRating='" + idRating + '\'' +
               ", causalityTaskId='" + causalityTaskId + '\'' +
               ", reviewerId='" + reviewerId + '\'' +
               ", influencingCriteriaId='" + influencingCriteriaId + '\'' +
               ", influencedCriteriaId='" + influencedCriteriaId + '\'' +
               ", ratingValue='" + ratingValue + '\'' +
               ", numericRatingValue=" + numericRatingValue + // Tampilkan numeric value
               ", dateRated=" + dateRated +
               '}';
    }
}