package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Company.CompanyBean;
import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
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
@RequestMapping("/entrepreneur")
@CrossOrigin(origins = "http://localhost:4200")
public class EntrepreneurController {

    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurController.class);
    
    @PostMapping("/add")
    public ResponseEntity<?> addEntrepreneur(@RequestBody EntrepreneurBean entrepreneur) {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();

        logger.info("Adding new entrepreneur data: {}", entrepreneur.toString());

        // Add the new entrepreneur to the database
        boolean isEntrepreneurAdded = entrepreneurDatabase.addEntrepreneur(entrepreneur);
        if (!isEntrepreneurAdded) {
            logger.error("Failed to add entrepreneur to database");
            return new ResponseEntity<>("Failed to add entrepreneur to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new education to the database
        boolean isEducationAdded = entrepreneurDatabase.addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(), entrepreneur.getDegree(),
                entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        if (!isEducationAdded) {
            logger.error("Failed to add education to database");
            return new ResponseEntity<>("Failed to add education to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new work experience to the database
        boolean isWorkExperienceAdded = entrepreneurDatabase.addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());
        if (!isWorkExperienceAdded) {
            logger.error("Failed to add work experience to database");
            return new ResponseEntity<>("Failed to add work experience to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        boolean isCompanyDetailsAdded = entrepreneurDatabase.addCompanyDetails(entrepreneur);
        if (!isCompanyDetailsAdded) {
            logger.error("Failed to add company details to database");
            return new ResponseEntity<>("Failed to add company details to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(Status.buildStatus("ADDENT001","Added Entrepreneur succesfully"), HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateEntrepreneur(@PathVariable String UUID, @RequestBody EntrepreneurBean entrepreneur) {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();
        logger.info("Updating entrepreneur data: {}", entrepreneur.toString());

        // Update the entrepreneur in the database
        boolean isEntrepreneurUpdated = entrepreneurDatabase.updateEntrepreneur(UUID, entrepreneur);
        if (!isEntrepreneurUpdated) {
            logger.error("Failed to update entrepreneur {} in database", UUID);
            return new ResponseEntity<>("Failed to update entrepreneur in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Entrepreneur record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteEntrepreneur(@PathVariable String UUID) {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();
        logger.info("Deleting entrepreneur: {}", UUID);

        // Delete the entrepreneur from the database
        boolean isEntrepreneurDeleted = entrepreneurDatabase.deleteEntrepreneur(UUID);
        if (!isEntrepreneurDeleted) {
            logger.error("Failed to delete entrepreneur {}.", UUID);
            return new ResponseEntity<>("Failed to delete entrepreneur from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted entrepreneur {} successfully.", UUID);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<EntrepreneurBean> getEntrepreneur(@PathVariable String UUID) {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();
        logger.info("Getting entrepreneur data: {}", UUID);

        // Retrieve the entrepreneur from the database
        EntrepreneurBean entrepreneur = entrepreneurDatabase.getEntrepreneur(UUID);
        if (entrepreneur == null) {
            logger.error("Entrepreneur {} not found in database.", UUID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the entrepreneur data as a JSON response
        logger.info("Entrepreneur {} details fetched successfully.", UUID);
        return new ResponseEntity<>(entrepreneur, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyBean>> getAllCompanies() {

        EntrepreneurDatabase entrepreneurDatabase = new EntrepreneurDatabase();
        logger.info("Getting all companies");

        List<CompanyBean> allCompanies = new ArrayList<CompanyBean>();

        allCompanies = entrepreneurDatabase.getCompanies();

        return new ResponseEntity<>(allCompanies, HttpStatus.OK);

    }
}
