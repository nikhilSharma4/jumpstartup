package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.JobsDatabase;
import com.jumpstartup.Jobs.JobsBean;
import com.jumpstartup.Model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:4200")

public class JobsController {

    private static final Logger logger = LoggerFactory.getLogger(JobsController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addJob(@RequestBody JobsBean job) {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Adding new Job data: {}", job.toString());

        // Add the new job to the database
        boolean isJobAdded = jobsDatabase.addJob(job);
        if (!isJobAdded) {
            logger.error("Failed to add Job to database");
            return new ResponseEntity<>("Failed to add Job to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(Status.buildStatus("ADDENT001", "Added Job successfully"), HttpStatus.OK);
    }

    @PutMapping("/update/{jobUuid}")
    public ResponseEntity<String> updateJob(@PathVariable String jobUuid, @RequestBody JobsBean job) {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Updating job data: {}", job.toString());

        // Update the Job in the database
        boolean isJobUpdated = jobsDatabase.updateJob(jobUuid, job);
        if (!isJobUpdated) {
            logger.error("Failed to update Job {} in database", jobUuid);
            return new ResponseEntity<>("Failed to update Job in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Job record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{jobUuid}")
    public ResponseEntity<String> deleteJob(@PathVariable String jobUuid) {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Deleting Job: {}", jobUuid);

        // Delete the Job from the database
        boolean isJobDeleted = jobsDatabase.deleteJob(jobUuid);
        if (!isJobDeleted) {
            logger.error("Failed to delete Job {}.", jobUuid);
            return new ResponseEntity<>("Failed to delete Job from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted Job {} successfully.", jobUuid);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{jobUuid}")
    public ResponseEntity<JobsBean> getJobDetails(@PathVariable String jobUuid) {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Getting Job data: {}", jobUuid);

        // Retrieve the Job from the database
        JobsBean job = jobsDatabase.getJob(jobUuid);
        if (job == null) {
            logger.error("Job {} not found in database.", jobUuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the job data as a JSON response
        logger.info("Job {} details fetched successfully.", jobUuid);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/entrepreneur/{entrepreneurUuid}")
    public ResponseEntity<List<JobsBean>> getJobs(@PathVariable String entrepreneurUuid) {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Getting all Jobs for Entrepreneur with UUID {}", entrepreneurUuid);

        List<JobsBean> allJobs = new ArrayList<JobsBean>();

        allJobs = jobsDatabase.getJobs(entrepreneurUuid);

        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JobsBean>> getAllJobs() {
        JobsDatabase jobsDatabase = new JobsDatabase();
        logger.info("Getting all Jobs");

        List<JobsBean> allJobs = new ArrayList<JobsBean>();

        allJobs = jobsDatabase.getAllJobs();

        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

}
