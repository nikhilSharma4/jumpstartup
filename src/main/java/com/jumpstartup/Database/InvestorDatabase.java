package com.jumpstartup.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Investor.InvestorBean;


public class InvestorDatabase {

    private static final Logger logger = LoggerFactory.getLogger(InvestorDatabase.class);
    
    public boolean addInvestor(InvestorBean investor) {

        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Investor (uuid,phone_number, domain, email_id, linkedin_link, funding_available, brands_built) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, investor.getUuid());
            statement.setString(2, investor.getPhone_number());
            statement.setString(3, investor.getDomain());
            statement.setString(4, investor.getEmailId());
            statement.setString(5, investor.getLinkedin_link());
            statement.setString(6, investor.getFunding_available());
            statement.setString(7, investor.getBrands_built());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Investor added successfully.");
                return true;
            } else {
                logger.warn("Failed to add investor.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add investor: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addEducation(String uuid, String institution, String degree, String major, String year_of_completion) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Education (UUID, institution, degree, major, year_of_completion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            statement.setString(2, institution);
            statement.setString(3, degree);
            statement.setString(4, major);
            statement.setString(5, year_of_completion);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Education added successfully for investor with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add education for investor with UUID: {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add education: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addWorkExperience(String uuid, String work_experience) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Work_Experience (UUID, work_experience) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid);
            statement.setString(2, work_experience);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Work experience added successfully for investor with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add work experience for investor with UUID: {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add work experience: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }
    
    public boolean updateInvestor(String uuid, InvestorBean investor) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // update investor information
            String sql = "UPDATE Investor SET phone_number = ?, domain = ?, email_id = ?, linkedin_link = ?, funding_available = ?, brands_built = ? WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, investor.getPhone_number());
            statement.setString(2, investor.getDomain());
            statement.setString(3, investor.getEmailId());
            statement.setString(4, investor.getLinkedin_link());
            statement.setString(5, investor.getFunding_available());
            statement.setString(6, investor.getBrands_built());
            statement.setString(7, uuid);

            int rowsAffected = statement.executeUpdate();

            // update education information
            String sqlEducation = "UPDATE Education SET institution = ?, degree = ?, major = ?, year_of_completion = ? WHERE uuid = ?";
            PreparedStatement statementEducation = connection.prepareStatement(sqlEducation);
            statementEducation.setString(1, investor.getInstitution());
            statementEducation.setString(2, investor.getDegree());
            statementEducation.setString(3, investor.getMajor());
            statementEducation.setString(4, investor.getYear_of_completion());
            statementEducation.setString(5, uuid);

            int rowsAffectedEducation = statementEducation.executeUpdate();

            // update work experience information
            String sqlWorkExperience = "UPDATE Work_Experience SET work_experience = ? WHERE uuid = ?";
            PreparedStatement statementWorkExperience = connection.prepareStatement(sqlWorkExperience);
            statementWorkExperience.setString(1, investor.getWork_experience());
            statementWorkExperience.setString(2, uuid);

            int rowsAffectedWorkExperience = statementWorkExperience.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0 && rowsAffectedEducation > 0 && rowsAffectedWorkExperience > 0) {
                logger.info("Investor {} updated successfully", uuid);
                return true;
            } else {
                logger.warn("Failed to update investor {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to update investor: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean deleteInvestor(String UUID) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            String educationSql = "DELETE FROM Education WHERE uuid = ?";
            PreparedStatement educationStatement = connection.prepareStatement(educationSql);
            educationStatement.setString(1, UUID);
            int educationRowsAffected = educationStatement.executeUpdate();
            if (educationRowsAffected <= 0) {
                logger.warn("No education rows affected while deleting investor with UUID: {}", UUID);
                return false;
            }

            // Delete work experience
            String workExperienceSql = "DELETE FROM Work_Experience WHERE uuid = ?";
            PreparedStatement workExperienceStatement = connection.prepareStatement(workExperienceSql);
            workExperienceStatement.setString(1, UUID);
            int workExperienceRowsAffected = workExperienceStatement.executeUpdate();
            if (workExperienceRowsAffected <= 0) {
                logger.warn("No work experience rows affected while deleting investor with UUID: {}", UUID);
                return false;
            }
            
            // Delete investor
            String sql = "DELETE FROM Investor WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0 ) {
                logger.info("Investor {} deleted successfully", UUID);
                return true;
            } else {
                logger.warn("Failed to delete Investor {}", UUID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to delete investor: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public InvestorBean getInvestor(String UUID) {
        InvestorBean investor = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Investor WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {

                PreparedStatement nameStatement = connection.prepareStatement("SELECT * FROM myuser WHERE uuid = ?");
                nameStatement.setString(1, UUID);
                ResultSet nameResult = nameStatement.executeQuery();
                investor = new InvestorBean();
                if(nameResult.next()) {
                    investor.setFirstName(nameResult.getString("first_name"));
                    investor.setLastName(nameResult.getString("last_name"));
                }

                investor.setUuid(result.getString("uuid"));
                investor.setPhone_number(result.getString("phone_number"));
                investor.setDomain(result.getString("domain"));
                investor.setEmailId(result.getString("email_id"));
                investor.setLinkedin_link(result.getString("linkedin_link"));
                investor.setFunding_available(result.getString("funding_available"));
                investor.setBrands_built(result.getString("brands_built"));

                statement = connection.prepareStatement("SELECT * FROM education WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    investor.setInstitution(result.getString("institution"));
                    investor.setDegree(result.getString("degree"));
                    investor.setMajor(result.getString("major"));
                    investor.setYear_of_completion(result.getString("year_of_completion"));
                }

                statement = connection.prepareStatement("SELECT * FROM work_experience WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    investor.setWork_experience(result.getString("work_experience"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        logger.info("FETCHED INVESTOR SUCCESSFULLY !!");
        return investor;
    }

    public List<InvestorBean> getAllInvestors() {
        Connection connection = null;
        List<InvestorBean> allInvestors = null;
        InvestorBean investor = null;
        try {
            connection = DatabaseConnector.getConnection();
            allInvestors = new ArrayList<InvestorBean>();

            PreparedStatement investorStatement = connection.prepareStatement("SELECT uuid FROM Investor");

            ResultSet investorResult = investorStatement.executeQuery();

            while (investorResult.next()) {
                investor = this.getInvestor(investorResult.getString("uuid"));

                allInvestors.add(investor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }

        return allInvestors;
    }

}
