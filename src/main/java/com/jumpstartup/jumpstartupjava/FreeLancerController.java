package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Freelancer.FreelancerBean;
import com.jumpstartup.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jumpstartup.Database.FreeLancerDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/freelancer")
@CrossOrigin(origins = "http://localhost:4200")
public class FreeLancerController {

    private static final Logger logger = LoggerFactory.getLogger(FreeLancerController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addFreelancerData(@RequestBody FreelancerBean freelancer) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();
        logger.info("Adding new freelancer data: {}", freelancer.toString());

        // Add the new freelancer to the database
        boolean isFreelancerAdded = flDatabase.addFreelancer(freelancer);
        if (!isFreelancerAdded) {
            logger.error("Failed to add freelancer to database");
            return new ResponseEntity<>("Failed to add freelancer to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new education to the database
        boolean isEducationAdded = flDatabase.addEducation(freelancer.getUuid(), freelancer.getInstitution(), freelancer.getDegree(),
                freelancer.getMajor(), freelancer.getYear_of_completion());
        if (!isEducationAdded) {
            logger.error("Failed to add education to database");
            return new ResponseEntity<>("Failed to add education to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new work experience to the database
        boolean isWorkExperienceAdded = flDatabase.addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());
        if (!isWorkExperienceAdded) {
            logger.error("Failed to add work experience to database");
            return new ResponseEntity<>("Failed to add work experience to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Freelancer record added successfully.");
        return new ResponseEntity<>(Status.buildStatus("ADDFREE001","Added Freelancer succesfully"), HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateFreelancer(@PathVariable String UUID, @RequestBody FreelancerBean freelancer) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();
        logger.info("Updating freelancer data: {}", freelancer.toString());

        // Update the freelancer in the database
        boolean isFreelancerUpdated = flDatabase.updateFreelancer(UUID, freelancer);
        if (!isFreelancerUpdated) {
            logger.error("Failed to update freelancer {} in database", UUID);
            return new ResponseEntity<>("Failed to update freelancer in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Freelancer record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable String UUID) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();
        logger.info("Deleting freelancer: {}", UUID);

        // Delete the freelancer from the database
        boolean isFreelancerDeleted = flDatabase.deleteFreelancer(UUID);
        if (!isFreelancerDeleted) {
            logger.error("Failed to delete freelancer {}.", UUID);
            return new ResponseEntity<>("Failed to delete freelancer from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted freelancer {} successfully.", UUID);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<FreelancerBean> getFreelancer(@PathVariable String UUID) {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();
        logger.info("Getting freelancer data: {}", UUID);

        // Retrieve the freelancer from the database
        FreelancerBean freelancer = flDatabase.getFreelancer(UUID);
        if (freelancer == null) {
            logger.error("Freelancer {} not found in database.", UUID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the freelancer data as a JSON response
        logger.info("Freelancer {} details fetched successfully.", UUID);
        return new ResponseEntity<>(freelancer, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<FreelancerBean>> getAllFreelancers() {
        FreeLancerDatabase flDatabase = new FreeLancerDatabase();
        logger.info("Getting all freelancers");

        List<FreelancerBean> allFreelancers = flDatabase.getAllFreelancers();

        logger.info("All investors fetched successfully");
        return new ResponseEntity<>(allFreelancers, HttpStatus.OK);
    }

}
