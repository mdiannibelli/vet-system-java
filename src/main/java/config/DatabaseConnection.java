package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:h2:./veterinaryDB", "admin", "admin");
        }
        return connection;
    }

    public static void createTables() throws SQLException {
        try {
            Statement stmt = getConnection().createStatement();

            // Employee
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS employee (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100),
                            username VARCHAR(50) UNIQUE,
                            password VARCHAR(100),
                            age INT NOT NULL,
                            birthdate DATE NOT NULL,
                            address VARCHAR(100),
                            charge VARCHAR(50) NOT NULL
                        );
                    """);

            // Adopter
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS adopter (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100),
                            age INT,
                            birthdate DATE,
                            address VARCHAR(150)
                        );
                    """);

            // Pet
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS pet (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100),
                            birthdate DATE,
                            weight DOUBLE,
                            temperature DOUBLE,
                            specie VARCHAR(50),
                            health_state VARCHAR(50)
                        );
                    """);

            // Adoption
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS adoption (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            adopter_id INT,
                            employee_id INT,
                            pet_id INT,
                            date_adoption DATE,
                            adoption_type VARCHAR(50)
                        );
                    """);

            stmt.close();
            System.out.println("Tables created successfully.");
        } catch (Exception e) {
            System.out.println("Tables not created. Error: " + e.getMessage());
        }
    }
}
