package com.doyatama.university.model;

/**
 * @author alfa
 */
public class QuestionCriteria {
    private String id;
    private String name;
    private String description;
    private String category;
    private String type;
    private Double weight;

    private static final int MAX_ID_LENGTH = 6; // replace 16 with your desired maximum length


    public QuestionCriteria() {
    }

    // Constructor
    public QuestionCriteria(String id, String name, String description, String category,String type, Double weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.weight = weight;
    }
    // Getters and setters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public boolean isValid(){
        return this.id != null && this.name != null && this.description != null && this.category !=null;
    }
    public void set(String fieldName, Object value) {
        switch (fieldName) {
            case "id":
            String idValue = String.valueOf(value);
            if (idValue.length() > MAX_ID_LENGTH) {
                throw new IllegalArgumentException("ID must be no more than " + MAX_ID_LENGTH + " characters long");
            }
            this.id = idValue;
            break;
            case "name":
                this.name = String.valueOf(value);
                break;
            case "description":
                this.description = String.valueOf(value);
                break;
            case "category":
                this.category = String.valueOf(value);
                break;
            case "type":
                this.type = String.valueOf(value);
                break;
            default:
                throw new IllegalArgumentException("Field " + fieldName + " not found");
        }
    }


}