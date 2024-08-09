package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.LoginBody.LoginRequest;


public class LoginDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoginDatabase.class);

    public boolean authenticate(String username, String password) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM myUser WHERE username = ? AND hashpass = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            System.out.println(password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Username {} found successfully.", username);
                return true;
            } else {
                logger.warn("Username {} not found!!", username);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to authenticate user: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean newUser(String UUID,String username, String firstName, String lastName, String email, String hashpass, String type) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO myUser(UUID, username, first_name, last_name, email, hashpass, type) VALUES(?, ?, ? ,?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            statement.setString(2, username);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, email);
            statement.setString(6, hashpass);
            statement.setString(7, type);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User with UUID {} added successfully.", UUID);
                return true;
            } else {
                logger.warn("Unable to add new user with UUID {}", UUID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add new user: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean updateDetails(String firstName,String lastName, String UUID){
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "UPDATE MYUSER SET FIRST_NAME = ?, LAST_NAME = ? WHERE UUID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User with UUID {} updated successfully.", UUID);
                return true;
            } else {
                logger.warn("Unable to update user with UUID {}", UUID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to update new user: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public LoginRequest getDetails(String username) {
        Connection connection = null;
        LoginRequest loginRequest = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "select * from MYUSER where username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                loginRequest = new LoginRequest();
                loginRequest.setUuid(result.getString("UUID"));
                loginRequest.setFirstName(result.getString("FIRST_NAME"));
                loginRequest.setLastName(result.getString("LAST_NAME"));
                loginRequest.setEmail(result.getString("EMAIL"));
                loginRequest.setType(result.getString("TYPE"));
                loginRequest.setUsername(result.getString("USERNAME"));
                logger.info("User details fetched successfully.");
                return loginRequest;
            }
            else {
                logger.warn("User {} not found in database.", username);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to get user details: {}", e.getMessage());
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}
