package com.doyatama.university.model;

public class SubAssessmentCriteria {
    private String idSubAssessment;
    private AssessmentCriteria assessment_criterias;
    private String name;
    private String description;
    private double weight;

    public SubAssessmentCriteria() {
    }

    public SubAssessmentCriteria(String idSubAssessment, AssessmentCriteria assessment_criterias, String name, String description, double weight) {
        this.idSubAssessment = idSubAssessment;
        this.assessment_criterias = assessment_criterias;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getSubAssessmentCriteriaId() {
        return idSubAssessment;
    }

    public void setSubAssessmentCriteriaId(String idSubAssessment) {
        this.idSubAssessment = idSubAssessment;
    }

    public AssessmentCriteria getAssessmentCriteria() {
        return assessment_criterias;
    }

    public void setAssessmentCriteria(AssessmentCriteria assessment_criterias) {
        this.assessment_criterias = assessment_criterias;
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

    public boolean isValid() {
        return this.idSubAssessment != null && this.assessment_criterias != null && this.name != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idSubAssessment":
                this.idSubAssessment = value;
                break;
            case "name":
                this.name = value;
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
