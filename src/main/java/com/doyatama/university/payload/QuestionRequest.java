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

    private String rps_detail_id;

    private String examType;
    private String examType2;
    private String examType3;
    private String explanation;

    private String reviewer;
    private Double averageValue1;
    private Double averageValue2;
    private Double averageValue3;
    private Double averageValue4;
    private Double averageValue5;
    private Double averageValue6;
    private Double averageValue7;
    private Double averageValue8;
    private Double averageValue9;
    private Double averageValue10;

    private Boolean is_rated;

    public QuestionRequest() {
    }

    public QuestionRequest(String idQuestion, String title, String description, String question_type, String answer_type,
                           String idRps, String rps_detail_id, String examType, String examType2, String examType3, String explanation,
                           Boolean is_rated, Double averageValue1, Double averageValue2, Double averageValue3, Double averageValue4,
                           Double averageValue5, Double averageValue6, Double averageValue7, Double averageValue8, Double averageValue9, Double averageValue10,
                           String reviewer) {
        this.reviewer = reviewer;
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
        this.averageValue1 = averageValue1;
        this.averageValue2 = averageValue2;
        this.averageValue3 = averageValue3;
        this.averageValue4 = averageValue4;
        this.averageValue5 = averageValue5;
        this.averageValue6 = averageValue6;
        this.averageValue7 = averageValue7;
        this.averageValue8 = averageValue8;
        this.averageValue9 = averageValue9;
        this.averageValue10 = averageValue10;
    }

    // Getters and Setters
    // PENTING: Untuk idQuestion, nama getter harus getIdQuestion(), bukan getId()
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

    // Untuk backward compatibility (jika ada API lain yang memanggilnya dengan 'rps')
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

    public String getReviewer() {
        return reviewer;
    }
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Double getAverageValue1() {
        return averageValue1;
    }
    public void setAverageValue1(Double averageValue1) {
        this.averageValue1 = averageValue1;
    }
    public Double getAverageValue2() {
        return averageValue2;
    }
    public void setAverageValue2(Double averageValue2) {
        this.averageValue2 = averageValue2;
    }
    public Double getAverageValue3() {
        return averageValue3;
    }
    public void setAverageValue3(Double averageValue3) {
        this.averageValue3 = averageValue3;
    }
    public Double getAverageValue4() {
        return averageValue4;
    }
    public void setAverageValue4(Double averageValue4) {
        this.averageValue4 = averageValue4;
    }
    public Double getAverageValue5() {
        return averageValue5;
    }
    public void setAverageValue5(Double averageValue5) {
        this.averageValue5 = averageValue5;
    }
    public Double getAverageValue6() {
        return averageValue6;
    }
    public void setAverageValue6(Double averageValue6) {
        this.averageValue6 = averageValue6;
    }
    public Double getAverageValue7() {
        return averageValue7;
    }
    public void setAverageValue7(Double averageValue7) {
        this.averageValue7 = averageValue7;
    }
    public Double getAverageValue8() {
        return averageValue8;
    }
    public void setAverageValue8(Double averageValue8) {
        this.averageValue8 = averageValue8;
    }
    public Double getAverageValue9() {
        return averageValue9;
    }
    public void setAverageValue9(Double averageValue9) {
        this.averageValue9 = averageValue9;
    }
    public Double getAverageValue10() {
        return averageValue10;
    }
    public void setAverageValue10(Double averageValue10) {
        this.averageValue10 = averageValue10;
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
            // ✅ TAMBAHKAN INI
            case "reviewer":
                this.reviewer = value;
                break;
            case "averageValue1":
                this.averageValue1 = Double.parseDouble(value);
                break;
            case "averageValue2":
                this.averageValue2 = Double.parseDouble(value);
                break;
            case "averageValue3":
                this.averageValue3 = Double.parseDouble(value);
                break;
            case "averageValue4":
                this.averageValue4 = Double.parseDouble(value);
                break;
            case "averageValue5":
                this.averageValue5 = Double.parseDouble(value);
                break;
            case "averageValue6":
                this.averageValue6 = Double.parseDouble(value);
                break;
            case "averageValue7":
                this.averageValue7 = Double.parseDouble(value);
                break;
            case "averageValue8":
                this.averageValue8 = Double.parseDouble(value);
                break;
            case "averageValue9":
                this.averageValue9 = Double.parseDouble(value);
                break;
            case "averageValue10":
                this.averageValue10 = Double.parseDouble(value);
                break;
            // ✅ AKHIR TAMBAHAN
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }

    // ✅ FIXED: toString untuk debugging yang lengkap
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
                ", reviewer='" + reviewer + '\'' + // Tambahkan ini
                ", averageValue1=" + averageValue1 + // Tambahkan ini
                ", averageValue2=" + averageValue2 + // Tambahkan ini
                ", averageValue3=" + averageValue3 + // Tambahkan ini
                ", averageValue4=" + averageValue4 + // Tambahkan ini
                ", averageValue5=" + averageValue5 + // Tambahkan ini
                ", averageValue6=" + averageValue6 + // Tambahkan ini
                ", averageValue7=" + averageValue7 + // Tambahkan ini
                ", averageValue8=" + averageValue8 + // Tambahkan ini
                ", averageValue9=" + averageValue9 + // Tambahkan ini
                ", averageValue10=" + averageValue10 + // Tambahkan ini
                ", is_rated=" + is_rated + // Pastikan ini juga ada
                '}';
    }
}