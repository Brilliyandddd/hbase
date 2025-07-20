package com.doyatama.university.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private RPSDetail rps_detail;
    private boolean is_rated;
    private ExamType examType;
    private ExamType2 examType2;
    private ExamType3 examType3;
    private String explanation;
    private String created_by;
    private ExerciseAttempt exerciseAttempt;

    private String criteriaValuesJson;
    private transient List<CriteriaValue> criteria_values;

    private String questionRatingJson;
    private transient QuestionRating questionRating;

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
        // Type is now just ReviewerRating, which includes linguistic IDs
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
        // Type is now just ReviewerRating
        public Map<String, ReviewerRating> getReviewerRatings() {
            if (reviewerRatings == null) {
                reviewerRatings = new HashMap<>();
            }
            return reviewerRatings;
        }
        // Type is now just ReviewerRating
        public void setReviewerRatings(Map<String, ReviewerRating> reviewerRatings) { this.reviewerRatings = reviewerRatings; }

        // Type is now just ReviewerRating
        public void addReviewerRating(String reviewerName, ReviewerRating rating) {
            if (this.reviewerRatings == null) {
                this.reviewerRatings = new HashMap<>();
            }
            this.reviewerRatings.put(reviewerName, rating);
        }
    }

    // CRITICAL CHANGE: Merge LinguisticReviewerRating fields into ReviewerRating
    // This class now holds both average values and their linguistic IDs
    public static class ReviewerRating {
        private Double averageValue1;
        private String linguisticValue1Id; // Added
        private Double averageValue2;
        private String linguisticValue2Id; // Added
        private Double averageValue3;
        private String linguisticValue3Id; // Added
        private Double averageValue4;
        private String linguisticValue4Id; // Added
        private Double averageValue5;
        private String linguisticValue5Id; // Added
        private Double averageValue6;
        private String linguisticValue6Id; // Added
        private Double averageValue7;
        private String linguisticValue7Id; // Added
        private Double averageValue8;
        private String linguisticValue8Id; // Added
        private Double averageValue9;
        private String linguisticValue9Id; // Added
        private Double averageValue10;
        private String linguisticValue10Id; // Added

        public ReviewerRating() {}

        public ReviewerRating(Double averageValue1, Double averageValue2, Double averageValue3,
                              Double averageValue4, Double averageValue5, Double averageValue6,
                              Double averageValue7, Double averageValue8, Double averageValue9,
                              Double averageValue10,
                              String linguisticValue1Id, String linguisticValue2Id, String linguisticValue3Id,
                              String linguisticValue4Id, String linguisticValue5Id, String linguisticValue6Id,
                              String linguisticValue7Id, String linguisticValue8Id, String linguisticValue9Id,
                              String linguisticValue10Id) {
            this.averageValue1 = averageValue1;
            this.linguisticValue1Id = linguisticValue1Id;
            this.averageValue2 = averageValue2;
            this.linguisticValue2Id = linguisticValue2Id;
            this.averageValue3 = averageValue3;
            this.linguisticValue3Id = linguisticValue3Id;
            this.averageValue4 = averageValue4;
            this.linguisticValue4Id = linguisticValue4Id;
            this.averageValue5 = averageValue5;
            this.linguisticValue5Id = linguisticValue5Id;
            this.averageValue6 = averageValue6;
            this.linguisticValue6Id = linguisticValue6Id;
            this.averageValue7 = averageValue7;
            this.linguisticValue7Id = linguisticValue7Id;
            this.averageValue8 = averageValue8;
            this.linguisticValue8Id = linguisticValue8Id;
            this.averageValue9 = averageValue9;
            this.linguisticValue9Id = linguisticValue9Id;
            this.averageValue10 = averageValue10;
            this.linguisticValue10Id = linguisticValue10Id;
        }

        // Getters and Setters for all average values and their linguistic IDs
        public Double getAverageValue1() { return averageValue1; }
        public void setAverageValue1(Double averageValue1) { this.averageValue1 = averageValue1; }
        public String getLinguisticValue1Id() { return linguisticValue1Id; }
        public void setLinguisticValue1Id(String linguisticValue1Id) { this.linguisticValue1Id = linguisticValue1Id; }

        public Double getAverageValue2() { return averageValue2; }
        public void setAverageValue2(Double averageValue2) { this.averageValue2 = averageValue2; }
        public String getLinguisticValue2Id() { return linguisticValue2Id; }
        public void setLinguisticValue2Id(String linguisticValue2Id) { this.linguisticValue2Id = linguisticValue2Id; }

        public Double getAverageValue3() { return averageValue3; }
        public void setAverageValue3(Double averageValue3) { this.averageValue3 = averageValue3; }
        public String getLinguisticValue3Id() { return linguisticValue3Id; }
        public void setLinguisticValue3Id(String linguisticValue3Id) { this.linguisticValue3Id = linguisticValue3Id; }

        public Double getAverageValue4() { return averageValue4; }
        public void setAverageValue4(Double averageValue4) { this.averageValue4 = averageValue4; }
        public String getLinguisticValue4Id() { return linguisticValue4Id; }
        public void setLinguisticValue4Id(String linguisticValue4Id) { this.linguisticValue4Id = linguisticValue4Id; }

        public Double getAverageValue5() { return averageValue5; }
        public void setAverageValue5(Double averageValue5) { this.averageValue5 = averageValue5; }
        public String getLinguisticValue5Id() { return linguisticValue5Id; }
        public void setLinguisticValue5Id(String linguisticValue5Id) { this.linguisticValue5Id = linguisticValue5Id; }

        public Double getAverageValue6() { return averageValue6; }
        public void setAverageValue6(Double averageValue6) { this.averageValue6 = averageValue6; }
        public String getLinguisticValue6Id() { return linguisticValue6Id; }
        public void setLinguisticValue6Id(String linguisticValue6Id) { this.linguisticValue6Id = linguisticValue6Id; }

        public Double getAverageValue7() { return averageValue7; }
        public void setAverageValue7(Double averageValue7) { this.averageValue7 = averageValue7; }
        public String getLinguisticValue7Id() { return linguisticValue7Id; }
        public void setLinguisticValue7Id(String linguisticValue7Id) { this.linguisticValue7Id = linguisticValue7Id; }

        public Double getAverageValue8() { return averageValue8; }
        public void setAverageValue8(Double averageValue8) { this.averageValue8 = averageValue8; }
        public String getLinguisticValue8Id() { return linguisticValue8Id; }
        public void setLinguisticValue8Id(String linguisticValue8Id) { this.linguisticValue8Id = linguisticValue8Id; }

        public Double getAverageValue9() { return averageValue9; }
        public void setAverageValue9(Double averageValue9) { this.averageValue9 = averageValue9; }
        public String getLinguisticValue9Id() { return linguisticValue9Id; }
        public void setLinguisticValue9Id(String linguisticValue9Id) { this.linguisticValue9Id = linguisticValue9Id; }

        public Double getAverageValue10() { return averageValue10; }
        public void setAverageValue10(Double averageValue10) { this.averageValue10 = averageValue10; }
        public String getLinguisticValue10Id() { return linguisticValue10Id; }
        public void setLinguisticValue10Id(String linguisticValue10Id) { this.linguisticValue10Id = linguisticValue10Id; }
    }

    public Question() {
    }

    public Question(String idQuestion, String title, String description, QuestionType question_type, AnswerType answer_type,
                            String file_path, RPS rps, RPSDetail rps_detail, boolean is_rated,
                            ExamType examType, ExamType2 examType2, ExamType3 examType3, String explanation, String created_by,
                            List<CriteriaValue> criteria_values) {
        this.idQuestion = idQuestion;
        this.title = title;
        this.description = description;
        this.question_type = question_type;
        this.answer_type = answer_type;
        this.file_path = file_path;
        this.rps = rps;
        this.rps_detail = rps_detail;
        this.is_rated = is_rated;
        this.examType = examType;
        this.examType2 = examType2;
        this.examType3 = examType3;
        this.explanation = explanation;
        this.created_by = created_by;

        setCriteriaValues(criteria_values);
    }

    public String getIdQuestion() { return idQuestion; }
    public void setIdQuestion(String idQuestion) { this.idQuestion = idQuestion; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public QuestionType getQuestion_type() { return question_type; }
    public void setQuestion_type(QuestionType question_type) { this.question_type = question_type; }

    public AnswerType getAnswer_type() { return answer_type; }
    public void setAnswer_type(AnswerType answer_type) { this.answer_type = answer_type; }

    public String getFile_path() { return file_path; }
    public void setFile_path(String file_path) { this.file_path = file_path; }

    public RPS getRps() { return rps; }
    public void setRps(RPS rps) { this.rps = rps; }

    public RPSDetail getRps_detail() { return rps_detail; }
    public void setRps_detail(RPSDetail rps_detail) { this.rps_detail = rps_detail; }

    public ExamType getExamType() { return this.examType != null ? this.examType : ExamType.NOTHING; }
    public void setExamType(ExamType examType) { this.examType = examType; }

    public ExamType2 getExamType2() { return this.examType2 != null ? this.examType2 : ExamType2.NOTHING; }
    public void setExamType2(ExamType2 examType2) { this.examType2 = examType2; }

    public ExamType3 getExamType3() { return this.examType3 != null ? this.examType3 : ExamType3.NOTHING; }
    public void setExamType3(ExamType3 examType3) { this.examType3 = examType3; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getCreated_by() { return created_by; }
    public void setCreated_by(String created_by) { this.created_by = created_by; }

    public boolean isIs_rated() { return is_rated; }
    public void setIs_rated(boolean is_rated) { this.is_rated = is_rated; }

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
        this.criteria_values = null;
    }

    @JsonProperty("questionRating")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public QuestionRating getQuestionRating() {
        if (this.questionRating == null && this.idQuestion != null && this.questionRatingJson != null && !this.questionRatingJson.isEmpty()) {
            try {
                // Now, ReviewerRating itself has the linguistic IDs, so no special type needed here.
                this.questionRating = objectMapper.readValue(this.questionRatingJson, QuestionRating.class);
            } catch (JsonProcessingException e) {
                System.err.println("ERROR Question Model: Failed to deserialize questionRatingJson: " + e.getMessage());
                e.printStackTrace();
                this.questionRating = new QuestionRating(this.idQuestion);
            }
        } else if (this.questionRating == null) {
            this.questionRating = new QuestionRating(this.idQuestion);
        }
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
        this.questionRating = null;
    }

    public boolean isValid() {
        return this.idQuestion != null && this.title != null && this.description != null && this.question_type != null && this.answer_type != null;
    }
}