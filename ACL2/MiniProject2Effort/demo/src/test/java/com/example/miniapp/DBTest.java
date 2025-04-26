package com.example.miniapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class DBTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testMongoDBConnection() {
        String databaseName = mongoTemplate.getDb().getName();
        System.out.println("MongoDB Connection Test: SUCCESS");
        System.out.println("MongoDB Database: " + databaseName);
        assertNotNull(databaseName);
    }

    @Test
    public void testPostgreSQLConnection() {
        String dbVersion = jdbcTemplate.queryForObject("SELECT version()", String.class);
        System.out.println("PostgreSQL Connection Test: SUCCESS");
        System.out.println("PostgreSQL Version: " + dbVersion);
        assertNotNull(dbVersion);
    }
}