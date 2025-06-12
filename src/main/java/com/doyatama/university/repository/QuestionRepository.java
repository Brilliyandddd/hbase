package com.doyatama.university.repository;

import com.doyatama.university.controller.DepartmentController; // Periksa apakah ini masih diperlukan
import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.*;
import com.google.gson.Gson; // Masih diperlukan untuk beberapa objek, tapi tidak untuk List<Question>
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
// Hapus import Jackson ObjectMapper, JavaTimeModule, JsonInclude di sini
// Karena sekarang ObjectMapper dikelola di model Question itu sendiri
// import com.fasterxml.jackson.databind.ObjectMapper; 
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// import com.fasterxml.jackson.annotation.JsonInclude;


@Repository
public class QuestionRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuestionRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "questions";
    // Hapus atau komen out jika tidak digunakan
    // DepartmentController departmentController = new DepartmentController(); 
    private final Gson gson = new Gson(); // Masih diperlukan jika ada serialisasi lain

    public List<Question> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        // Mapping RPS dan RPSDetail jika disimpan sebagai String ID atau JSON String
        columnMapping.put("rps", "rps"); // Asumsi 'rps' adalah qualifier string untuk ID RPS
        columnMapping.put("rps_detail_id", "rps_detail_id"); // Asumsi 'rps_detail_id' adalah qualifier string untuk ID RPSDetail
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values"); // Map qualifier 'criteria_values' ke field 'criteriaValuesJson'
        // --- AKHIR NEW ---

        // Mapping untuk averageValue1-10 jika disimpan langsung di HBase
        // Jika averageValue1-10 juga didapat dari hasil parsing criteriaValuesJson, tidak perlu map langsung
        columnMapping.put("averageValue1", "averageValue1");
        columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3");
        columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5");
        columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7");
        columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9");
        columnMapping.put("averageValue10", "averageValue10");

        return client.showListTable(tableQuestions.toString(), columnMapping, Question.class, size);
    }

    public List<Question> findAllByRPSDetail(String rpsDetailID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail_id", "rps_detail_id");
        columnMapping.put("exam_type", "exam_type"); // Periksa apakah ini typo atau berbeda dengan examType
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values");
        // --- AKHIR NEW ---
        // Mapping untuk averageValue1-10
        columnMapping.put("averageValue1", "averageValue1"); columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3"); columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5"); columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7"); columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9"); columnMapping.put("averageValue10", "averageValue10");


        return client.getDataListByColumn(tableQuestions.toString(), columnMapping, "detail", "idQuestion", rpsDetailID, Question.class, size);
    }


    public List<Question> findAllByRPS(String rpsID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail_id", "rps_detail_id");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values");
        // --- AKHIR NEW ---
        // Mapping untuk averageValue1-10
        columnMapping.put("averageValue1", "averageValue1"); columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3"); columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5"); columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7"); columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9"); columnMapping.put("averageValue10", "averageValue10");

        return client.getDataListByColumn(tableQuestions.toString(), columnMapping, "detail", "idRps", rpsID, Question.class, size);
    }

    public List<Question> findAllByRPSType(String rpsID, String type_exercise, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail_id", "rps_detail_id");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("type_exercise", type_exercise);  // Add this line
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values");
        // --- AKHIR NEW ---
        // Mapping untuk averageValue1-10
        columnMapping.put("averageValue1", "averageValue1"); columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3"); columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5"); columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7"); columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9"); columnMapping.put("averageValue10", "averageValue10");


        return client.getDataListByColumn(tableQuestions.toString(), columnMapping, "detail", "idRps", rpsID, Question.class, size);
    }

    public Question save(Question question) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString().substring(0, 20);

        TableName tableQuestion = TableName.valueOf(tableName);
        client.insertRecord(tableQuestion, rowKey, "main", "idQuestion", rowKey);
        client.insertRecord(tableQuestion, rowKey, "main", "title", question.getTitle());
        client.insertRecord(tableQuestion, rowKey, "main", "description", question.getDescription());
        client.insertRecord(tableQuestion, rowKey, "main", "question_type", question.getQuestionType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "answer_type", question.getAnswerType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType", question.getExamType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType2", question.getExamType2().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType3", question.getExamType3().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "explanation", question.getExplanation());
        client.insertRecord(tableQuestion, rowKey, "main", "is_rated", String.valueOf(question.isIs_rated())); // NEW: Simpan is_rated

        // --- NEW: Simpan criteriaValuesJson ke HBase ---
        if (question.getCriteriaValuesJson() != null && !question.getCriteriaValuesJson().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", question.getCriteriaValuesJson());
        } else {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", "[]"); // Simpan array kosong jika tidak ada
        }
        // --- AKHIR NEW ---

        if (question.getFile_path() != null && !question.getFile_path().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "file_path", question.getFile_path());
        }
        client.insertRecord(tableQuestion, rowKey, "rps", "idRps", question.getRps().getIdRps());
        client.insertRecord(tableQuestion, rowKey, "rps", "nameRps", question.getRps().getNameRps());
        client.insertRecord(tableQuestion, rowKey, "detail", "id", question.getRps_detail_id().getId());
        client.insertRecord(tableQuestion, rowKey, "detail", "sub_cp_mk", question.getRps_detail_id().getSub_cp_mk());
        
        client.insertRecord(tableQuestion, rowKey, "detail", "created_by", "Doyatama");
        return question;
    }

    public Question findById(String questionId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableQuestions = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail_id", "rps_detail_id");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values");
        // --- AKHIR NEW ---
        // Mapping untuk averageValue1-10
        columnMapping.put("averageValue1", "averageValue1"); columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3"); columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5"); columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7"); columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9"); columnMapping.put("averageValue10", "averageValue10");


        return client.showDataTable(tableQuestions.toString(), columnMapping, questionId, Question.class);
    }


    public List<Question> findAllById(List<String> questionIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("idQuestion", "idQuestion");
        columnMapping.put("title", "title");
        columnMapping.put("description", "description");
        columnMapping.put("question_type", "question_type");
        columnMapping.put("answer_type", "answer_type");
        columnMapping.put("file_path", "file_path");
        columnMapping.put("rps", "rps");
        columnMapping.put("rps_detail_id", "rps_detail_id");
        columnMapping.put("examType", "examType");
        columnMapping.put("examType2", "examType2");
        columnMapping.put("examType3", "examType3");
        columnMapping.put("explanation", "explanation");
        columnMapping.put("is_rated", "is_rated"); // NEW: Tambahkan mapping untuk is_rated

        // --- NEW: Tambahkan mapping untuk JSON String dari criteria_values ---
        columnMapping.put("criteriaValuesJson", "criteria_values");
        // --- AKHIR NEW ---
        // Mapping untuk averageValue1-10
        columnMapping.put("averageValue1", "averageValue1"); columnMapping.put("averageValue2", "averageValue2");
        columnMapping.put("averageValue3", "averageValue3"); columnMapping.put("averageValue4", "averageValue4");
        columnMapping.put("averageValue5", "averageValue5"); columnMapping.put("averageValue6", "averageValue6");
        columnMapping.put("averageValue7", "averageValue7"); columnMapping.put("averageValue8", "averageValue8");
        columnMapping.put("averageValue9", "averageValue9"); columnMapping.put("averageValue10", "averageValue10");


        List<Question> questions = new ArrayList<>();
        for (String questionId : questionIds) {
            Question question = client.showDataTable(table.toString(), columnMapping, questionId, Question.class);
            questions.add(question);
        }

        return questions;
    }

    public Question update(String questionId ,Question question) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = question.getIdQuestion();

        TableName tableQuestion = TableName.valueOf(tableName);
        client.insertRecord(tableQuestion, rowKey, "main", "idQuestion", rowKey);
        client.insertRecord(tableQuestion, rowKey, "main", "title", question.getTitle());
        client.insertRecord(tableQuestion, rowKey, "main", "description", question.getDescription());
        client.insertRecord(tableQuestion, rowKey, "main", "question_type", question.getQuestionType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "answer_type", question.getAnswerType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType", question.getExamType().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType2", question.getExamType2().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "examType3", question.getExamType3().toString());
        client.insertRecord(tableQuestion, rowKey, "main", "explanation", question.getExplanation());
        client.insertRecord(tableQuestion, rowKey, "main", "is_rated", String.valueOf(question.isIs_rated())); // NEW: Simpan is_rated

        // --- NEW: Simpan criteriaValuesJson ke HBase ---
        if (question.getCriteriaValuesJson() != null && !question.getCriteriaValuesJson().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", question.getCriteriaValuesJson());
        } else {
            client.insertRecord(tableQuestion, rowKey, "main", "criteria_values", "[]"); // Simpan array kosong jika tidak ada
        }
        // --- AKHIR NEW ---

        if (question.getFile_path() != null && !question.getFile_path().isEmpty()) {
            client.insertRecord(tableQuestion, rowKey, "main", "file_path", question.getFile_path());
        }
        client.insertRecord(tableQuestion, rowKey, "rps", "idRps", question.getRps().getIdRps());
        client.insertRecord(tableQuestion, rowKey, "rps", "nameRps", question.getRps().getNameRps());
        client.insertRecord(tableQuestion, rowKey, "detail", "id", question.getRps_detail_id().getId());
        client.insertRecord(tableQuestion, rowKey, "detail", "sub_cp_mk", question.getRps_detail_id().getSub_cp_mk());
        
        client.insertRecord(tableQuestion, rowKey, "detail", "created_by", "Doyatama");
        return question;
    }

    public boolean deleteById(String questionId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, questionId);
        return true;
    }
}