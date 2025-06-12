package com.doyatama.university.payload;

import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull; // Anda bisa menghapus ini atau biarkan, @NotBlank sudah menyiratkan not null
// import javax.validation.constraints.Min; // HAPUS INI
// import javax.validation.constraints.Max; // HAPUS INI

public class CausalityRatingRequest {

    @NotBlank(message = "Causality Task ID is required")
    private String causalityTaskId;

    @NotBlank(message = "Reviewer ID is required")
    private String reviewerId;

    @NotBlank(message = "Influencing Criteria ID is required")
    private String influencingCriteriaId;

    @NotBlank(message = "Influenced Criteria ID is required")
    private String influencedCriteriaId;

    // Cukup gunakan @NotBlank untuk string
    @NotBlank(message = "Rating value (Linguistic ID) is required") // <-- Cukup ini saja
    private String ratingValue;

    // Default constructor
    public CausalityRatingRequest() {
    }

    // Full constructor
    public CausalityRatingRequest(String causalityTaskId, String reviewerId, String influencingCriteriaId,
                                 String influencedCriteriaId, String ratingValue) {
        this.causalityTaskId = causalityTaskId;
        this.reviewerId = reviewerId;
        this.influencingCriteriaId = influencingCriteriaId;
        this.influencedCriteriaId = influencedCriteriaId;
        this.ratingValue = ratingValue;
    }

    // --- Getters and Setters ---
    public String getCausalityTaskId() {
        return causalityTaskId;
    }

    public void setCausalityTaskId(String causalityTaskId) {
        this.causalityTaskId = causalityTaskId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getInfluencingCriteriaId() {
        return influencingCriteriaId;
    }

    public void setInfluencingCriteriaId(String influencingCriteriaId) {
        this.influencingCriteriaId = influencingCriteriaId;
    }

    public String getInfluencedCriteriaId() {
        return influencedCriteriaId;
    }

    public void setInfluencedCriteriaId(String influencedCriteriaId) {
        this.influencedCriteriaId = influencedCriteriaId;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "CausalityRatingRequest{" +
                "causalityTaskId='" + causalityTaskId + '\'' +
                ", reviewerId='" + reviewerId + '\'' +
                ", influencingCriteriaId='" + influencingCriteriaId + '\'' +
                ", influencedCriteriaId='" + influencedCriteriaId + '\'' +
                ", ratingValue=" + ratingValue + // String akan tercetak dengan baik
                '}';
    }
}