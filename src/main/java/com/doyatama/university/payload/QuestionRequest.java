package com.doyatama.university.payload;

import com.doyatama.university.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Collections;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

public class QuestionRequest {
    private String idQuestion;
    private String title;
    private String description;
    private String question_type;
    private String answer_type;
    
    private String idRps;
   
    private String rps_detail_id;
    
    private String examType;
    private String examType2;
    private String examType3;
    private String explanation;

    private Boolean is_rated;

    public QuestionRequest() {
    }

    public QuestionRequest(String idQuestion, String title, String description, String question_type, String answer_type, String idRps, String rps_detail_id, String examType, String examType2, String examType3, String explanation, Boolean is_rated) {
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.question_type = question_type;
        this.answer_type = answer_type;
        this.idRps = idRps;
        this.rps_detail_id = rps_detail_id;
        this.examType = examType;
        this.examType2 = examType2;
        this.examType3 = examType3;
        this.explanation = explanation;
        this.is_rated = is_rated;
    }

    // Getters and Setters
    public String getId() {
        return idQuestion;
    }

    public void setId(String idQuestion) {
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

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    // ✅ FIXED: Method name yang benar
    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamType2() {
        return examType2;
    }

    public void setExamType2(String examType2) {
        this.examType2 = examType2;
    }

    public String getExamType3() {
        return examType3;
    }

    public void setExamType3(String examType3) {
        this.examType3 = examType3;
    }

    // ✅ FIXED: Getter untuk idRps
    public String getIdRps() {
        return idRps;
    }
    
    // ✅ FIXED: Setter untuk idRps
    public void setIdRps(String idRps) {
        this.idRps = idRps;
    }
    
    // Untuk backward compatibility
    public String getRps() {
        return idRps;
    }
    
    public void setRps(String idRps) {
        this.idRps = idRps;
    }

    public String getRps_detail_id() {
        return this.rps_detail_id;
    }

    public void setRps_detail_id(String rps_detail_id) {
        this.rps_detail_id = rps_detail_id;
    }
    
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Boolean getIs_rated() {
        return is_rated;
    }
    public void setIs_rated(Boolean is_rated) {
        this.is_rated = is_rated;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idQuestion":
                this.idQuestion = value;
                break;
            case "rps":
            case "idRps":  // ✅ Handle both variants
                this.idRps = value;
                break;
            case "rps_detail_id":
                this.rps_detail_id = value;
                break;
            case "title":
                this.title = value;
                break;
            case "description":
                this.description = value;
                break;
            case "question_type":
                this.question_type = value;
                break;
            case "answer_type":
                this.answer_type = value;
                break;
            case "examType":
                this.examType = value;
                break;
            case "examType2":
                this.examType2 = value;
                break;
            case "examType3":
                this.examType3 = value;
                break;
            case "explanation":
                this.explanation = value;
                break;
            case "is_rated":
                this.is_rated = Boolean.parseBoolean(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
    
    // ✅ ADD: toString untuk debugging
    @Override
    public String toString() {
        return "QuestionRequest{" +
                "idQuestion='" + idQuestion + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", question_type='" + question_type + '\'' +
                ", answer_type='" + answer_type + '\'' +
                ", idRps='" + idRps + '\'' +
                ", rps_detail_id='" + rps_detail_id + '\'' +
                ", examType='" + examType + '\'' +
                ", examType2='" + examType2 + '\'' +
                ", examType3='" + examType3 + '\'' +
                ", explanation='" + explanation + '\'' +
                ", is_rated=" + is_rated +
                '}';
    }
}