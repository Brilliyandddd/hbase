package com.doyatama.university.payload;

import com.doyatama.university.model.*;
import com.fasterxml.jackson.annotation.JsonProperty; // Not strictly needed for this class unless you remap properties

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

    private String rps_detail;

    private String examType;
    private String examType2;
    private String examType3;
    private String explanation;

    private String reviewer;
    private Double averageValue1;
    private String linguisticValue1Id; // NEW FIELD
    private Double averageValue2;
    private String linguisticValue2Id; // NEW FIELD
    private Double averageValue3;
    private String linguisticValue3Id; // NEW FIELD
    private Double averageValue4;
    private String linguisticValue4Id; // NEW FIELD
    private Double averageValue5;
    private String linguisticValue5Id; // NEW FIELD
    private Double averageValue6;
    private String linguisticValue6Id; // NEW FIELD
    private Double averageValue7;
    private String linguisticValue7Id; // NEW FIELD
    private Double averageValue8;
    private String linguisticValue8Id; // NEW FIELD
    private Double averageValue9;
    private String linguisticValue9Id; // NEW FIELD
    private Double averageValue10;
    private String linguisticValue10Id; // NEW FIELD

    private Boolean is_rated;

    public QuestionRequest() {
    }

    public QuestionRequest(String idQuestion, String title, String description, String question_type, String answer_type,
                           String idRps, String rps_detail, String examType, String examType2, String examType3, String explanation,
                           Boolean is_rated, Double averageValue1, String linguisticValue1Id, // Add linguisticValue1Id
                           Double averageValue2, String linguisticValue2Id, // Add linguisticValue2Id
                           Double averageValue3, String linguisticValue3Id, // Add linguisticValue3Id
                           Double averageValue4, String linguisticValue4Id, // Add linguisticValue4Id
                           Double averageValue5, String linguisticValue5Id, // Add linguisticValue5Id
                           Double averageValue6, String linguisticValue6Id, // Add linguisticValue6Id
                           Double averageValue7, String linguisticValue7Id, // Add linguisticValue7Id
                           Double averageValue8, String linguisticValue8Id, // Add linguisticValue8Id
                           Double averageValue9, String linguisticValue9Id, // Add linguisticValue9Id
                           Double averageValue10, String linguisticValue10Id, // Add linguisticValue10Id
                           String reviewer) {
        this.reviewer = reviewer;
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.question_type = question_type;
        this.answer_type = answer_type;
        this.idRps = idRps;
        this.rps_detail = rps_detail;
        this.examType = examType;
        this.examType2 = examType2;
        this.examType3 = examType3;
        this.explanation = explanation;
        this.is_rated = is_rated;

        this.averageValue1 = averageValue1;
        this.linguisticValue1Id = linguisticValue1Id; // Initialize new field
        this.averageValue2 = averageValue2;
        this.linguisticValue2Id = linguisticValue2Id; // Initialize new field
        this.averageValue3 = averageValue3;
        this.linguisticValue3Id = linguisticValue3Id; // Initialize new field
        this.averageValue4 = averageValue4;
        this.linguisticValue4Id = linguisticValue4Id; // Initialize new field
        this.averageValue5 = averageValue5;
        this.linguisticValue5Id = linguisticValue5Id; // Initialize new field
        this.averageValue6 = averageValue6;
        this.linguisticValue6Id = linguisticValue6Id; // Initialize new field
        this.averageValue7 = averageValue7;
        this.linguisticValue7Id = linguisticValue7Id; // Initialize new field
        this.averageValue8 = averageValue8;
        this.linguisticValue8Id = linguisticValue8Id; // Initialize new field
        this.averageValue9 = averageValue9;
        this.linguisticValue9Id = linguisticValue9Id; // Initialize new field
        this.averageValue10 = averageValue10;
        this.linguisticValue10Id = linguisticValue10Id; // Initialize new field
    }

    // Getters and Setters
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

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getAnswer_type() {
        return answer_type;
    }

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

    public String getIdRps() {
        return idRps;
    }

    public void setIdRps(String idRps) {
        this.idRps = idRps;
    }

    public String getRps() {
        return idRps;
    }

    public void setRps(String idRps) {
        this.idRps = idRps;
    }

    public String getRps_detail() {
        return this.rps_detail;
    }

    public void setRps_detail(String rps_detail) {
        this.rps_detail = rps_detail;
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

    public String getReviewer() {
        return reviewer;
    }
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    // Getters and Setters for averageValueX
    public Double getAverageValue1() {
        return averageValue1;
    }
    public void setAverageValue1(Double averageValue1) {
        this.averageValue1 = averageValue1;
    }

    // NEW GETTER/SETTER for linguisticValue1Id
    public String getLinguisticValue1Id() {
        return linguisticValue1Id;
    }
    public void setLinguisticValue1Id(String linguisticValue1Id) {
        this.linguisticValue1Id = linguisticValue1Id;
    }

    public Double getAverageValue2() {
        return averageValue2;
    }
    public void setAverageValue2(Double averageValue2) {
        this.averageValue2 = averageValue2;
    }
    // NEW GETTER/SETTER for linguisticValue2Id
    public String getLinguisticValue2Id() {
        return linguisticValue2Id;
    }
    public void setLinguisticValue2Id(String linguisticValue2Id) {
        this.linguisticValue2Id = linguisticValue2Id;
    }

    public Double getAverageValue3() {
        return averageValue3;
    }
    public void setAverageValue3(Double averageValue3) {
        this.averageValue3 = averageValue3;
    }
    // NEW GETTER/SETTER for linguisticValue3Id
    public String getLinguisticValue3Id() {
        return linguisticValue3Id;
    }
    public void setLinguisticValue3Id(String linguisticValue3Id) {
        this.linguisticValue3Id = linguisticValue3Id;
    }

    public Double getAverageValue4() {
        return averageValue4;
    }
    public void setAverageValue4(Double averageValue4) {
        this.averageValue4 = averageValue4;
    }
    // NEW GETTER/SETTER for linguisticValue4Id
    public String getLinguisticValue4Id() {
        return linguisticValue4Id;
    }
    public void setLinguisticValue4Id(String linguisticValue4Id) {
        this.linguisticValue4Id = linguisticValue4Id;
    }

    public Double getAverageValue5() {
        return averageValue5;
    }
    public void setAverageValue5(Double averageValue5) {
        this.averageValue5 = averageValue5;
    }
    // NEW GETTER/SETTER for linguisticValue5Id
    public String getLinguisticValue5Id() {
        return linguisticValue5Id;
    }
    public void setLinguisticValue5Id(String linguisticValue5Id) {
        this.linguisticValue5Id = linguisticValue5Id;
    }

    public Double getAverageValue6() {
        return averageValue6;
    }
    public void setAverageValue6(Double averageValue6) {
        this.averageValue6 = averageValue6;
    }
    // NEW GETTER/SETTER for linguisticValue6Id
    public String getLinguisticValue6Id() {
        return linguisticValue6Id;
    }
    public void setLinguisticValue6Id(String linguisticValue6Id) {
        this.linguisticValue6Id = linguisticValue6Id;
    }

    public Double getAverageValue7() {
        return averageValue7;
    }
    public void setAverageValue7(Double averageValue7) {
        this.averageValue7 = averageValue7;
    }
    // NEW GETTER/SETTER for linguisticValue7Id
    public String getLinguisticValue7Id() {
        return linguisticValue7Id;
    }
    public void setLinguisticValue7Id(String linguisticValue7Id) {
        this.linguisticValue7Id = linguisticValue7Id;
    }

    public Double getAverageValue8() {
        return averageValue8;
    }
    public void setAverageValue8(Double averageValue8) {
        this.averageValue8 = averageValue8;
    }
    // NEW GETTER/SETTER for linguisticValue8Id
    public String getLinguisticValue8Id() {
        return linguisticValue8Id;
    }
    public void setLinguisticValue8Id(String linguisticValue8Id) {
        this.linguisticValue8Id = linguisticValue8Id;
    }

    public Double getAverageValue9() {
        return averageValue9;
    }
    public void setAverageValue9(Double averageValue9) {
        this.averageValue9 = averageValue9;
    }
    // NEW GETTER/SETTER for linguisticValue9Id
    public String getLinguisticValue9Id() {
        return linguisticValue9Id;
    }
    public void setLinguisticValue9Id(String linguisticValue9Id) {
        this.linguisticValue9Id = linguisticValue9Id;
    }

    public Double getAverageValue10() {
        return averageValue10;
    }
    public void setAverageValue10(Double averageValue10) {
        this.averageValue10 = averageValue10;
    }
    // NEW GETTER/SETTER for linguisticValue10Id
    public String getLinguisticValue10Id() {
        return linguisticValue10Id;
    }
    public void setLinguisticValue10Id(String linguisticValue10Id) {
        this.linguisticValue10Id = linguisticValue10Id;
    }

    // Method `set` ini kemungkinan besar tidak digunakan oleh Jackson
    // saat @RequestBody digunakan, jadi perubahannya tidak terlalu relevan untuk masalah ini.
    // Namun, jika Anda memang menggunakan ini untuk parsing manual, maka perlu ditambahkan averageValueX
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idQuestion":
                this.idQuestion = value;
                break;
            case "rps":
            case "idRps":
                this.idRps = value;
                break;
            case "rps_detail":
                this.rps_detail = value;
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
            case "reviewer":
                this.reviewer = value;
                break;
            case "averageValue1":
                this.averageValue1 = Double.parseDouble(value);
                break;
            case "linguisticValue1Id": // NEW CASE
                this.linguisticValue1Id = value;
                break;
            case "averageValue2":
                this.averageValue2 = Double.parseDouble(value);
                break;
            case "linguisticValue2Id": // NEW CASE
                this.linguisticValue2Id = value;
                break;
            case "averageValue3":
                this.averageValue3 = Double.parseDouble(value);
                break;
            case "linguisticValue3Id": // NEW CASE
                this.linguisticValue3Id = value;
                break;
            case "averageValue4":
                this.averageValue4 = Double.parseDouble(value);
                break;
            case "linguisticValue4Id": // NEW CASE
                this.linguisticValue4Id = value;
                break;
            case "averageValue5":
                this.averageValue5 = Double.parseDouble(value);
                break;
            case "linguisticValue5Id": // NEW CASE
                this.linguisticValue5Id = value;
                break;
            case "averageValue6":
                this.averageValue6 = Double.parseDouble(value);
                break;
            case "linguisticValue6Id": // NEW CASE
                this.linguisticValue6Id = value;
                break;
            case "averageValue7":
                this.averageValue7 = Double.parseDouble(value);
                break;
            case "linguisticValue7Id": // NEW CASE
                this.linguisticValue7Id = value;
                break;
            case "averageValue8":
                this.averageValue8 = Double.parseDouble(value);
                break;
            case "linguisticValue8Id": // NEW CASE
                this.linguisticValue8Id = value;
                break;
            case "averageValue9":
                this.averageValue9 = Double.parseDouble(value);
                break;
            case "linguisticValue9Id": // NEW CASE
                this.linguisticValue9Id = value;
                break;
            case "averageValue10":
                this.averageValue10 = Double.parseDouble(value);
                break;
            case "linguisticValue10Id": // NEW CASE
                this.linguisticValue10Id = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }

    @Override
    public String toString() {
        return "QuestionRequest{" +
                "idQuestion='" + idQuestion + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", question_type='" + question_type + '\'' +
                ", answer_type='" + answer_type + '\'' +
                ", idRps='" + idRps + '\'' +
                ", rps_detail='" + rps_detail + '\'' +
                ", examType='" + examType + '\'' +
                ", examType2='" + examType2 + '\'' +
                ", examType3='" + examType3 + '\'' +
                ", explanation='" + explanation + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", averageValue1=" + averageValue1 +
                ", linguisticValue1Id='" + linguisticValue1Id + '\'' + // Include in toString
                ", averageValue2=" + averageValue2 +
                ", linguisticValue2Id='" + linguisticValue2Id + '\'' + // Include in toString
                ", averageValue3=" + averageValue3 +
                ", linguisticValue3Id='" + linguisticValue3Id + '\'' + // Include in toString
                ", averageValue4=" + averageValue4 +
                ", linguisticValue4Id='" + linguisticValue4Id + '\'' + // Include in toString
                ", averageValue5=" + averageValue5 +
                ", linguisticValue5Id='" + linguisticValue5Id + '\'' + // Include in toString
                ", averageValue6=" + averageValue6 +
                ", linguisticValue6Id='" + linguisticValue6Id + '\'' + // Include in toString
                ", averageValue7=" + averageValue7 +
                ", linguisticValue7Id='" + linguisticValue7Id + '\'' + // Include in toString
                ", averageValue8=" + averageValue8 +
                ", linguisticValue8Id='" + linguisticValue8Id + '\'' + // Include in toString
                ", averageValue9=" + averageValue9 +
                ", linguisticValue9Id='" + linguisticValue9Id + '\'' + // Include in toString
                ", averageValue10=" + averageValue10 +
                ", linguisticValue10Id='" + linguisticValue10Id + '\'' + // Include in toString
                ", is_rated=" + is_rated +
                '}';
    }
}