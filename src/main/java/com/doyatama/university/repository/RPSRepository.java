package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.RPS;
import com.doyatama.university.model.RPSDetail;
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
    columnMapping.put("idRps", "idRps");
        columnMapping.put("nameRps", "nameRps");
        columnMapping.put("sks", "sks");
        columnMapping.put("semester", "semester");
        columnMapping.put("cplProdi", "cplProdi");
        columnMapping.put("cplMk", "cplMk");
        columnMapping.put("created_at", "created_at");
        columnMapping.put("studyProgram", "studyProgram");
        columnMapping.put("subject", "subject");
        columnMapping.put("learningMediaSoftware", "learningMediaSoftware");
        columnMapping.put("learningMediaHardware", "learningMediaHardware");
        columnMapping.put("developerLecturer", "developerLecturer");
        columnMapping.put("coordinatorLecturer", "coordinatorLecturer");
        columnMapping.put("instructorLecturer", "instructorLecturer");


    return client.showListTable(tableRPS.toString(), columnMapping, RPS.class, size);
}

    public RPS findById(String rpsId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idRps", "idRps");
        columnMapping.put("nameRps", "nameRps");
        columnMapping.put("sks", "sks");
        columnMapping.put("semester", "semester");
        columnMapping.put("cplProdi", "cplProdi");
        columnMapping.put("cplMk", "cplMk");
        columnMapping.put("created_at", "created_at");
        columnMapping.put("studyProgram", "studyProgram");
        columnMapping.put("subject", "subject");
        columnMapping.put("learningMediaSoftware", "learningMediaSoftware");
        columnMapping.put("learningMediaHardware", "learningMediaHardware");
        columnMapping.put("developerLecturer", "developerLecturer");
        columnMapping.put("coordinatorLecturer", "coordinatorLecturer");
        columnMapping.put("instructorLecturer", "instructorLecturer");

        return client.showDataTable(tableUsers.toString(), columnMapping, rpsId, RPS.class);
    }

    public RPS save(RPS rps) throws IOException {
    HBaseCustomClient client = new HBaseCustomClient(conf);
    String rowKey = rps.getIdRps();
    System.out.println("id repo" + rps.getIdRps());

    TableName tableRPS = TableName.valueOf(tableName);

    System.out.println("developer " + rps.getDeveloperLecturer());
    System.out.println("coordinator " + rps.getCoordinatorLecturer());
    System.out.println("instructor " + rps.getInstructorLecturer());
    System.out.println("Software " + rps.getLearningMediaSoftware());
    System.out.println("Hardware " + rps.getLearningMediaHardware());
    
    // Main data
    client.insertRecord(tableRPS, rowKey, "main", "idRps", rps.getIdRps());
    client.insertRecord(tableRPS, rowKey, "main", "nameRps", rps.getNameRps());
    client.insertRecord(tableRPS, rowKey, "main", "sks", String.valueOf(rps.getSks()));
    client.insertRecord(tableRPS, rowKey, "main", "semester", String.valueOf(rps.getSemester()));
    client.insertRecord(tableRPS, rowKey, "main", "cplProdi", rps.getCplProdi());
    client.insertRecord(tableRPS, rowKey, "main", "cplMk", rps.getCplMk());

    // Study Program (with null check)
    if (rps.getStudyProgram() != null) {
        client.insertRecord(tableRPS, rowKey, "studyProgram", "id", rps.getStudyProgram().getId());
        client.insertRecord(tableRPS, rowKey, "studyProgram", "name", rps.getStudyProgram().getName());
    }

    // Subject (with null check)
    if (rps.getSubject() != null) {
        client.insertRecord(tableRPS, rowKey, "subject", "id", rps.getSubject().getId());
        client.insertRecord(tableRPS, rowKey, "subject", "name", rps.getSubject().getName());
    }

    if (rps.getLearningMediaSoftware() != null) {
        client.insertRecord(tableRPS, rowKey, "learningMediaSoftware", "id", rps.getLearningMediaSoftware().getId());
        client.insertRecord(tableRPS, rowKey, "learningMediaSoftware", "name", rps.getLearningMediaSoftware().getName());
        client.insertRecord(tableRPS, rowKey, "learningMediaSoftware", "type", String.valueOf(rps.getLearningMediaSoftware().getType()));
    }

    if (rps.getLearningMediaHardware() != null) {
        client.insertRecord(tableRPS, rowKey, "learningMediaHardware", "id", rps.getLearningMediaHardware().getId());
        client.insertRecord(tableRPS, rowKey, "learningMediaHardware", "name", rps.getLearningMediaHardware().getName());
        client.insertRecord(tableRPS, rowKey, "learningMediaHardware", "type", String.valueOf(rps.getLearningMediaHardware().getType()));
    }

    if (rps.getDeveloperLecturer() != null) {
        client.insertRecord(tableRPS, rowKey, "developerLecturer", "id", rps.getDeveloperLecturer().getId());
        client.insertRecord(tableRPS, rowKey, "developerLecturer", "name", rps.getDeveloperLecturer().getName());
    }
    if (rps.getCoordinatorLecturer() != null) {
        client.insertRecord(tableRPS, rowKey, "coordinatorLecturer", "id", rps.getCoordinatorLecturer().getId());
        client.insertRecord(tableRPS, rowKey, "coordinatorLecturer", "name", rps.getCoordinatorLecturer().getName());
    }
    if (rps.getInstructorLecturer() != null) {
        client.insertRecord(tableRPS, rowKey, "instructorLecturer", "id", rps.getInstructorLecturer().getId());
        client.insertRecord(tableRPS, rowKey, "instructorLecturer", "name", rps.getInstructorLecturer().getName());
    }

    // if (rps.getRoleLecturers() != null && !rps.getRoleLecturers().isEmpty()) {
    //     for (Map.Entry<String, Lecture> entry : rps.getRoleLecturers().entrySet()) {
    //         String role = entry.getKey();       // developer, coordinator, or instructor
    //         Lecture lecture = entry.getValue(); // the corresponding Lecture object
            
    //         if (lecture != null) {
    //             // Save data with role as part of the qualifier
    //             client.insertRecord(tableRPS, rowKey, "lecture", role + "_id", lecture.getId());
    //             client.insertRecord(tableRPS, rowKey, "lecture", role + "_name", lecture.getName());
                
    //             // Add more fields if needed
    //             if (lecture.getNidn() != null) {
    //                 client.insertRecord(tableRPS, rowKey, "lecture", role + "_nidn", lecture.getNidn());
    //             }
                
    //             if (lecture.getStudyProgram() != null) {
    //                 client.insertRecord(tableRPS, rowKey, "lecture", role + "_study_program", lecture.getStudyProgram().getName());
    //             }
    //         }
    //     }
    // }

    // Timestamp
    Instant instant = Instant.now();
    client.insertRecord(tableRPS, rowKey, "detail", "created_by", "Doyatama");
    client.insertRecord(tableRPS, rowKey, "detail", "created_at", instant.toString());

    return rps;
}

    public RPS update(String idRps, RPS rps) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        // Gson gson = new Gson();
        TableName tableRPS = TableName.valueOf(tableName);

        System.out.println("Data edit " + rps);

        client.insertRecord(tableRPS, idRps, "main", "nameRps", rps.getNameRps());
        client.insertRecord(tableRPS, idRps, "main", "sks", rps.getSks().toString());
        client.insertRecord(tableRPS, idRps, "main", "semester", rps.getSemester().toString());
        client.insertRecord(tableRPS, idRps, "main", "cplProdi", rps.getCplProdi());
        client.insertRecord(tableRPS, idRps, "main", "cplMk", rps.getCplMk());

        if(rps.getStudyProgram() !=null){
            client.insertRecord(tableRPS, idRps, "studyProgram", "id", rps.getStudyProgram().getId());
            client.insertRecord(tableRPS, idRps, "studyProgram", "name", rps.getStudyProgram().getName());
        }

        if(rps.getSubject() !=null){
            client.insertRecord(tableRPS, idRps, "subject", "id", rps.getSubject().getId());
            client.insertRecord(tableRPS, idRps, "subject", "name", rps.getSubject().getName());
        }

        if (rps.getLearningMediaSoftware() != null) {
            client.insertRecord(tableRPS, idRps, "learningMediaSoftware", "id", rps.getLearningMediaSoftware().getId());
            client.insertRecord(tableRPS, idRps, "learningMediaSoftware", "name", rps.getLearningMediaSoftware().getName());
            client.insertRecord(tableRPS, idRps, "learningMediaSoftware", "type", String.valueOf(rps.getLearningMediaSoftware().getType()));
        }
        if (rps.getLearningMediaHardware() != null) {
            client.insertRecord(tableRPS, idRps, "learningMediaHardware", "id", rps.getLearningMediaHardware().getId());
            client.insertRecord(tableRPS, idRps, "learningMediaHardware", "name", rps.getLearningMediaHardware().getName());
            client.insertRecord(tableRPS, idRps, "learningMediaHardware", "type", String.valueOf(rps.getLearningMediaHardware().getType()));
        }
    

        if (rps.getDeveloperLecturer() != null) {
            client.insertRecord(tableRPS, idRps, "developerLecturer", "id", rps.getDeveloperLecturer().getId());
            client.insertRecord(tableRPS, idRps, "developerLecturer", "name", rps.getDeveloperLecturer().getName());
        }
        if (rps.getCoordinatorLecturer() != null) {
            client.insertRecord(tableRPS, idRps, "coordinatorLecturer", "id", rps.getCoordinatorLecturer().getId());
            client.insertRecord(tableRPS, idRps, "coordinatorLecturer", "name", rps.getCoordinatorLecturer().getName());
        }
        if (rps.getInstructorLecturer() != null) {
            client.insertRecord(tableRPS, idRps, "instructorLecturer", "id", rps.getInstructorLecturer().getId());
            client.insertRecord(tableRPS, idRps, "instructorLecturer", "name", rps.getInstructorLecturer().getName());
        }
        // Timestamp
        Instant instant = ZonedDateTime.now(ZoneId.of("Asia/Jakarta")).toInstant();
        client.insertRecord(tableRPS, idRps, "detail", "created_by", "Doyatama");
        client.insertRecord(tableRPS, idRps, "detail", "created_at", instant.toString());

        return rps;
    }

    public boolean deleteById(String idRps) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, idRps);
        return true;
    }

    public RPS findByIdLecture(String idRps) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idRps", "idRps");
        columnMapping.put("name", "name");
        columnMapping.put("lecture", "lecture");

        List<RPS> rpsList = client.showListTable(tableUsers.toString(), columnMapping, RPS.class, Integer.MAX_VALUE);
        return rpsList.stream()
                .filter(rps -> rps.getIdRps().equals(idRps))
                .findFirst()
                .orElse(null);
    }
}
