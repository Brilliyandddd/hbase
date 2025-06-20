package com.doyatama.university.payload;

import com.doyatama.university.model.Question;
import com.doyatama.university.model.CriteriaValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponse {
    private String idQuestion;
    private String title;
    private String description;
    private String questionType;
    private String answerType;
    private String filePath;
    private boolean isRated;
    private String examType;
    private String examType2;
    private String examType3;
    private String explanation;

    // Type is now just Question.ReviewerRating, which includes linguistic IDs
    @JsonProperty("questionRating")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Question.ReviewerRating> reviewerRatings;

    @JsonProperty("criteriaValues")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CriteriaValue> criteriaValues;


    public QuestionResponse(Question question) {
        this.idQuestion = question.getIdQuestion();
        this.title = question.getTitle();
        this.description = question.getDescription();

        this.questionType = question.getQuestionType() != null ? question.getQuestionType().name() : null;
        this.answerType = question.getAnswerType() != null ? question.getAnswerType().name() : null;

        this.filePath = question.getFile_path();
        this.isRated = question.isIs_rated();

        this.examType = question.getExamType() != null ? question.getExamType().name() : null;
        this.examType2 = question.getExamType2() != null ? question.getExamType2().name() : null;
        this.examType3 = question.getExamType3() != null ? question.getExamType3().name() : null;
        this.explanation = question.getExplanation();

        if (question.getQuestionRating() != null && question.getQuestionRating().getReviewerRatings() != null) {
            // No type cast needed, as the map already holds ReviewerRating which now includes linguistic IDs
            this.reviewerRatings = question.getQuestionRating().getReviewerRatings();
        } else {
            this.reviewerRatings = new java.util.HashMap<>();
        }

        this.criteriaValues = question.getCriteriaValues();
    }

    public String getIdQuestion() { return idQuestion; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getQuestionType() { return questionType; }
    public String getAnswerType() { return answerType; }
    public String getFilePath() { return filePath; }
    public boolean getIsRated() { return isRated; }
    public String getExamType() { return examType; }
    public String getExamType2() { return examType2; }
    public String getExamType3() { return examType3; }
    public String getExplanation() { return explanation; }

    // Updated getter for reviewerRatings to return the new type
    public Map<String, Question.ReviewerRating> getReviewerRatings() { return reviewerRatings; }

    public List<CriteriaValue> getCriteriaValues() { return criteriaValues; }
}