package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {

    @Test
    void testGetConnection() throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        assertNotNull(conn);
        assertTrue(conn.isValid(10));
        DatabaseConnector.closeConnection(conn);
    }

    @Test
    void testCloseConnection() throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        assertNotNull(conn);
        assertTrue(conn.isValid(10));
        DatabaseConnector.closeConnection(conn);
    }

    @Test
    void testInvalidCredentials() {
        assertThrows(SQLException.class, () -> {
            Connection conn = DriverManager.getConnection(DatabaseConnector.getDbUrl(), null, null);
        });
    }

}
