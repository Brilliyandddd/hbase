package com.doyatama.university.repository;

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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*; // Import semua dari java.util
import java.util.stream.Collectors; // Tambahkan import ini jika belum ada

@Repository
public class QuizRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuizRepository.class);
    Configuration conf = HBaseConfiguration.create();
    String tableName = "quizzes";
    private String lastSavedRowKey;
    private final Gson gson = new Gson();

    public List<Quiz> findAll(int size) throws IOException {
        TableName tableQuizzes = TableName.valueOf(tableName);

        List<Quiz> quizzes = new ArrayList<>();
        org.apache.hadoop.hbase.client.Connection connection = null;
        org.apache.hadoop.hbase.client.Table table = null;
        try {
            connection = org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(conf);
            table = connection.getTable(tableQuizzes);
            org.apache.hadoop.hbase.client.Scan scan = new org.apache.hadoop.hbase.client.Scan();
            scan.setCaching(size);
            org.apache.hadoop.hbase.client.ResultScanner scanner = table.getScanner(scan);

            for (Result result : scanner) {
                Quiz quiz = new Quiz();
                quiz.setIdQuiz(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("idQuiz"))));
                quiz.setName(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("name"))));
                quiz.setDescription(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("description"))));
                
                String minGradeStr = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("min_grade")));
                if (minGradeStr != null && !minGradeStr.isEmpty()) {
                    quiz.setMin_grade(Integer.parseInt(minGradeStr));
                }

                String durationStr = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("duration")));
                if (durationStr != null && !durationStr.isEmpty()) {
                    quiz.setDuration(Integer.parseInt(durationStr));
                }

                quiz.setDate_start(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("date_start"))));
                quiz.setDate_end(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("date_end"))));
                quiz.setDeveloperId(Bytes.toString(result.getValue(Bytes.toBytes("developerLecturer"), Bytes.toBytes("developerId"))));
                quiz.setCoordinatorId(Bytes.toString(result.getValue(Bytes.toBytes("coordinatorLecturer"), Bytes.toBytes("coordinatorId"))));
                quiz.setInstructorId(Bytes.toString(result.getValue(Bytes.toBytes("instructorLecturer"), Bytes.toBytes("instructorId"))));
                quiz.setMessage(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("message"))));
                quiz.setType_quiz(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("type_quiz"))));
                quiz.setCreated_at(Bytes.toString(result.getValue(Bytes.toBytes("detail"), Bytes.toBytes("created_at"))));

                // Manually retrieve and deserialize RPS object
                String rpsId = Bytes.toString(result.getValue(Bytes.toBytes("rps"), Bytes.toBytes("idRps")));
                String rpsName = Bytes.toString(result.getValue(Bytes.toBytes("rps"), Bytes.toBytes("nameRps")));
                if (rpsId != null) {
                    RPS rps = new RPS();
                    rps.setIdRps(rpsId);
                    rps.setNameRps(rpsName);
                    quiz.setRps(rps);
                }

                // --- PERUBAHAN UTAMA DI SINI (Baca questionsRaw) ---
                String questionsRaw = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("questions")));
                quiz.setQuestionsRaw(questionsRaw); // Set string mentah ke model
                // getQuestions() di model akan memparsing questionsRaw ke List<String> ID
                // Frontend akan memanggil API terpisah untuk mendapatkan objek Question penuh
                // --- AKHIR PERUBAHAN UTAMA ---

                quizzes.add(quiz);
            }
        } finally {
            if (table != null) table.close();
            if (connection != null) connection.close();
        }
        return quizzes;
    }

    public Quiz save(Quiz quiz) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        lastSavedRowKey = UUID.randomUUID().toString();
        TableName tableQuiz = TableName.valueOf(tableName);

        saveCommonAttributes(quiz, client, lastSavedRowKey, tableQuiz);

        // --- PERUBAHAN UTAMA DI SINI (Simpan questionsRaw) ---
        // Simpan questionsRaw sebagai String ke Column Family "main"
        if (quiz.getQuestionsRaw() != null && !quiz.getQuestionsRaw().isEmpty()) {
            client.insertRecord(tableQuiz, lastSavedRowKey, "main", "questions", quiz.getQuestionsRaw());
        } else {
            // Simpan string kosong jika tidak ada pertanyaan
            client.insertRecord(tableQuiz, lastSavedRowKey, "main", "questions", ""); 
        }
        // --- AKHIR PERUBAHAN UTAMA ---
        
        quiz.setIdQuiz(lastSavedRowKey);
        return quiz;
    }

    public String getLastSavedRowKey() {
        return lastSavedRowKey;
    }

    private void saveCommonAttributes(Quiz quiz, HBaseCustomClient client, String rowKey, TableName tableQuiz) throws IOException {
        if (quiz.getIdQuiz() == null) {
            quiz.setIdQuiz(rowKey);
        }
        client.insertRecord(tableQuiz, rowKey, "main", "idQuiz", quiz.getIdQuiz());
        client.insertRecord(tableQuiz, rowKey, "main", "name", quiz.getName());
        client.insertRecord(tableQuiz, rowKey, "main", "description", quiz.getDescription());
        client.insertRecord(tableQuiz, rowKey, "main", "min_grade", quiz.getMin_grade() != null ? quiz.getMin_grade().toString() : "");
        client.insertRecord(tableQuiz, rowKey, "main", "duration", quiz.getDuration() != null ? quiz.getDuration().toString() : "");
        
        client.insertRecord(tableQuiz, rowKey, "main", "date_start", quiz.getDate_start());
        client.insertRecord(tableQuiz, rowKey, "main", "date_end", quiz.getDate_end());

        if (quiz.getDeveloperId() != null) {
            client.insertRecord(tableQuiz, rowKey, "developerLecturer", "developerId", quiz.getDeveloperId());
        }
        if (quiz.getCoordinatorId() != null) {
            client.insertRecord(tableQuiz, rowKey, "coordinatorLecturer", "coordinatorId", quiz.getCoordinatorId());
        }
        if (quiz.getInstructorId() != null) {
            client.insertRecord(tableQuiz, rowKey, "instructorLecturer", "instructorId", quiz.getInstructorId());
        }

        client.insertRecord(tableQuiz, rowKey, "main", "message", quiz.getMessage());
        
        if (quiz.getRps() != null) {
            client.insertRecord(tableQuiz, rowKey, "rps", "idRps", quiz.getRps().getIdRps());
            client.insertRecord(tableQuiz, rowKey, "rps", "nameRps", quiz.getRps().getNameRps());
        }

        client.insertRecord(tableQuiz, rowKey, "main", "type_quiz", quiz.getType_quiz());

        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        client.insertRecord(tableQuiz, rowKey, "detail", "created_by", "Doyatama");
        client.insertRecord(tableQuiz, rowKey, "detail", "created_at", instant.toString());
    }

    public Quiz findById(String quizId) throws IOException {
        TableName tableQuizzes = TableName.valueOf(tableName);
        logger.info("QuizRepository: Mencoba menemukan kuis dengan ID: {}", quizId);

        org.apache.hadoop.hbase.client.Connection connection = null;
        org.apache.hadoop.hbase.client.Table table = null;
        Quiz quiz = null;
        try {
            connection = org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(conf);
            table = connection.getTable(tableQuizzes);
            org.apache.hadoop.hbase.client.Get get = new org.apache.hadoop.hbase.client.Get(Bytes.toBytes(quizId));
            Result result = table.get(get);

            if (!result.isEmpty()) {
                quiz = new Quiz();
                quiz.setIdQuiz(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("idQuiz"))));
                quiz.setName(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("name"))));
                quiz.setDescription(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("description"))));
                
                String minGradeStr = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("min_grade")));
                if (minGradeStr != null && !minGradeStr.isEmpty()) {
                    quiz.setMin_grade(Integer.parseInt(minGradeStr));
                }

                String durationStr = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("duration")));
                if (durationStr != null && !durationStr.isEmpty()) {
                    quiz.setDuration(Integer.parseInt(durationStr));
                }

                quiz.setDate_start(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("date_start"))));
                quiz.setDate_end(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("date_end"))));
                quiz.setDeveloperId(Bytes.toString(result.getValue(Bytes.toBytes("developerLecturer"), Bytes.toBytes("developerId"))));
                quiz.setCoordinatorId(Bytes.toString(result.getValue(Bytes.toBytes("coordinatorLecturer"), Bytes.toBytes("coordinatorId"))));
                quiz.setInstructorId(Bytes.toString(result.getValue(Bytes.toBytes("instructorLecturer"), Bytes.toBytes("instructorId"))));
                quiz.setMessage(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("message"))));
                quiz.setType_quiz(Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("type_quiz"))));
                quiz.setCreated_at(Bytes.toString(result.getValue(Bytes.toBytes("detail"), Bytes.toBytes("created_at"))));

                // Manually retrieve and deserialize RPS object
                String rpsId = Bytes.toString(result.getValue(Bytes.toBytes("rps"), Bytes.toBytes("idRps")));
                String rpsName = Bytes.toString(result.getValue(Bytes.toBytes("rps"), Bytes.toBytes("nameRps")));
                if (rpsId != null) {
                    RPS rps = new RPS();
                    rps.setIdRps(rpsId);
                    rps.setNameRps(rpsName);
                    quiz.setRps(rps);
                }

                // --- PERUBAHAN UTAMA DI SINI (Baca questionsRaw) ---
                String questionsRaw = Bytes.toString(result.getValue(Bytes.toBytes("main"), Bytes.toBytes("questions")));
                quiz.setQuestionsRaw(questionsRaw); // Set string mentah ke model
                // getQuestions() di model akan memparsing questionsRaw ke List<String>
                // Frontend akan memanggil API terpisah untuk mendapatkan objek Question penuh
                // --- AKHIR PERUBAHAN UTAMA ---

            }
        } finally {
            if (table != null) table.close();
            if (connection != null) connection.close();
        }
        return quiz;
    }

    public Quiz findAnswer(String quizId) throws IOException {
        // This method appears to be a duplicate of findById based on its current implementation.
        // It will return a Quiz object with populated questions and their answers (if present in Question model).
        return findById(quizId);
    }

    public Quiz update(String quizId, Quiz quiz) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableQuiz = TableName.valueOf(tableName);

        // Update common attributes
        client.insertRecord(tableQuiz, quizId, "main", "name", quiz.getName());
        client.insertRecord(tableQuiz, quizId, "main", "description", quiz.getDescription());
        client.insertRecord(tableQuiz, quizId, "main", "min_grade", quiz.getMin_grade() != null ? quiz.getMin_grade().toString() : "");
        client.insertRecord(tableQuiz, quizId, "main", "duration", quiz.getDuration() != null ? quiz.getDuration().toString() : "");
        client.insertRecord(tableQuiz, quizId, "main", "date_start", quiz.getDate_start());
        client.insertRecord(tableQuiz, quizId, "main", "date_end", quiz.getDate_end());
        client.insertRecord(tableQuiz, quizId, "main", "message", quiz.getMessage());
        client.insertRecord(tableQuiz, quizId, "main", "type_quiz", quiz.getType_quiz());

        if (quiz.getDeveloperId() != null) {
            client.insertRecord(tableQuiz, quizId, "developerLecturer", "developerId", quiz.getDeveloperId());
        }
        if (quiz.getCoordinatorId() != null) {
            client.insertRecord(tableQuiz, quizId, "coordinatorLecturer", "coordinatorId", quiz.getCoordinatorId());
        }
        if (quiz.getInstructorId() != null) {
            client.insertRecord(tableQuiz, quizId, "instructorLecturer", "instructorId", quiz.getInstructorId());
        }

        if (quiz.getRps() != null) {
            client.insertRecord(tableQuiz, quizId, "rps", "idRps", quiz.getRps().getIdRps());
            client.insertRecord(tableQuiz, quizId, "rps", "nameRps", quiz.getRps().getNameRps());
        }

        // --- PERUBAHAN UTAMA DI SINI (Simpan questionsRaw saat update) ---
        // Simpan questionsRaw sebagai String saat update
        if (quiz.getQuestionsRaw() != null && !quiz.getQuestionsRaw().isEmpty()) {
            client.insertRecord(tableQuiz, quizId, "main", "questions", quiz.getQuestionsRaw());
        } else {
            // Jika daftar pertanyaan kosong, simpan string kosong untuk meng-overwrite data lama
            client.insertRecord(tableQuiz, quizId, "main", "questions", ""); 
        }
        // --- AKHIR PERUBAHAN UTAMA ---

        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        client.insertRecord(tableQuiz, quizId, "detail", "created_by", "Doyatama");
        client.insertRecord(tableQuiz, quizId, "detail", "created_at", instant.toString()); // Ini mungkin harus created_at/updated_at yang sesuai
        return quiz;
    }

    public boolean deleteById(String quizId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, quizId);
        return true;
    }
}