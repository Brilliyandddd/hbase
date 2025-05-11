package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.SubAssessmentCriteria;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class SubAssessmentCriteriaRepository {
    private final Configuration conf = HBaseConfiguration.create();
    private final String tableName = "sub_assessment_criterias";

    private Map<String, String> getColumnMapping() {
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idSubAssessment", "idSubAssessment");
        columnMapping.put("name", "name");
        columnMapping.put("description", "description");
        columnMapping.put("weight", "weight");
        columnMapping.put("assessment_criterias" , "assessment_criterias");
        return columnMapping;
    }

    public List<SubAssessmentCriteria> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idSubAssessment", "idSubAssessment");
        columnMapping.put("name", "name");
        columnMapping.put("description", "description");
        columnMapping.put("weight", "weight");
        columnMapping.put("assessment_criterias" , "assessment_criterias");

        return client.showListTable(table.toString(), columnMapping, SubAssessmentCriteria.class, size);
    }

    public SubAssessmentCriteria save(SubAssessmentCriteria subAssessmentCriteria) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        String rowKey = UUID.randomUUID().toString();
        TableName table = TableName.valueOf(tableName);
        
        client.insertRecord(table, rowKey, "main", "idSubAssessment", rowKey);
        client.insertRecord(table, rowKey, "main", "name", subAssessmentCriteria.getName());
        client.insertRecord(table, rowKey, "main", "description", subAssessmentCriteria.getDescription());
        client.insertRecord(table, rowKey, "main", "weight", String.valueOf(subAssessmentCriteria.getWeight()));
        
        if (subAssessmentCriteria.getAssessmentCriteria() != null) {
            client.insertRecord(table, rowKey, "assessment_criterias", "id", String.valueOf(subAssessmentCriteria.getAssessmentCriteria().getId()));
            client.insertRecord(table, rowKey, "assessment_criterias", "name", subAssessmentCriteria.getAssessmentCriteria().getName());
        }
        
        client.insertRecord(table, rowKey, "detail", "created_by", "Doyatama");
        return subAssessmentCriteria;
    }

    public SubAssessmentCriteria findById(String subAssessmentCriteriaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableSubAssessmentCriteria = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idSubAssessment", "idSubAssessment");
        columnMapping.put("name", "name");
        columnMapping.put("description", "description");
        columnMapping.put("weight", "weight");
        columnMapping.put("assessment_criterias" , "assessment_criterias");

        return client.showDataTable(tableSubAssessmentCriteria.toString(), columnMapping, subAssessmentCriteriaId, SubAssessmentCriteria.class);
    }

    public SubAssessmentCriteria findSubAssessmentCriteriaById(String subAssessmentCriteriaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableSubAssessmentCriteria = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        columnMapping.put("idSubAssessment", "idSubAssessment");
        columnMapping.put("name", "name");
        columnMapping.put("description", "description");
        columnMapping.put("weight", "weight");
        columnMapping.put("assessment_criterias" , "assessment_criterias");

        return client.showDataTable(tableSubAssessmentCriteria.toString(), columnMapping, subAssessmentCriteriaId, SubAssessmentCriteria.class);
    }

    public SubAssessmentCriteria update(String subAssessmentCriteriaId, SubAssessmentCriteria subAssessmentCriteria) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);
        
        client.insertRecord(table, subAssessmentCriteriaId, "main", "name", subAssessmentCriteria.getName());
        client.insertRecord(table, subAssessmentCriteriaId, "main", "description", subAssessmentCriteria.getDescription());
        client.insertRecord(table, subAssessmentCriteriaId, "main", "weight", String.valueOf(subAssessmentCriteria.getWeight()));
    
        if (subAssessmentCriteria.getAssessmentCriteria() != null) {
            client.insertRecord(table, subAssessmentCriteriaId, "assessment_criterias", "id", (subAssessmentCriteria.getAssessmentCriteria().getId()));
            client.insertRecord(table, subAssessmentCriteriaId, "assessment_criterias", "name", subAssessmentCriteria.getAssessmentCriteria().getName());
        }
    
        return subAssessmentCriteria;
    }    

    public boolean deleteById(String subAssessmentCriteriaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, subAssessmentCriteriaId);
        return true;
    }

    public boolean existsById(String subAssessmentCriteriaId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName table = TableName.valueOf(tableName);
        
        SubAssessmentCriteria result = client.getDataByColumn(table.toString(), getColumnMapping(), "assessment_criterias", "id", subAssessmentCriteriaId, SubAssessmentCriteria.class);
        
        return result != null;
    }
}
