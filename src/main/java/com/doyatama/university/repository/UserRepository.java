package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

@Repository
public class UserRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "users";

    private Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/namadatabase";
    String username = "root";
    String password = "passwordmu";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // pastikan drivernya ada
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    return DriverManager.getConnection(url, username, password);
}

    public List<User> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");
        return client.showListTable(tableUsers.toString(), columnMapping, User.class, size);
    }

    public List<User> findUsersNotUsedInLectures(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");

        // Get the list of all users
        List<User> allUsers = client.showListTable(tableUsers.toString(), columnMapping, User.class, size);

        // Get the list of all user IDs that have been used in lectures
        Set<String> userIdsInLectures = new HashSet<>();
        Scan scan = new Scan();
        ResultScanner scanner = client.getTable("lectures").getScanner(scan);
        for (Result result : scanner) {
            byte[] userIdBytes = result.getValue(Bytes.toBytes("user"), Bytes.toBytes("id"));
            if (userIdBytes != null) {
                String userId = Bytes.toString(userIdBytes);
                userIdsInLectures.add(userId);
            }
        }
        scanner.close();

        // Find all users that have not been used in any lectures
        List<User> unusedUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (!userIdsInLectures.contains(user.getId())) {
                unusedUsers.add(user);
            }
        }

        return unusedUsers;
    }

    public User findByUsername(String username) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");

        return client.getDataByColumn(tableUsers.toString(), columnMapping, "main", "username", username, User.class);
    }

    public User findById(String id) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");

        return client.showDataTable(tableUsers.toString(), columnMapping, id, User.class);
    }

    public boolean existsByUsername(String username) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");

        User user = client.getDataByColumn(tableUsers.toString(), columnMapping, "main", "username", username, User.class);
        if(user.getUsername() != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean existsByEmail(String email) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("name", "name");
        columnMapping.put("username", "username");
        columnMapping.put("email", "email");
        columnMapping.put("password", "password");
        columnMapping.put("roles", "roles");

        User user = client.getDataByColumn(tableUsers.toString(), columnMapping, "main", "email", email, User.class);
        if(user.getEmail() != null){
            return true;
        }else{
            return false;
        }
    }
    public void deleteById(String id) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
    
        client.deleteRecord(tableUsers.toString(), id);
    }
    

    public User save(User user) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableUsers = TableName.valueOf(tableName);
    
        // Ambil hanya ID pengguna
        List<User> allUsers = findAll(9999);
        int maxId = 0;
    
        for (User u : allUsers) {
            String id = u.getId(); // contoh: USR001
            if (id != null && id.startsWith("USR")) {
                try {
                    int numericPart = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, numericPart);
                } catch (NumberFormatException ignored) {}
            }
        }
    
        int nextId = maxId + 1;
        String newId = String.format("USR%03d", nextId);
        user.setId(newId);
    
        // Set createdAt jika null
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(Instant.now());
        }
    
        client.insertRecord(tableUsers, newId, "main", "id", newId);
        client.insertRecord(tableUsers, newId, "main", "name", user.getName());
        client.insertRecord(tableUsers, newId, "main", "username", user.getUsername());
        client.insertRecord(tableUsers, newId, "main", "email", user.getEmail());
        client.insertRecord(tableUsers, newId, "main", "password", user.getPassword());
        client.insertRecord(tableUsers, newId, "main", "roles", user.getRoles());
        client.insertRecord(tableUsers, newId, "main", "created_at", user.getCreatedAt().toString());
    
        return user;
    }
    
    
}