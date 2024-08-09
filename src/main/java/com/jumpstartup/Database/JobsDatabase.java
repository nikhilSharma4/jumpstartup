package com.jumpstartup.Database;


import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Jobs.JobsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobsDatabase {
    private static final Logger logger = LoggerFactory.getLogger(JobsDatabase.class);

    public boolean addJob(JobsBean job) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String sql = "INSERT INTO Jobs (job_uuid, entrepreneur_uuid, description,  is_active,  number_of_openings," +
                         "skills,  pay_estimate,  type,  posting_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, job.getJobUuid());
            statement.setString(2, job.getEntrepreneurUuid());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getIsActive());
            statement.setString(5, job.getNumberOfOpenings());
            statement.setString(6, job.getSkills());
            statement.setString(7, job.getPayEstimate());
            statement.setString(8, job.getType());
            statement.setString(9, job.getPostingDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to add Jobs: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean updateJob(String jobUuid, JobsBean job) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            // update Job information
            String sql = "UPDATE Jobs SET description = ?, is_active = ?, number_of_openings = ?, skills = ?, " +
                        "pay_estimate = ?, type = ?, posting_date = ? WHERE job_uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, job.getDescription());
            statement.setString(2, job.getIsActive());
            statement.setString(3, job.getNumberOfOpenings());
            statement.setString(4, job.getSkills());
            statement.setString(5, job.getPayEstimate());
            statement.setString(6, job.getType());
            statement.setString(7, job.getPostingDate());
            statement.setString(8, jobUuid);

            int rowsAffected = statement.executeUpdate();

            // check if updates were successful
            if (rowsAffected > 0) {
                logger.info("Job with UUID {} has been updated successfully.", jobUuid);
                return true;
            } else {
                logger.warn("Failed to update Job with UUID {}.", jobUuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to update Jobs with uuid: {}", e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public boolean deleteJob(String jobUuid) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            String sql = "DELETE FROM Jobs WHERE job_uuid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jobUuid);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Jobs with UUID {} deleted successfully", jobUuid);
                return true;
            } else {
                logger.warn("No table 'Jobs' rows affected while deleting Job with UUID: {}", jobUuid);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while trying to delete Job with UUID: {}, error message: {}", jobUuid, e.getMessage());
            return false;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
    }

    public JobsBean getJob (String jobUuid) {
        JobsBean job = null;
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Jobs WHERE job_uuid = ?");
            statement.setString(1, jobUuid);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                job = new JobsBean();

                job.setEntrepreneurUuid(result.getString("entrepreneur_uuid"));
                job.setDescription(result.getString("description"));
                job.setIsActive(result.getString("is_active"));
                job.setNumberOfOpenings(result.getString("number_of_openings"));
                job.setSkills(result.getString("skills"));
                job.setPayEstimate(result.getString("pay_estimate"));
                job.setType(result.getString("type"));
                job.setPostingDate(result.getString("posting_date"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        logger.info("Fetched Job successfully");
        return job;
    }

    public List<JobsBean> getJobs (String entrepreneurUuid) {
        Connection connection = null;
        List<JobsBean> entrepreneurJobs = null;

        try {
            connection = DatabaseConnector.getConnection();
            entrepreneurJobs = new ArrayList<JobsBean>();

            PreparedStatement jobsStatement = connection.prepareStatement("SELECT * FROM Jobs WHERE entrepreneur_uuid = ?");
            jobsStatement.setString(1, entrepreneurUuid);

            ResultSet jobsResult = jobsStatement.executeQuery();

            while (jobsResult.next()) {
                JobsBean job = new JobsBean();

                job.setJobUuid(jobsResult.getString("job_uuid"));
                job.setEntrepreneurUuid(jobsResult.getString("entrepreneur_uuid"));
                job.setDescription(jobsResult.getString("description"));
                job.setIsActive(jobsResult.getString("is_active"));
                job.setNumberOfOpenings(jobsResult.getString("number_of_openings"));
                job.setSkills(jobsResult.getString("skills"));
                job.setPayEstimate(jobsResult.getString("pay_estimate"));
                job.setType(jobsResult.getString("type"));
                job.setPostingDate(jobsResult.getString("posting_date"));

                entrepreneurJobs.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }

        return entrepreneurJobs;
    }

    public List<JobsBean> getAllJobs() {
        Connection connection = null;
        List<JobsBean> allJobs = null;

        try {
            connection = DatabaseConnector.getConnection();
            allJobs = new ArrayList<JobsBean>();

            PreparedStatement jobStatement = connection.prepareStatement("SELECT * FROM Jobs");

            ResultSet jobsResult = jobStatement.executeQuery();

            while (jobsResult.next()) {
                JobsBean job = new JobsBean();

                job.setEntrepreneurUuid(jobsResult.getString("entrepreneur_uuid"));
                job.setJobUuid(jobsResult.getString("job_uuid"));
                job.setDescription(jobsResult.getString("description"));
                job.setIsActive(jobsResult.getString("is_active"));
                job.setNumberOfOpenings(jobsResult.getString("number_of_openings"));
                job.setSkills(jobsResult.getString("skills"));
                job.setPayEstimate(jobsResult.getString("pay_estimate"));
                job.setType(jobsResult.getString("type"));
                job.setPostingDate(jobsResult.getString("posting_date"));

                allJobs.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnector.closeConnection(connection);
        }

        return allJobs;
    }
}
