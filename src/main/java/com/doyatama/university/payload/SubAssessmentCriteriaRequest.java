package com.doyatama.university.payload;

public class SubAssessmentCriteriaRequest{
    private String subAssessmentCriteriaId;
    private String assessmentCriteriaId;
    private String name;
    private String description;
    private double weight;

    public SubAssessmentCriteriaRequest() {
    }

    public SubAssessmentCriteriaRequest(String subAssessmentCriteriaId ,String assessmentCriteriaId, String name, String description, double weight) {
        this.subAssessmentCriteriaId = subAssessmentCriteriaId;
        this.name = name;
        this.assessmentCriteriaId = assessmentCriteriaId;
        this.description = description;
        this.weight = weight;
    }

    public String getSubAssessmentCriteriaId () {
        return subAssessmentCriteriaId;
    }

    public void setSubAssessmentCriteriaId(String subAssessmentCriteriaId) {
        this.subAssessmentCriteriaId = subAssessmentCriteriaId;
    }

    public String getAssessmentCriteriaId() {
        return assessmentCriteriaId;
    }

    public void setAssessmentCriteriaId(String assessmentCriteriaId) {
        this.assessmentCriteriaId = assessmentCriteriaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "subAssessmentCriteriaId":
                this.subAssessmentCriteriaId = value;
                break;
            case "name":
                this.name = value;
                break;
            case "assessmentCriteriaId":
                this.assessmentCriteriaId = value;
                break;
            case "description":
                this.description = value;
                break;
            case "weight":
                this.weight = Double.parseDouble(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
