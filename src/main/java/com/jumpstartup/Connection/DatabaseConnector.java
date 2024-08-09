package com.jumpstartup.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseConnector {
    private static final String DB_URL = "jdbc:h2:mem:jsudb";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

    public static Connection getConnection() throws SQLException {
        logger.debug("Getting database connection...");
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        logger.debug("Database connection obtained");
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.debug("Database connection closed");
            } catch (SQLException e) {
                logger.error("Error while trying to close database connection: {}", e.getMessage());
            }
        }
    }

    public static String getDbUrl() {
        return DB_URL;
    }

}
