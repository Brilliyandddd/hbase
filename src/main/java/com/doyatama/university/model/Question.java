package com.doyatama.university.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Import ini

import com.fasterxml.jackson.core.JsonProcessingException; // Import ini
import com.fasterxml.jackson.databind.ObjectMapper; // Import ini
import com.fasterxml.jackson.core.type.TypeReference; // Import ini untuk List<CriteriaValue>

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; // Jika diperlukan oleh helper lain
import java.util.stream.Collectors; // Jika diperlukan oleh helper lain
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Untuk Instant/LocalDateTime jika ada di CriteriaValue

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true) // Penting untuk mengabaikan field yang tidak dikenal saat deserialisasi

public class Question {
    private String idQuestion;
    private String title;
    private String description;
    private QuestionType question_type;
    private AnswerType answer_type;
    private String file_path;
    private RPS rps;
    private RPSDetail rps_detail_id;
    private boolean is_rated; // NEW: Field is_rated
    private ExamType examType;
    private ExamType2 examType2;
    private ExamType3 examType3;
    private String explanation;
    private ExerciseAttempt exerciseAttempt;

    // Untuk hitung ivihf topsis tahap matrix
    // @Transient // Opsional: Jika Anda menggunakan JPA, ini mencegah field ini dipetakan langsung ke DB
    private List<CriteriaValue> criteria_values;

    // Field-field averageValue1-10 (Asumsi ini dihitung dari criteria_values)
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

    // NEW: Field untuk menyimpan List<CriteriaValue> sebagai JSON String di HBase
    private String criteriaValuesJson; 

    // NEW: ObjectMapper untuk konversi JSON (harus statis dan singleton)
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule()); // Penting jika ada Instant/LocalDateTime di CriteriaValue
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Hanya serialize field non-null
    }

    public enum QuestionType {
        IMAGE, AUDIO, VIDEO, NORMAL,
    }

    public enum AnswerType {
        MULTIPLE_CHOICE, BOOLEAN, COMPLETION, ESSAY, MATCHING,
    }

    public enum ExamType{
        EXERCISE, NOTHING,
    }

    public enum ExamType2{
        QUIZ, NOTHING,
    }
    
    public enum ExamType3{
        EXAM, NOTHING,
    }

    public Question() {
        // Default constructor
    }

    // Konstruktor dengan semua field, termasuk is_rated dan criteria_values
    public Question(String idQuestion, String title, String description, QuestionType questionType, AnswerType answerType, 
                    String file_path, RPS rps, RPSDetail rps_detail_id, boolean is_rated, // NEW: is_rated di konstruktor
                    ExamType examType, ExamType2 examType2, ExamType3 examType3, String explanation, 
                    List<CriteriaValue> criteria_values, Double averageValue1, Double averageValue2, 
                    Double averageValue3, Double averageValue4, Double averageValue5, Double averageValue6, 
                    Double averageValue7, Double averageValue8, Double averageValue9, Double averageValue10) {
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.question_type = questionType;
        this.answer_type = answerType;
        this.file_path = file_path;
        this.rps = rps;
        this.rps_detail_id = rps_detail_id;
        this.is_rated = is_rated; // NEW: Set is_rated
        this.examType = examType;
        this.examType2 = examType2;
        this.examType3 = examType3;
        this.explanation = explanation;

        this.criteria_values = criteria_values;
        // NEW: Konversi List<CriteriaValue> ke JSON String saat constructor dipanggil
        if (criteria_values != null) {
            try {
                this.criteriaValuesJson = objectMapper.writeValueAsString(criteria_values);
            } catch (JsonProcessingException e) {
                System.err.println("Error serializing criteria_values in constructor: " + e.getMessage());
                this.criteriaValuesJson = "[]"; // Fallback to empty JSON array
            }
        } else {
            this.criteriaValuesJson = "[]";
        }

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

    // --- Getters and Setters ---
    public String getIdQuestion() { return idQuestion; }
    public void setIdQuestion(String idQuestion) { this.idQuestion = idQuestion; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public QuestionType getQuestionType() { return question_type; }
    public void setQuestionType(QuestionType question_type) { this.question_type = question_type; }

    public AnswerType getAnswerType() { return answer_type; }
    public void setAnswerType(AnswerType answer_type) { this.answer_type = answer_type; }

    public String getFile_path() { return file_path; }
    public void setFile_path(String file_path) { this.file_path = file_path; }

    public RPS getRps() { return rps; }
    public void setRps(RPS rps) { this.rps = rps; }

    public RPSDetail getRps_detail_id() { return rps_detail_id; }
    public void setRps_detail_id(RPSDetail rps_detail_id) { this.rps_detail_id = rps_detail_id; }

    public ExamType getExamType() { return this.examType != null ? this.examType : ExamType.NOTHING; }
    public void setExamType(ExamType examType) { this.examType = examType; }

    public ExamType2 getExamType2() { return this.examType2 != null ? this.examType2 : ExamType2.NOTHING; }
    public void setExamType2(ExamType2 examType2) { this.examType2 = examType2; }

    public ExamType3 getExamType3() { return this.examType3 != null ? this.examType3 : ExamType3.NOTHING; }
    public void setExamType3(ExamType3 examType3) { this.examType3 = examType3; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    // NEW: Getter untuk is_rated
    public boolean isIs_rated() { return is_rated; }
    public void setIs_rated(boolean is_rated) { this.is_rated = is_rated; }

    // --- Getters and Setters untuk ivihf topsis tahap matrix ---
    @JsonInclude(JsonInclude.Include.NON_NULL) // Pastikan ini hanya diserialkan jika tidak null saat JSON out
    public List<CriteriaValue> getCriteriaValues() {
        // NEW: Jika criteria_values (objek) null, coba deserialisasi dari criteriaValuesJson (string)
        if (this.criteria_values == null && this.criteriaValuesJson != null && !this.criteriaValuesJson.isEmpty()) {
            try {
                this.criteria_values = objectMapper.readValue(this.criteriaValuesJson, new TypeReference<List<CriteriaValue>>() {});
            } catch (JsonProcessingException e) {
                System.err.println("Error deserializing criteriaValuesJson: " + e.getMessage());
                this.criteria_values = new ArrayList<>(); // Fallback
            }
        } else if (this.criteria_values == null) {
            this.criteria_values = new ArrayList<>(); // Pastikan tidak pernah return null
        }
        return criteria_values;
    }

    public void setCriteriaValues(List<CriteriaValue> criteria_values) {
        this.criteria_values = criteria_values;
        // NEW: Serialisasi List<CriteriaValue> ke JSON String saat diset
        try {
            this.criteriaValuesJson = objectMapper.writeValueAsString(criteria_values);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing criteria_values in setter: " + e.getMessage());
            this.criteriaValuesJson = "[]"; // Fallback to empty JSON array
        }
    }

    // NEW: Getter dan Setter untuk field String JSON (criteriaValuesJson)
    public String getCriteriaValuesJson() {
        return criteriaValuesJson;
    }

    public void setCriteriaValuesJson(String criteriaValuesJson) {
        this.criteriaValuesJson = criteriaValuesJson;
        // Saat set string ini, pastikan objek List<CriteriaValue> direset
        this.criteria_values = null; // Agar getter memicu deserialisasi ulang
    }

    public Double getAverageValue1() { return averageValue1; }
    public void setAverageValue1(Double averageValue1) { this.averageValue1 = averageValue1; }

    public Double getAverageValue2() { return averageValue2; }
    public void setAverageValue2(Double averageValue2) { this.averageValue2 = averageValue2; }

    public Double getAverageValue3() { return averageValue3; }
    public void setAverageValue3(Double averageValue3) { this.averageValue3 = averageValue3; }

    public Double getAverageValue4() { return averageValue4; }
    public void setAverageValue4(Double averageValue4) { this.averageValue4 = averageValue4; }

    public Double getAverageValue5() { return averageValue5; }
    public void setAverageValue5(Double averageValue5) { this.averageValue5 = averageValue5; }

    public Double getAverageValue6() { return averageValue6; }
    public void setAverageValue6(Double averageValue6) { this.averageValue6 = averageValue6; }

    public Double getAverageValue7() { return averageValue7; }
    public void setAverageValue7(Double averageValue7) { this.averageValue7 = averageValue7; }

    public Double getAverageValue8() { return averageValue8; }
    public void setAverageValue8(Double averageValue8) { this.averageValue8 = averageValue8; }

    public Double getAverageValue9() { return averageValue9; }
    public void setAverageValue9(Double averageValue9) { this.averageValue9 = averageValue9; }

    public Double getAverageValue10() { return averageValue10; }
    public void setAverageValue10(Double averageValue10) { this.averageValue10 = averageValue10; }

    public boolean isValid() {
        return this.idQuestion != null && this.title != null && this.description != null && this.question_type != null && this.answer_type != null ;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idQuestion": this.idQuestion = value; break;
            case "title": this.title = value; break;
            case "description": this.description = value; break;
            case "question_type": this.question_type = QuestionType.valueOf(value); break;
            case "answer_type": this.answer_type = AnswerType.valueOf(value); break;
            case "file_path": this.file_path = value; break;
            // Handle RPS dan RPSDetail jika disimpan sebagai String ID dalam set()
            // case "rps": this.rps = new RPS(); this.rps.setIdRps(value); break; // Contoh jika RPS disimpan sebagai ID String
            // case "rps_detail_id": this.rps_detail_id = new RPSDetail(); this.rps_detail_id.setId(value); break;
            default: throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}