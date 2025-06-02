package com.doyatama.university.payload;
public class AnswerRequest {
    private String idAnswer;
    private String idQuestion;
    private String title;
    private String description;
    private String type;
    private Boolean is_right;

    public AnswerRequest() {
    }

    public AnswerRequest(String idAnswer, String idQuestion, String title, String description, String type, Boolean is_right) {
        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.type = type;
        this.is_right = is_right;

    }

    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_right() {
        return is_right;
    }

    public void setIs_right(Boolean is_right) {
        this.is_right = is_right;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idAnswer":
                this.idAnswer = value;
                break;
            case "idQuestion":
                this.idQuestion = value;
                break;
            case "title":
                this.title = value;
                break;
            case "description":
                this.description = value;
                break;
            case "type":
                this.type = value;
                break;
            case "is_right":
                this.is_right = Boolean.valueOf(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}