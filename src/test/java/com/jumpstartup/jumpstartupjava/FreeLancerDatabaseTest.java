package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jumpstartup.Database.FreeLancerDatabase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FreeLancerDatabaseTest {
    @InjectMocks
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private FreeLancerDatabase freeLancerDatabase = new FreeLancerDatabase();

    @Test
    @Order(1)
    public void testAddFreelancer() throws SQLException {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("123");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setDegree("MBA");
        freelancer.setMajor("Finance");
        freelancer.setInstitution("UofT");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("2 years");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");

        when(databaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        boolean result = freeLancerDatabase.addFreelancer(freelancer);

        verify(databaseConnector, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(statement, times(4)).setString(anyInt(), any(String.class));
        verify(statement, times(1)).executeUpdate();
        verify(databaseConnector, times(1)).closeConnection(connection);

        assertTrue(result);
    }

    @Test
    @Order(2)
    void testAddEducation_Success() throws SQLException {
        // Arrange
        String uuid = "1234-5678";
        String institution = "Example University";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String year_of_completion = "2022";

        // Mock the database connection
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(DatabaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = freeLancerDatabase.addEducation(uuid, institution, degree, major, year_of_completion);

        // Assert
        assertTrue(result);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(statement, times(1)).setString(1, uuid);
        verify(statement, times(1)).setString(2, institution);
        verify(statement, times(1)).setString(3, degree);
        verify(statement, times(1)).setString(4, major);
        verify(statement, times(1)).setString(5, year_of_completion);
        verify(statement, times(1)).executeUpdate();
        verify(connection, times(1)).close();
    }

    @Test
    @Order(3)
    void testAddEducation_Failure() throws SQLException {
        // Arrange
        String uuid = "1234-5678";
        String institution = "Example University";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String year_of_completion = "2022";

        // Mock the database connection
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(DatabaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = freeLancerDatabase.addEducation(uuid, institution, degree, major, year_of_completion);

        // Assert
        assertFalse(result);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(statement, times(1)).setString(1, uuid);
        verify(statement, times(1)).setString(2, institution);
        verify(statement, times(1)).setString(3, degree);
        verify(statement, times(1)).setString(4, major);
        verify(statement, times(1)).setString(5, year_of_completion);
        verify(statement, times(1)).executeUpdate();
        verify(connection, times(1)).close();
    }

    @Test
    @Order(4)
    void testAddEducation_Exception() throws SQLException {
        // Arrange
        String uuid = "1234-5678";
        String institution = "Example University";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String year_of_completion = "2022";
        SQLException exception = new SQLException("Test exception");

        // Mock the database connection
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(DatabaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(exception).when(statement).executeUpdate();

        // Act
        boolean result = freeLancerDatabase.addEducation(uuid, institution, degree, major, year_of_completion);

        // Assert
        assertFalse(result);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(statement, times(1)).setString(1, uuid);
        verify(statement, times(1)).setString(2, institution);
        verify(statement, times(1)).setString(3, degree);
        verify(statement, times(1)).setString(4, major);
        verify(statement, times(1)).setString(5, year_of_completion);
        verify(statement, times(1)).executeUpdate();
//        verify(logger, times(1)).warn(eq("Failed to add education for freelancer with UUID: {}"), eq(uuid));
    }

    @Test
    @Order(5)
    public void testAddEducationSQLException() throws SQLException {
        String uuid = "1234-5678";
        String institution = "ABC University";
        String degree = "Bachelor's";
        String major = "Computer Science";
        String year_of_completion = "2022";

        Connection connection = mock(Connection.class);
        when(DatabaseConnector.getConnection()).thenReturn(connection);

        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        doThrow(new SQLException()).when(statement).executeUpdate();

        boolean result = freeLancerDatabase.addEducation(uuid, institution, degree, major, year_of_completion);

        assertFalse(result);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(statement, times(1)).setString(1, uuid);
        verify(statement, times(1)).setString(2, institution);
        verify(statement, times(1)).setString(3, degree);
        verify(statement, times(1)).setString(4, major);
        verify(statement, times(1)).setString(5, year_of_completion);
        verify(statement, times(1)).executeUpdate();
//        verify(logger, times(1)).error(eq("Error while trying to add education: {}"), anyString());
        verify(databaseConnector, times(1)).closeConnection(connection);
    }

    @Test
    @Order(7)
    public void testGetFreelancer() throws SQLException {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("123");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");

        when(databaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("uuid")).thenReturn(freelancer.getUuid());
        when(resultSet.getString("phone_number")).thenReturn(freelancer.getPhone_number());
        when(resultSet.getString("skills")).thenReturn(freelancer.getSkills());
        when(resultSet.getString("linkedin_link")).thenReturn(freelancer.getLinkedin_link());

        FreelancerBean result = freeLancerDatabase.getFreelancer("123");

        verify(databaseConnector, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(statement, times(1)).setString(anyInt(), any(String.class));
        verify(statement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getString("uuid");
        verify(resultSet, times(1)).getString("phone_number");
        verify(resultSet, times(1)).getString("skills");
        verify(resultSet, times(1)).getString("linkedin_link");
        verify(databaseConnector, times(1)).closeConnection(connection);

        assertNotNull(result);
        assertEquals(freelancer.getUuid(), result.getUuid());
        assertEquals(freelancer.getPhone_number(), result.getPhone_number());
        assertEquals(freelancer.getSkills(), result.getSkills());
        assertEquals(freelancer.getLinkedin_link(), result.getLinkedin_link());
    }

    @Test
    @Order(6)
    public void testUpdateFreelancer() throws SQLException {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("123");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Java, Python");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");

        when(databaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        boolean result = freeLancerDatabase.updateFreelancer(freelancer.getUuid(), freelancer);

        verify(databaseConnector, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(statement, times(4)).setString(anyInt(), any(String.class));
        verify(statement, times(1)).executeUpdate();
        verify(databaseConnector, times(1)).closeConnection(connection);

        assertTrue(result);
    }

    @Test
    @Order(8)
    public void testDeleteFreelancer() throws SQLException {
        when(databaseConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        boolean result = freeLancerDatabase.deleteFreelancer("123");

        verify(databaseConnector, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(statement, times(1)).setString(anyInt(), any(String.class));
        verify(statement, times(1)).executeUpdate();
        verify(databaseConnector, times(1)).closeConnection(connection);

        assertTrue(result);
    }
}