package com.jumpstartup.Encryption;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Exception.UserDetailsNotValid;
import com.jumpstartup.Model.Error;
import com.jumpstartup.Model.LoginDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncryption {

    private static final Logger logger = LoggerFactory.getLogger(PasswordEncryption.class);

   static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public static LoginDetails decryptPassword(String username, String password) throws UserDetailsNotValid{
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "select * from MYUSER where USERNAME = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String encodePassword = result.getString("HASHPASS");
                logger.info("encoded hashpass: {}", encodePassword);
                if(!encoder.matches(password, encodePassword)){
                    throw new UserDetailsNotValid( Error.buildError("ERR001","Password doesn't Match"));
                }
                return LoginDetails.buildLoginDetails(username,result.getString("type"),result.getString("UUID"), result.getString("email"));
            }
            else {
                logger.warn("Encoded hashpass not found in DB");
                throw new UserDetailsNotValid(Error.buildError("ERR002","User not found"));
            }
        }
        catch (SQLException e) {
            logger.error("Error while login: {}", e.getMessage());
            throw new UserDetailsNotValid(Error.buildError("ERR000",e.getMessage()));
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
}