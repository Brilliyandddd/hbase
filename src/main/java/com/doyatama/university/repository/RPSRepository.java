package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.RPS;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.doyatama.university.model.LearningMedia;
import com.doyatama.university.model.Lecture;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Repository
public class RPSRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "rps";

    public List<RPS> findAll(int size) throws IOException {
    HBaseCustomClient client = new HBaseCustomClient(conf);
    TableName tableRPS = TableName.valueOf(tableName);
    Map<String, String> columnMapping = new HashMap<>();

    // Main fields
    columnMapping.put("id", "id");
    columnMapping.put("name", "name");
    columnMapping.put("sks", "sks");
    columnMapping.put("semester", "semester");
    columnMapping.put("cplProdi", "cplProdi");
    columnMapping.put("cplMk", "cplMk");

    // // Relational fields
    // columnMapping.put("studyProgram", "studyProgram");
    // columnMapping.put("idSubject", "idSubject");

    // // Learning Media (as List)
    // columnMapping.put("learningMedia:id", "learningMedia.id");
    // columnMapping.put("learningMedia:name", "learningMedia.name");
    // columnMapping.put("learningMedia:type", "learningMedia.type");

    // // Lecture (as List)
    // columnMapping.put("lecture:id", "lecture.id");
    // columnMapping.put("lecture:name", "lecture.name");

    return client.showListTable(tableRPS.toString(), columnMapping, RPS.class, size);
}

    public RPS findById(String rpsId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("sks", "sks");
        columnMapping.put("semester", "semester");
        columnMapping.put("cplProdi", "cplProdi");
        columnMapping.put("cplMk", "cplMk");
        columnMapping.put("created_at", "created_at");
        columnMapping.put("idProgramStudi", "idProgramStudi");
        columnMapping.put("idSubject", "idSubject");
        columnMapping.put("learning_media", "learning_media");
        columnMapping.put("lecture", "lecture");
        // columnMapping.put("ka_study_program", "ka_study_program");

        return client.showDataTable(tableUsers.toString(), columnMapping, rpsId, RPS.class);
    }

    public RPS save(RPS rps) throws IOException {
    HBaseCustomClient client = new HBaseCustomClient(conf);
    String rowKey = (rps.getId() != null && !rps.getId().isEmpty()) ? rps.getId() : UUID.randomUUID().toString().substring(0, 20);

    TableName tableRPS = TableName.valueOf(tableName);
    
    // Main data
    client.insertRecord(tableRPS, rowKey, "main", "id", rowKey);
    client.insertRecord(tableRPS, rowKey, "main", "name", rps.getName());
    client.insertRecord(tableRPS, rowKey, "main", "sks", String.valueOf(rps.getSks()));
    client.insertRecord(tableRPS, rowKey, "main", "semester", String.valueOf(rps.getSemester()));
    client.insertRecord(tableRPS, rowKey, "main", "cplProdi", rps.getCplProdi());
    client.insertRecord(tableRPS, rowKey, "main", "cplMk", rps.getCplMk());

    // Study Program (with null check)
    if (rps.getStudyProgram() != null) {
        client.insertRecord(tableRPS, rowKey, "study_program", "id", rps.getStudyProgram().getId());
        client.insertRecord(tableRPS, rowKey, "study_program", "name", rps.getStudyProgram().getName());
    }

    // Subject (with null check)
    if (rps.getSubject() != null) {
        client.insertRecord(tableRPS, rowKey, "subject", "id", rps.getSubject().getId());
        client.insertRecord(tableRPS, rowKey, "subject", "name", rps.getSubject().getName());
    }

    // Learning Media (with null check)
    if (rps.getLearningMedia() != null) {
        for (LearningMedia media : rps.getLearningMedia()) {
            if (media != null) {
                client.insertRecord(tableRPS, rowKey, "learning_media", "id", media.getId());
                client.insertRecord(tableRPS, rowKey, "learning_media", "name", media.getName());
                client.insertRecord(tableRPS, rowKey, "learning_media", "type", String.valueOf(media.getType()));
            }
        }
    }

    // Lecture (with null check)
    if (rps.getLecture() != null) {
        for (Lecture lecture : rps.getLecture()) {
            if (lecture != null) {
                client.insertRecord(tableRPS, rowKey, "lecture", "id", lecture.getId());
                client.insertRecord(tableRPS, rowKey, "lecture", "name", lecture.getName());
            }
        }
    }

    // Timestamp
    Instant instant = Instant.now();
    client.insertRecord(tableRPS, rowKey, "detail", "created_by", "Doyatama");
    client.insertRecord(tableRPS, rowKey, "detail", "created_at", instant.toString());

    return rps;
}

    public RPS update(String rpsId, RPS rps) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        // Gson gson = new Gson();
        TableName tableRPS = TableName.valueOf(tableName);

        client.insertRecord(tableRPS, rpsId, "main", "name", rps.getName());
        client.insertRecord(tableRPS, rpsId, "main", "sks", rps.getSks().toString());
        client.insertRecord(tableRPS, rpsId, "main", "semester", rps.getSemester().toString());
        client.insertRecord(tableRPS, rpsId, "main", "cplProdi", rps.getCplProdi());
        client.insertRecord(tableRPS, rpsId, "main", "cplMk", rps.getCplMk());

        client.insertRecord(tableRPS, rpsId, "study_program", "id", rps.getStudyProgram().getId());
        client.insertRecord(tableRPS, rpsId, "study_program", "name", rps.getStudyProgram().getName());
        client.insertRecord(tableRPS, rpsId, "subject", "id", rps.getSubject().getId());
        client.insertRecord(tableRPS, rpsId, "subject", "name", rps.getSubject().getName());

        for (LearningMedia media : rps.getLearningMedia()) {
            String mediaPrefix = "Learning_Media " + media.getType();
            client.insertRecord(tableRPS, rpsId, "learning_media", "id", media.getId());
            client.insertRecord(tableRPS, rpsId, "learning_media", "name", media.getName());
            client.insertRecord(tableRPS, rpsId, "learning_media", "type", media.getType().toString());
        }

        // client.insertRecord(tableRPS, rpsId, "learning_media", "id", rps.getLearningMedia().getId());
        // client.insertRecord(tableRPS, rpsId, "learning_media", "type", rps.getLearningMedia().getType().toString());
        // client.insertRecord(tableRPS, rpsId, "learning_media", "name", rps.getLearningMedia().getName());

        for (Lecture lecture : rps.getLecture()) {
            String lecturePrefix = "Lecture " + lecture.getName();
            client.insertRecord(tableRPS, rpsId, "lecture", "id", lecture.getId());
            client.insertRecord(tableRPS, rpsId, "lecture", "name", lecture.getName());
        }

        // client.insertRecord(tableRPS, rpsId, "lecture", "id", rps.getLecture().getId());
        // client.insertRecord(tableRPS, rpsId, "lecture", "name", rps.getLecture().getName());

        // client.insertRecord(tableRPS, rpsId, "main", "ka_study_program", rps.getKaStudyProgram());

        // Timestamp
        Instant instant = ZonedDateTime.now(ZoneId.of("Asia/Jakarta")).toInstant();
        client.insertRecord(tableRPS, rpsId, "detail", "created_by", "Doyatama");
        client.insertRecord(tableRPS, rpsId, "detail", "created_at", instant.toString());

        return rps;
    }

    public boolean deleteById(String rpsId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, rpsId);
        return true;
    }

    public RPS findByIdLecture(String rpsId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("lecture", "lecture");

        List<RPS> rpsList = client.showListTable(tableUsers.toString(), columnMapping, RPS.class, Integer.MAX_VALUE);
        return rpsList.stream()
                .filter(rps -> rps.getId().equals(rpsId))
                .findFirst()
                .orElse(null);
    }
}
