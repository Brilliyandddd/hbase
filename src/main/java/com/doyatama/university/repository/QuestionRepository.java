package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper; // Make sure this is imported
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class QuestionRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuestionRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "questions";

    // Use ObjectMapper static from the Question model
    private static final ObjectMapper objectMapper = Question.getObjectMapper();

    // Helper method to deserialize rating and criteria (avoid code duplication)
    private void deserializeQuestionFields(Question question) {
        if (question == null) return;

        // Deserialisasi questionRatingJson ke transient field questionRating
        if (question.getQuestionRatingJson() != null && !question.getQuestionRatingJson().isEmpty()) {
            try {
                Question.QuestionRating rating = objectMapper.readValue(
                    question.getQuestionRatingJson(), Question.QuestionRating.class);
                question.setQuestionRating(rating);
                // logger.debug("Repository: Deserialized questionRating for ID: {}", question.getIdQuestion());
            } catch (Exception e) {
                logger.error("Repository: Gagal parsing questionRatingJson for ID {}: {}", question.getIdQuestion(), e.getMessage(), e);
            }
        }

        // Deserialisasi criteriaValuesJson ke transient field criteria_values
        if (question.getCriteriaValuesJson() != null && !question.getCriteriaValuesJson().isEmpty()) {
            try {
                List<CriteriaValue> values = objectMapper.readValue(
                    question.getCriteriaValuesJson(), new TypeReference<List<CriteriaValue>>() {});
                question.setCriteriaValues(values);
                // logger.debug("Repository: Deserialized criteriaValues for ID: {}", question.getIdQuestion());
            } catch (Exception e) {
                logger.error("Repository: Gagal parsing criteriaValuesJson for ID {}: {}", question.getIdQuestion(), e.getMessage(), e);
            }
        }
    }

    public List<Question> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail", "rps_detail");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated");
        columnMapping.put("criteria_values", "criteriaValuesJson");
        columnMapping.put("questionRating", "questionRatingJson"); // Map HBase column 'questionRating' to field 'questionRatingJson'

        List<Question> questions = client.showListTable(tableQuestions.toString(), columnMapping, Question.class, size);
        
        // CRITICAL: Ensure deserialization for each Question object fetched
        questions.forEach(this::deserializeQuestionFields);

        return questions;
    }

    public List<Question> findAllByRpsDetailId(String rpsDetailID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail", "rps_detail");
        columnMapping.put("exam_type", "exam_type"); 
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); 
        columnMapping.put("criteria_values", "criteriaValuesJson");
        columnMapping.put("questionRating", "questionRatingJson");

        List<Question> questions = client.getDataListByColumn(tableQuestions.toString(), columnMapping, "rps_detail", "id", rpsDetailID, Question.class, size);
        
        // CRITICAL: Ensure deserialization for each Question object fetched
        questions.forEach(this::deserializeQuestionFields);

        return questions;
    }

    public List<Question> findAllByRPS(String rpsID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail", "rps_detail");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); 
        columnMapping.put("criteria_values", "criteriaValuesJson");
        columnMapping.put("questionRating", "questionRatingJson");

        List<Question> questions = client.getDataListByColumn(tableQuestions.toString(), columnMapping, "rps", "idRps", rpsID, Question.class, size);
        
        // CRITICAL: Ensure deserialization for each Question object fetched
        questions.forEach(this::deserializeQuestionFields);

        return questions;
    }

    public List<Question> findAllByRPSType(String rpsID, String type_exercise, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail", "rps_detail");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("type_exercise", type_exercise); 
        columnMapping.put("is_rated", "is_rated"); 
        columnMapping.put("criteria_values", "criteriaValuesJson");
        columnMapping.put("questionRating", "questionRatingJson");

        List<Question> questions = client.getDataListByColumn(tableQuestions.toString(), columnMapping, "rps", "idRps", rpsID, Question.class, size);
        
        // CRITICAL: Ensure deserialization for each Question object fetched
        questions.forEach(this::deserializeQuestionFields);

        return questions;
    }

    public Question save(Question question) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        String rowKey = UUID.randomUUID().toString().substring(0, 20); // Generate a new row key
        TableName tableQuestion = TableName.valueOf(tableName);

        client.insertRecord(tableQuestion, rowKey, "main", "idQuestion", rowKey);
        client.insertRecord(tableQuestion, rowKey, "main", "title", question.getTitle());
        client.insertRecord(tableQuestion, rowKey, "main", "description", question.getDescription());
        client.insertRecord(tableQuestion, rowKey, "main", "question_type", question.getQuestion_type().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "answer_type", question.getAnswer_type().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType", question.getExamType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType2", question.getExamType2().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType3", question.getExamType3().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "explanation", question.getExplanation());
        client.insertRecord(tableQuestion, rowKey, "main", "is_rated", String.valueOf(question.isIs_rated())); 

        if (question.getCriteriaValuesJson() != null && !question.getCriteriaValuesJson().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", question.getCriteriaValuesJson());
        } else {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", "[]"); 
        }

        // IMPORTANT: Ensure questionRatingJson is always stored, even if initially empty
        if (question.getQuestionRatingJson() != null && !question.getQuestionRatingJson().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "questionRating", question.getQuestionRatingJson());
        } else {
            // Save an empty JSON object as default if no rating data is provided
            client.insertRecord(tableQuestion, rowKey, "main", "questionRating", "{}"); 
        }

        if (question.getFile_path() != null && !question.getFile_path().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "file_path", question.getFile_path());
        }
        client.insertRecord(tableQuestion, rowKey, "rps", "idRps", question.getRps().getIdRps());
        client.insertRecord(tableQuestion, rowKey, "rps", "nameRps", question.getRps().getNameRps());
        client.insertRecord(tableQuestion, rowKey, "rps_detail", "id", question.getRps_detail().getId());
        client.insertRecord(tableQuestion, rowKey, "rps_detail", "sub_cp_mk", question.getRps_detail().getSub_cp_mk());
        
        client.insertRecord(tableQuestion, rowKey, "rps_detail", "created_by", "Doyatama");
        
        // After saving to HBase, set the generated ID and ensure the object is fully deserialized
        question.setIdQuestion(rowKey); 
        deserializeQuestionFields(question); 
        return question;
    }

    public Question.QuestionRating saveQuestionRating(Question question) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        String rowKey = question.getIdQuestion();
        TableName tableQuestion = TableName.valueOf(tableName);

        try {
            if (question.getQuestionRatingJson() != null && !question.getQuestionRatingJson().isEmpty()) {
                logger.info("Saving rating JSON for {}: {}", rowKey, question.getQuestionRatingJson());
                client.insertRecord(tableQuestion, rowKey, "main", "questionRating", question.getQuestionRatingJson());
            } else {
                logger.warn("questionRatingJson is null or empty for question {}. Saving empty object.", rowKey);
                client.insertRecord(tableQuestion, rowKey, "main", "questionRating", "{}"); 
            }
            client.insertRecord(tableQuestion, rowKey, "main", "is_rated", String.valueOf(question.isIs_rated()));
        } catch (Exception e) {
            logger.error("Failed to save question rating for {}: {}", rowKey, e.getMessage(), e);
            throw new IOException("Failed to save question rating", e);
        }

        // After saving, ensure the returned object's transient fields are populated
        // This implicitly calls getQuestionRating() which triggers deserialization if needed.
        deserializeQuestionFields(question); // Call helper to re-hydrate after saving
        return question.getQuestionRating();
    }

    public Question findById(String questionId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuestions = TableName.valueOf(tableName);

        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated");
        columnMapping.put("criteria_values", "criteriaValuesJson");
        columnMapping.put("questionRating", "questionRatingJson"); 

        Question question = client.showDataTable(tableQuestions.toString(), columnMapping, questionId, Question.class);

        // CRITICAL: Apply deserialization helper after fetching from HBase
        deserializeQuestionFields(question); 

        return question;
    }

    public List<Question> findAllById(List<String> questionIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail", "rps_detail");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated");
        columnMapping.put("criteriaValuesJson", "criteria_values");
        columnMapping.put("questionRatingJson", "questionRating"); 

        List<Question> questions = new ArrayList<>();
        for (String questionId : questionIds) {
            Question question = client.showDataTable(table.toString(), columnMapping, questionId, Question.class);
            if (question != null) {
                deserializeQuestionFields(question); // Apply deserialization helper
                questions.add(question);
            }
        }
        return questions;
    }

    // In com.doyatama.university.repository.QuestionRepository.java

// ... (imports and class definition) ...

public Question update(String questionId, Question question) throws IOException {
    HBaseCustomClient client = new HBaseCustomClient(conf);
    String rowKey = question.getIdQuestion();
    TableName tableQuestion = TableName.valueOf(tableName);

    // Always insert the idQuestion/rowKey, as it's fundamental and should never be null
    // This call ensures there's always at least one column inserted.
    client.insertRecord(tableQuestion, rowKey, "main", "idQuestion", rowKey);

    // Conditionally insert other fields only if they are not null or empty
    if (question.getTitle() != null && !question.getTitle().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "title", question.getTitle());
    }
    if (question.getDescription() != null && !question.getDescription().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "description", question.getDescription());
    }
    if (question.getExplanation() != null && !question.getExplanation().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "explanation", question.getExplanation());
    }

    // For Enums: Check if the enum object itself is not null before calling .toString()
    if (question.getQuestion_type() != null) {
        client.insertRecord(tableQuestion, rowKey, "main", "question_type", question.getQuestion_type().toString());
    }
    if (question.getAnswer_type() != null) {
        client.insertRecord(tableQuestion, rowKey, "main", "answer_type", question.getAnswer_type().toString());
    }

    // Crucially for examType, examType2, examType3 which can be null from frontend/service
    if (question.getExamType() != null) { // This will be null if EXERCISE checkbox is not selected
        client.insertRecord(tableQuestion, rowKey, "main", "examType", question.getExamType().toString());
    } else {
        // Option to delete the column if it becomes null (HBase doesn't have true 'null' but can delete columns)
        // If your client.deleteColumn method exists and is suitable:
        // client.deleteColumn(tableQuestion, rowKey, "main", "examType");
        // Otherwise, simply don't insert/update it, letting its previous value persist if it existed.
        // For this problem, simply not calling insertRecord is enough.
    }
    if (question.getExamType2() != null) { // This will be null if QUIZ checkbox is not selected
        client.insertRecord(tableQuestion, rowKey, "main", "examType2", question.getExamType2().toString());
    } else {
        // client.deleteColumn(tableQuestion, rowKey, "main", "examType2");
    }
    if (question.getExamType3() != null) { // This will be null if EXAM checkbox is not selected
        client.insertRecord(tableQuestion, rowKey, "main", "examType3", question.getExamType3().toString());
    } else {
        // client.deleteColumn(tableQuestion, rowKey, "main", "examType3");
    }

    // Booleans will always convert to "true" or "false" strings, so no null check needed for isIs_rated()
    client.insertRecord(tableQuestion, rowKey, "main", "is_rated", String.valueOf(question.isIs_rated()));

    // JSON fields: already handled to insert empty JSON string if null/empty
    if (question.getCriteriaValuesJson() != null && !question.getCriteriaValuesJson().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", question.getCriteriaValuesJson());
    } else {
        client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", "[]");
    }

    if (question.getQuestionRatingJson() != null && !question.getQuestionRatingJson().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "questionRating", question.getQuestionRatingJson());
    } else {
        client.insertRecord(tableQuestion, rowKey, "main", "questionRating", "{}");
    }

    // File path
    if (question.getFile_path() != null && !question.getFile_path().isEmpty()) {
        client.insertRecord(tableQuestion, rowKey, "main", "file_path", question.getFile_path());
    }

    // Nested RPS object fields: Check if the object itself is not null
    if (question.getRps() != null) {
        if (question.getRps().getIdRps() != null && !question.getRps().getIdRps().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "rps", "idRps", question.getRps().getIdRps());
        }
        if (question.getRps().getNameRps() != null && !question.getRps().getNameRps().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "rps", "nameRps", question.getRps().getNameRps());
        }
    }

    // Nested RPS Detail object fields: Check if the object itself is not null
    if (question.getRps_detail() != null) {
        if (question.getRps_detail().getId() != null && !question.getRps_detail().getId().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "rps_detail", "id", question.getRps_detail().getId());
        }
        if (question.getRps_detail().getSub_cp_mk() != null && !question.getRps_detail().getSub_cp_mk().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "rps_detail", "sub_cp_mk", question.getRps_detail().getSub_cp_mk());
        }
    }

    // This fixed value will always be inserted, helping ensure at least one column is put
    client.insertRecord(tableQuestion, rowKey, "rps_detail", "created_by", "Doyatama");

    deserializeQuestionFields(question); // Apply deserialization helper
    // Consider adding client.close() here if HBaseCustomClient manages its own connection per operation
    // If client.insertRecord is closing connection, then don't add client.close() here.
    return question;
}

    public boolean deleteById(String questionId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, questionId);
        return true;
    }
}