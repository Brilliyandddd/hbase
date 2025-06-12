package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.*;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author alfa
 */
public class CriteriaValueRepository {

    Configuration conf = HBaseConfiguration.create();
    String tableName = "criteria_values";

    public List<CriteriaValue> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(this.tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("value1", "value1");
        columnMapping.put("value2", "value2");
        columnMapping.put("value3", "value3");
        columnMapping.put("value4", "value4");
        columnMapping.put("value5", "value5");
        columnMapping.put("value6", "value6");
        columnMapping.put("value7", "value7");
        columnMapping.put("value8", "value8");
        columnMapping.put("value9", "value9");
        columnMapping.put("value10", "value10"); // Add value10 mapping
        columnMapping.put("user_id", "user_id");
        columnMapping.put("avgOfAvgValue10", "avgOfAvgValue10");
        columnMapping.put("question", "question");
        columnMapping.put("user", "user");
        columnMapping.put("team_teaching", "team_teaching");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("linguistic_value", "linguistic_value");

        return client.showListTable(tableUsers.toString(), columnMapping, CriteriaValue.class, size);
    }

    public List<CriteriaValue> findAllByQuestion(String questionId, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(this.tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("value1", "value1");
        columnMapping.put("value2", "value2");
        columnMapping.put("value3", "value3");
        columnMapping.put("value4", "value4");
        columnMapping.put("value5", "value5");
        columnMapping.put("value6", "value6");
        columnMapping.put("value7", "value7");
        columnMapping.put("value8", "value8");
        columnMapping.put("value9", "value9");
        columnMapping.put("value10", "value10"); // Add value10 mapping
        columnMapping.put("user_id", "user_id");
        columnMapping.put("avgOfAvgValue10", "avgOfAvgValue10");
        columnMapping.put("question", "question");
        columnMapping.put("user", "user");
        columnMapping.put("team_teaching", "team_teaching");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("linguistic_value", "linguistic_value");

        // Use the questionId as the filter and sort by id
        List<CriteriaValue> criteriaValue = client.getDataListByColumn(tableUsers.toString(), columnMapping, "question", "idQuestion", questionId, CriteriaValue.class, size);

        return criteriaValue;
    }

    public List<CriteriaValue> findByUser(String userID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(this.tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("value1", "value1");
        columnMapping.put("value2", "value2");
        columnMapping.put("value3", "value3");
        columnMapping.put("value4", "value4");
        columnMapping.put("value5", "value5");
        columnMapping.put("value6", "value6");
        columnMapping.put("value7", "value7");
        columnMapping.put("value8", "value8");
        columnMapping.put("value9", "value9");
        columnMapping.put("value10", "value10"); // Add value10 mapping
        columnMapping.put("user_id", "user_id");
        columnMapping.put("avgOfAvgValue10", "avgOfAvgValue10");
        columnMapping.put("question", "question");
        columnMapping.put("user", "user");
        columnMapping.put("team_teaching", "team_teaching");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("linguistic_value", "linguistic_value");
        
        return client.getDataListByColumn(tableUsers.toString(), columnMapping, "user", "id", userID, CriteriaValue.class, size);
    }

    public CriteriaValue save(CriteriaValue criteriaValue, String questionId) throws IOException {
        if (!criteriaValue.getQuestion().getIdQuestion().equals(questionId)) {
            throw new IllegalArgumentException("The provided questionId does not match the questionId of the CriteriaValue");
        }
        
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString().substring(0, 5);

        TableName tableCriteriaValue = TableName.valueOf(this.tableName);

        client.insertRecord(tableCriteriaValue, rowKey, "main", "id", rowKey);
        client.insertRecord(tableCriteriaValue, rowKey, "main", "user_id", criteriaValue.getUser());

        // Save question information
        client.insertRecord(tableCriteriaValue, rowKey, "question", "idQuestion", criteriaValue.getQuestion().getIdQuestion());
        client.insertRecord(tableCriteriaValue, rowKey, "question", "title", criteriaValue.getQuestion().getTitle());

        // Save team_teaching if exists
        if (criteriaValue.getTeamTeaching() != null) {
            client.insertRecord(tableCriteriaValue, rowKey, "team_teaching", "id", criteriaValue.getTeamTeaching().getId());
            client.insertRecord(tableCriteriaValue, rowKey, "team_teaching", "name", criteriaValue.getTeamTeaching().getName());
        }

        // Save lecture if exists
        if (criteriaValue.getLecture() != null) {
            client.insertRecord(tableCriteriaValue, rowKey, "lecture", "id", criteriaValue.getLecture().getId());
            client.insertRecord(tableCriteriaValue, rowKey, "lecture", "name", criteriaValue.getLecture().getName());
        }

        // Save each LinguisticValue with its average (including value10)
        for (int i = 1; i <= 10; i++) {
            LinguisticValue value = null;
            switch (i) {
                case 1: value = criteriaValue.getValue1(); break;
                case 2: value = criteriaValue.getValue2(); break;
                case 3: value = criteriaValue.getValue3(); break;
                case 4: value = criteriaValue.getValue4(); break;
                case 5: value = criteriaValue.getValue5(); break;
                case 6: value = criteriaValue.getValue6(); break;
                case 7: value = criteriaValue.getValue7(); break;
                case 8: value = criteriaValue.getValue8(); break;
                case 9: value = criteriaValue.getValue9(); break;
                case 10: value = criteriaValue.getValue10(); break;
            }
            if (value != null) {
                String valueKey = "value" + i;
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "id", value.getId());
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "name", value.getName());
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "value1", String.valueOf(value.getValue1()));
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "value2", String.valueOf(value.getValue2()));
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "value3", String.valueOf(value.getValue3()));
                client.insertRecord(tableCriteriaValue, rowKey, valueKey, "value4", String.valueOf(value.getValue4()));
            }
        }

        // Save avgOfAvgValue10 if calculated
        if (criteriaValue.getAvgOfAvgValue10() != null) {
            client.insertRecord(tableCriteriaValue, rowKey, "main", "avgOfAvgValue10", criteriaValue.getAvgOfAvgValue10());
        }

        // Set the generated ID back to the object
        criteriaValue.setId(rowKey);
        
        return criteriaValue;
    }

    public CriteriaValue findById(String criteriaValueId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableCriteriaValue = TableName.valueOf(this.tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("value1", "value1");
        columnMapping.put("value2", "value2");
        columnMapping.put("value3", "value3");
        columnMapping.put("value4", "value4");
        columnMapping.put("value5", "value5");
        columnMapping.put("value6", "value6");
        columnMapping.put("value7", "value7");
        columnMapping.put("value8", "value8");
        columnMapping.put("value9", "value9");
        columnMapping.put("value10", "value10"); // Add value10 mapping
        columnMapping.put("user_id", "user_id");
        columnMapping.put("avgOfAvgValue10", "avgOfAvgValue10");
        columnMapping.put("question", "question");
        columnMapping.put("team_teaching", "team_teaching");
        columnMapping.put("lecture", "lecture");
        columnMapping.put("linguistic_value", "linguistic_value");

        return client.showDataTable(tableCriteriaValue.toString(), columnMapping, criteriaValueId, CriteriaValue.class);
    }

    public CriteriaValue update(String criteriaValueId, CriteriaValue criteriaValue) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableCriteriaValue = TableName.valueOf(this.tableName);

        // Update basic information
        if (criteriaValue.getUser() != null) {
            client.insertRecord(tableCriteriaValue, criteriaValueId, "main", "user_id", criteriaValue.getUser());
        }

        // Update question information
        if (criteriaValue.getQuestion() != null) {
            client.insertRecord(tableCriteriaValue, criteriaValueId, "question", "idQuestion", criteriaValue.getQuestion().getIdQuestion());
            client.insertRecord(tableCriteriaValue, criteriaValueId, "question", "title", criteriaValue.getQuestion().getTitle());
        }

        // Update each LinguisticValue (including value10)
        for (int i = 1; i <= 10; i++) {
            LinguisticValue value = null;
            switch (i) {
                case 1: value = criteriaValue.getValue1(); break;
                case 2: value = criteriaValue.getValue2(); break;
                case 3: value = criteriaValue.getValue3(); break;
                case 4: value = criteriaValue.getValue4(); break;
                case 5: value = criteriaValue.getValue5(); break;
                case 6: value = criteriaValue.getValue6(); break;
                case 7: value = criteriaValue.getValue7(); break;
                case 8: value = criteriaValue.getValue8(); break;
                case 9: value = criteriaValue.getValue9(); break;
                case 10: value = criteriaValue.getValue10(); break;
            }
            if (value != null) {
                String valueKey = "value" + i;
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "id", value.getId());
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "name", value.getName());
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "value1", String.valueOf(value.getValue1()));
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "value2", String.valueOf(value.getValue2()));
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "value3", String.valueOf(value.getValue3()));
                client.insertRecord(tableCriteriaValue, criteriaValueId, valueKey, "value4", String.valueOf(value.getValue4()));
            }
        }

        // Update avgOfAvgValue10 if calculated
        if (criteriaValue.getAvgOfAvgValue10() != null) {
            client.insertRecord(tableCriteriaValue, criteriaValueId, "main", "avgOfAvgValue10", criteriaValue.getAvgOfAvgValue10());
        }

        return criteriaValue;
    }

    public boolean deleteById(String criteriaValueId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, criteriaValueId);
        return true;
    }

    public List<CriteriaValue> findAllById(List<String> criteriaValueIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(this.tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("value1", "value1");
        columnMapping.put("value2", "value2");
        columnMapping.put("value3", "value3");
        columnMapping.put("value4", "value4");
        columnMapping.put("value5", "value5");
        columnMapping.put("value6", "value6");
        columnMapping.put("value7", "value7");
        columnMapping.put("value8", "value8");
        columnMapping.put("value9", "value9");
        columnMapping.put("value10", "value10"); // Add value10 mapping
        columnMapping.put("user_id", "user_id");
        columnMapping.put("avgOfAvgValue10", "avgOfAvgValue10");
        columnMapping.put("question", "question");
        columnMapping.put("team_teaching", "team_teaching");
        columnMapping.put("lecture", "lecture");

        List<CriteriaValue> criteriaValues = new ArrayList<>();
        for (String criteriaValueId : criteriaValueIds) {
            CriteriaValue criteriaValue = client.showDataTable(table.toString(), columnMapping, criteriaValueId, CriteriaValue.class);
            if (criteriaValue != null) {
                criteriaValues.add(criteriaValue);
            }
        }
        return criteriaValues;
    }
}