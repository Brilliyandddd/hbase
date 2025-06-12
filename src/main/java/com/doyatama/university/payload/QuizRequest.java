package com.doyatama.university.payload;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class QuizRequest {
    private String idQuiz;
    private String name;
    private String description;
    private List<String> questions; // This is correct for receiving IDs
    private String rps_id;
    private Integer min_grade;
    private Integer duration;
    private String message;
    private String developerId;
    private String coordinatorId;
    private String instructorId;
    private Instant date_start;
    private Instant date_end;
    private String type_quiz;

    public QuizRequest() {
    }

    public QuizRequest(String idQuiz,String name, String description, List<String> questions, String rps_id,String developerId,String coordinatorId,String instructorId, Integer min_grade, Integer duration,String message ,String type_quiz,Instant date_start, Instant date_end) {
        this.idQuiz = idQuiz;
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.rps_id = rps_id;
        this.min_grade = min_grade;
        this.duration = duration;
        this.message = message;
        this.developerId = developerId;
        this.coordinatorId = coordinatorId;
        this.instructorId = instructorId;
        this.type_quiz = type_quiz;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public String getIdQuiz() {
        return idQuiz;
    }
    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
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

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getRps_id() {
        return rps_id;
    }

    public void setRps_id(String rps_id) {
        this.rps_id = rps_id;
    }

    public Integer getMin_grade() {
        return min_grade;
    }

    public void setMin_grade(Integer min_grade) {
        this.min_grade = min_grade;
    }

    public String getDeveloperId(){
        return developerId;
    }

    public void setDeveloperId(String developerId){
        this.developerId = developerId;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }
    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }
    public String getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate_start() {
        return date_start;
    }

    public void setDate_start(Instant date_start) {
        this.date_start = date_start;
    }

    public Instant getDate_end() {
        return date_end;
    }

    public void setDate_end(Instant date_end) {
        this.date_end = date_end;
    }

    public String getType_quiz() {
        return type_quiz;
    }

    public void setType_quiz(String type_quiz) {
        this.type_quiz = type_quiz;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idQuiz":
                this.idQuiz = value;
                break;
            case "name":
                this.name = value;
                break;
            case "description":
                this.description = value;
                break;
            case "questions":
                // This converts a single string into a list, which might not be intended for a comma-separated list of IDs.
                // If "questions" in a set method is ever meant to take a comma-separated string,
                // you'd need to split it: `Arrays.asList(value.split(","))`.
                // However, for typical request body, `setQuestions(List<String> questions)` is called directly.
                this.questions = Collections.singletonList(value);
                break;
            case "rps_id":
                this.rps_id = value;
                break;
            case "min_grade":
                this.min_grade = Integer.parseInt(value);
                break;
            case "developerId":
                this.developerId = value;
                break;
            case "coordinatorId":
                this.coordinatorId = value;
                break;
            case "instructorId":
                this.instructorId = value;
                break;
            case "duration":
                this.duration = Integer.parseInt(value);
                break;
            case "message":
                this.message = value;
                break;
            case "type_quiz":
                this.type_quiz = value;
                break;
            case "date_start":
                this.date_start = Instant.parse(value);
                break;
            case "date_end":
                this.date_end = Instant.parse(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}