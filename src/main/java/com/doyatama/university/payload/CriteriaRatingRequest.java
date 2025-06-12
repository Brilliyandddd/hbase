package com.doyatama.university.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriteriaRatingRequest {
    private String questionId;
    private String criterionId;
    private Double ratingValue;

    // Default constructor is usually needed for deserialization
    public CriteriaRatingRequest() {
    }

    // Constructor with all fields (optional, but good practice)
    public CriteriaRatingRequest(String questionId, String criterionId, Double ratingValue) {
        this.questionId = questionId;
        this.criterionId = criterionId;
        this.ratingValue = ratingValue;
    }

    // Getters and Setters
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public Double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Double ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "CriteriaRatingRequest{" +
                "questionId='" + questionId + '\'' +
                ", criterionId='" + criterionId + '\'' +
                ", ratingValue=" + ratingValue +
                '}';
    }
}