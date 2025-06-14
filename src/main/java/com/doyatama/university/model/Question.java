package com.doyatama.university.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty; // Import ini
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private String idQuestion;
    private String title;
    private String description;
    private QuestionType question_type;
    private AnswerType answer_type;
    private String file_path;
    private RPS rps;
    private RPSDetail rps_detail_id;
    private boolean is_rated;
    private ExamType examType;
    private ExamType2 examType2;
    private ExamType3 examType3;
    private String explanation;
    private ExerciseAttempt exerciseAttempt;

    // Field untuk menyimpan List<CriteriaValue> sebagai JSON String di HBase
    private String criteriaValuesJson;
    // Transient field for criteria_values object
    private transient List<CriteriaValue> criteria_values;

    // Field untuk menyimpan struktur rating oleh reviewer sebagai JSON String di HBase
    private String questionRatingJson;
    // Transient field for QuestionRating object
    private transient QuestionRating questionRating; // Tetap transient

    // ObjectMapper untuk konversi JSON (harus statis dan singleton)
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public enum QuestionType {
        IMAGE, AUDIO, VIDEO, NORMAL,
    }

    public enum AnswerType {
        MULTIPLE_CHOICE, BOOLEAN, COMPLETION, ESSAY, MATCHING,
    }

    public enum ExamType {
        EXERCISE, NOTHING,
    }

    public enum ExamType2 {
        QUIZ, NOTHING,
    }

    public enum ExamType3 {
        EXAM, NOTHING,
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class QuestionRating {
        private String idQuestion;
        @JsonProperty("reviewerRatings")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, ReviewerRating> reviewerRatings;

        public QuestionRating() {
            this.reviewerRatings = new HashMap<>();
        }

        public QuestionRating(String idQuestion) {
            this.idQuestion = idQuestion;
            this.reviewerRatings = new HashMap<>();
        }

        public String getIdQuestion() { return idQuestion; }
        public void setIdQuestion(String idQuestion) { this.idQuestion = idQuestion; }

        @JsonProperty("reviewerRatings")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public Map<String, ReviewerRating> getReviewerRatings() {
            if (reviewerRatings == null) {
                reviewerRatings = new HashMap<>();
            }
            return reviewerRatings;
        }
        public void setReviewerRatings(Map<String, ReviewerRating> reviewerRatings) { this.reviewerRatings = reviewerRatings; }

        public void addReviewerRating(String reviewerName, ReviewerRating rating) {
            if (this.reviewerRatings == null) {
                this.reviewerRatings = new HashMap<>();
            }
            this.reviewerRatings.put(reviewerName, rating);
        }
    }

    // Inner class untuk rating dari satu reviewer
    public static class ReviewerRating {
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

        public ReviewerRating() {}

        public ReviewerRating(Double averageValue1, Double averageValue2, Double averageValue3,
                              Double averageValue4, Double averageValue5, Double averageValue6,
                              Double averageValue7, Double averageValue8, Double averageValue9,
                              Double averageValue10) {
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

        // Getters and Setters untuk semua average values
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
    }

    public Question() {
        // Default constructor
    }

    // Constructor with essential fields (adjust as needed)
    public Question(String idQuestion, String title, String description, QuestionType questionType, AnswerType answerType,
                            String file_path, RPS rps, RPSDetail rps_detail_id, boolean is_rated,
                            ExamType examType, ExamType2 examType2, ExamType3 examType3, String explanation,
                            List<CriteriaValue> criteria_values) {
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.question_type = questionType;
        this.answer_type = answerType;
        this.file_path = file_path;
        this.rps = rps;
        this.rps_detail_id = rps_detail_id;
        this.is_rated = is_rated;
        this.examType = examType;
        this.examType2 = examType2;
        this.examType3 = examType3;
        this.explanation = explanation;

        // Handle criteria_values JSON serialization
        setCriteriaValues(criteria_values); // Gunakan setter untuk menangani konversi JSON
    }

    // --- Getters and Setters Utama ---
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

    public boolean isIs_rated() { return is_rated; }
    public void setIs_rated(boolean is_rated) { this.is_rated = is_rated; }

    // --- Getters and Setters untuk criteria_values (dari/ke JSON String) ---
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<CriteriaValue> getCriteriaValues() {
        if (this.criteria_values == null && this.criteriaValuesJson != null && !this.criteriaValuesJson.isEmpty()) {
            try {
                this.criteria_values = objectMapper.readValue(this.criteriaValuesJson, new TypeReference<List<CriteriaValue>>() {});
            } catch (JsonProcessingException e) {
                System.err.println("Error deserializing criteriaValuesJson: " + e.getMessage());
                this.criteria_values = new ArrayList<>();
            }
        } else if (this.criteria_values == null) {
            this.criteria_values = new ArrayList<>();
        }
        return criteria_values;
    }

    public void setCriteriaValues(List<CriteriaValue> criteria_values) {
        this.criteria_values = criteria_values;
        if (criteria_values != null) {
            try {
                this.criteriaValuesJson = objectMapper.writeValueAsString(criteria_values);
            } catch (JsonProcessingException e) {
                System.err.println("Error serializing criteria_values in setter: " + e.getMessage());
                this.criteriaValuesJson = "[]";
            }
        } else {
            this.criteriaValuesJson = "[]";
        }
    }

    public void serializeRatingIfNeeded() throws JsonProcessingException {
    if (this.questionRating != null && (this.questionRatingJson == null || this.questionRatingJson.isEmpty())) {
        this.questionRatingJson = objectMapper.writeValueAsString(this.questionRating);
    }
}

    public String getCriteriaValuesJson() { return criteriaValuesJson; }
    public void setCriteriaValuesJson(String criteriaValuesJson) {
        this.criteriaValuesJson = criteriaValuesJson;
        this.criteria_values = null; // Kosongkan objek untuk memaksa re-deserialisasi
    }

    @JsonProperty("questionRating") // Keep this annotation for JSON output
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public QuestionRating getQuestionRating() {
        // System.out.println("DEBUG Question Model: getQuestionRating() called.");
        // System.out.println("DEBUG Question Model: Current questionRatingJson: " + (this.questionRatingJson != null && this.questionRatingJson.length() > 200 ? this.questionRatingJson.substring(0, 200) + "..." : this.questionRatingJson));

        if (this.questionRating == null && this.idQuestion != null && this.questionRatingJson != null && !this.questionRatingJson.isEmpty()) {
            try {
                this.questionRating = objectMapper.readValue(this.questionRatingJson, QuestionRating.class);
                // System.out.println("DEBUG Question Model: Successfully deserialized questionRating from JSON.");
            } catch (JsonProcessingException e) {
                System.err.println("ERROR Question Model: Failed to deserialize questionRatingJson: " + e.getMessage());
                e.printStackTrace();
                this.questionRating = new QuestionRating(this.idQuestion); // Fallback
            }
        } else if (this.questionRating == null) {
            this.questionRating = new QuestionRating(this.idQuestion);
        }

        // --- FINAL DEBUG CHECKS HERE ---
        // You can remove these debug logs in production
        // if (this.questionRating != null && this.questionRating.getReviewerRatings() != null) {
        //     System.out.println("DEBUG Question Model: Reviewer Ratings before return: " + this.questionRating.getReviewerRatings().size() + " entries.");
        //     if (this.questionRating.getReviewerRatings().containsKey("dosen6")) {
        //             System.out.println("DEBUG Question Model: 'dosen6' found in map: " + this.questionRating.getReviewerRatings().get("dosen6"));
        //     } else {
        //         System.out.println("DEBUG Question Model: 'dosen6' not found in map.");
        //     }
        // } else {
        //     System.out.println("DEBUG Question Model: Reviewer Ratings map is null or questionRating is null before return.");
        // }
        // --- END FINAL DEBUG CHECKS ---

        return questionRating;
    }

    public static ObjectMapper getObjectMapper() {
    return objectMapper;
}
public void setQuestionRating(QuestionRating questionRating) {
        this.questionRating = questionRating;
        if (questionRating != null) {
            try {
                this.questionRatingJson = objectMapper.writeValueAsString(questionRating);
            } catch (JsonProcessingException e) {
                System.err.println("Error serializing questionRating in setter: " + e.getMessage());
                this.questionRatingJson = "{}";
            }
        } else {
            this.questionRatingJson = "{}";
        }
    }

    public String getQuestionRatingJson() { return questionRatingJson; }
    public void setQuestionRatingJson(String questionRatingJson) {
        this.questionRatingJson = questionRatingJson;
        this.questionRating = null; // Clear the object to force re-deserialization
        // System.out.println("Serialized QuestionRating JSON:");
        // System.out.println(this.questionRatingJson);
    }


    public boolean isValid() {
        return this.idQuestion != null && this.title != null && this.description != null && this.question_type != null && this.answer_type != null;
    }
}