package com.jumpstartup.Database;

import com.jumpstartup.Company.CompanyBean;
import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EntrepreneurDatabase {

    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurDatabase.class);

    public boolean addEntrepreneur(EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Entrepreneur (uuid, phone_number, domain, email_id, linkedin_link) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getUuid());
            statement.setString(2, entrepreneur.getPhone_number());
            statement.setString(3, entrepreneur.getDomain());
            statement.setString(4, entrepreneur.getEmailId());
            statement.setString(5, entrepreneur.getLinkedin_link());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add entrepreneur: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean addCompanyDetails(EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String companySql = "INSERT INTO Company (uuid, company_name, is_registered, stakeholder," +
                    "                            company_size, funding_status, equity_offered, assets," +
                    "                            open_to_negotiations, profits_in_last_FY, pitch) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement companyStatement = connection.prepareStatement(companySql);
            companyStatement.setString(1, entrepreneur.getUuid());
            companyStatement.setString(2, entrepreneur.getCompany_name());
            companyStatement.setString(3, entrepreneur.getIs_registered());
            companyStatement.setString(4, entrepreneur.getStakeholder());
            companyStatement.setString(5, entrepreneur.getCompany_size());
            companyStatement.setString(6, entrepreneur.getFunding_status());
            companyStatement.setString(7, entrepreneur.getEquity_offered());
            companyStatement.setString(8, entrepreneur.getAssets());
            companyStatement.setString(9, entrepreneur.getOpen_to_negotiations());
            companyStatement.setString(10, entrepreneur.getProfits_in_last_fy());
            companyStatement.setString(11, entrepreneur.getPitch());

            int companyRowsAffected = companyStatement.executeUpdate();

            if (companyRowsAffected > 0) {
                logger.info("Company Details added successfully for entrepreneur with UUID: {}", entrepreneur.getUuid());
                return true;
            } else {
                logger.warn("Failed to add company details for entrepreneur with UUID: {}", entrepreneur.getUuid());
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add Company Details: {}", e.getMessage());
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
                logger.info("Education added successfully for entrepreneur with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add education for entrepreneur with UUID: {}", uuid);
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
                logger.info("Work experience added successfully for entrepreneur with UUID: {}", uuid);
                return true;
            } else {
                logger.warn("Failed to add work experience for entrepreneur with UUID: {}", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add work experience: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean updateEntrepreneur(String uuid, EntrepreneurBean entrepreneur) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // update entrepreneur information
            String sql = "UPDATE Entrepreneur SET phone_number = ?, domain = ?, email_id = ?, linkedin_link = ? WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entrepreneur.getPhone_number());
            statement.setString(2, entrepreneur.getDomain());
            statement.setString(3, entrepreneur.getEmailId());
            statement.setString(4, entrepreneur.getLinkedin_link());
            statement.setString(5, uuid);

            int rowsAffected = statement.executeUpdate();

            // update education information
            String sqlEducation = "UPDATE Education SET institution = ?, degree = ?, major = ?, year_of_completion = ? WHERE uuid = ?";
            PreparedStatement statementEducation = connection.prepareStatement(sqlEducation);
            statementEducation.setString(1, entrepreneur.getInstitution());
            statementEducation.setString(2, entrepreneur.getDegree());
            statementEducation.setString(3, entrepreneur.getMajor());
            statementEducation.setString(4, entrepreneur.getYear_of_completion());
            statementEducation.setString(5, uuid);

            int rowsAffectedEducation = statementEducation.executeUpdate();

            // update work experience information
            String sqlWorkExperience = "UPDATE Work_Experience SET work_experience = ? WHERE uuid = ?";
            PreparedStatement statementWorkExperience = connection.prepareStatement(sqlWorkExperience);
            statementWorkExperience.setString(1, entrepreneur.getWork_experience());
            statementWorkExperience.setString(2, uuid);

            int rowsAffectedWorkExperience = statementWorkExperience.executeUpdate();

            // update company details information
            String companySql = "UPDATE Company SET company_name = ?, is_registered = ?, stakeholder = ?," +
                    "company_size = ?, funding_status = ?, equity_offered = ?, assets = ?," +
                    "open_to_negotiations = ?, profits_in_last_FY = ?, pitch = ? " +
                    "WHERE uuid = ?";
            PreparedStatement companyStatement = connection.prepareStatement(companySql);
            companyStatement.setString(1, entrepreneur.getCompany_name());
            companyStatement.setString(2, entrepreneur.getIs_registered());
            companyStatement.setString(3, entrepreneur.getStakeholder());
            companyStatement.setString(4, entrepreneur.getCompany_size());
            companyStatement.setString(5, entrepreneur.getFunding_status());
            companyStatement.setString(6, entrepreneur.getEquity_offered());
            companyStatement.setString(7, entrepreneur.getAssets());
            companyStatement.setString(8, entrepreneur.getOpen_to_negotiations());
            companyStatement.setString(9, entrepreneur.getProfits_in_last_fy());
            companyStatement.setString(10, entrepreneur.getPitch());
            companyStatement.setString(11, entrepreneur.getUuid());

            int companyRowsAffected = companyStatement.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0 && rowsAffectedEducation > 0 && rowsAffectedWorkExperience > 0 && companyRowsAffected > 0) {
                logger.info("Entrepreneur with UUID {} has been updated successfully.", uuid);
                return true;
            } else {
                logger.warn("Failed to update entrepreneur with UUID {}.", uuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to update entrepreneur: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean deleteEntrepreneur(String UUID) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            String educationSql = "DELETE FROM Education WHERE uuid = ?";
            PreparedStatement educationStatement = connection.prepareStatement(educationSql);
            educationStatement.setString(1, UUID);
            int educationRowsAffected = educationStatement.executeUpdate();
            if (educationRowsAffected <= 0) {
                logger.warn("No education rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }

            String workExperienceSql = "DELETE FROM Work_Experience WHERE uuid = ?";
            PreparedStatement workExperienceStatement = connection.prepareStatement(workExperienceSql);
            workExperienceStatement.setString(1, UUID);
            int workExperienceRowsAffected = workExperienceStatement.executeUpdate();
            if (workExperienceRowsAffected <= 0) {
                logger.warn("No work experience rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }

            // Delete Company Details
            String companySql = "DELETE FROM Company WHERE uuid = ?";
            PreparedStatement companyStatement = connection.prepareStatement(companySql);
            companyStatement.setString(1, UUID);
            int companyRowsAffected = companyStatement.executeUpdate();
            if (companyRowsAffected <= 0) {
                logger.warn("No Company rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }
            // Delete entrepreneur
            String sql = "DELETE FROM Entrepreneur WHERE uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Entrepreneur with UUID {} deleted successfully", UUID);
                return true;
            } else {
                logger.warn("No table 'Entrepreneur' rows affected while deleting entrepreneur with UUID: {}", UUID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to delete entrepreneur with UUID: {}, error message: {}", UUID, e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public EntrepreneurBean getEntrepreneur(String UUID) {
        EntrepreneurBean entrepreneur = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Entrepreneur WHERE uuid = ?");
            statement.setString(1, UUID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                PreparedStatement nameStatement = connection.prepareStatement("SELECT * FROM myuser WHERE uuid = ?");
                nameStatement.setString(1, UUID);
                ResultSet nameResult = nameStatement.executeQuery();
                entrepreneur = new EntrepreneurBean();
                if (nameResult.next()) {
                    entrepreneur.setFirstName(nameResult.getString("first_name"));
                    entrepreneur.setLastName(nameResult.getString("last_name"));
                }

                entrepreneur.setUuid(result.getString("uuid"));
                entrepreneur.setPhone_number(result.getString("phone_number"));
                entrepreneur.setDomain(result.getString("domain"));
                entrepreneur.setEmailId(result.getString("email_id"));
                entrepreneur.setLinkedin_link(result.getString("linkedin_link"));

                statement = connection.prepareStatement("SELECT * FROM education WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    entrepreneur.setInstitution(result.getString("institution"));
                    entrepreneur.setDegree(result.getString("degree"));
                    entrepreneur.setMajor(result.getString("major"));
                    entrepreneur.setYear_of_completion(result.getString("year_of_completion"));
                }

                statement = connection.prepareStatement("SELECT * FROM work_experience WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    entrepreneur.setWork_experience(result.getString("work_experience"));
                }

                statement = connection.prepareStatement("SELECT * FROM Company WHERE uuid = ?");
                statement.setString(1, UUID);
                result = statement.executeQuery();
                if (result.next()) {
                    entrepreneur.setCompany_name(result.getString("company_name"));
                    entrepreneur.setIs_registered(result.getString("is_registered"));
                    entrepreneur.setStakeholder(result.getString("stakeholder"));
                    entrepreneur.setCompany_size(result.getString("company_size"));
                    entrepreneur.setFunding_status(result.getString("funding_status"));
                    entrepreneur.setEquity_offered(result.getString("equity_offered"));
                    entrepreneur.setAssets(result.getString("assets"));
                    entrepreneur.setOpen_to_negotiations(result.getString("open_to_negotiations"));
                    entrepreneur.setProfits_in_last_fy(result.getString("profits_in_last_FY"));
                    entrepreneur.setPitch(result.getString("pitch"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        logger.info("Fetched Entrepreneur successfully");
        return entrepreneur;
    }

    public List<CompanyBean> getCompanies() {
        Connection connection = null;
        List<CompanyBean> allCompanies = null;
        CompanyBean company = null;
        try {
            connection = DatabaseConnector.getConnection();
            allCompanies = new ArrayList<CompanyBean>();

            PreparedStatement companyStatement = connection.prepareStatement("SELECT * FROM Company");

            ResultSet companyResult = companyStatement.executeQuery();

            while (companyResult.next()) {
                company = new CompanyBean();

                company.setEntrepreneurUUID(companyResult.getString("uuid"));
                company.setCompany_name(companyResult.getString("company_name"));
                company.setIs_registered(companyResult.getString("is_registered"));
                company.setStakeholder(companyResult.getString("stakeholder"));
                company.setCompany_size(companyResult.getString("company_size"));
                company.setFunding_status(companyResult.getString("funding_status"));
                company.setEquity_offered(companyResult.getString("equity_offered"));
                company.setAssets(companyResult.getString("assets"));
                company.setOpen_to_negotiations(companyResult.getString("open_to_negotiations"));
                company.setProfits_in_last_fy(companyResult.getString("profits_in_last_FY"));
                company.setPitch(companyResult.getString("pitch"));

                allCompanies.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }

        return allCompanies;
    }
}
