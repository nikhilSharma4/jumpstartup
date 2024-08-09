package com.jumpstartup.jumpstartupjava;


import com.jumpstartup.Investor.InvestorBean;
import com.jumpstartup.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jumpstartup.Database.InvestorDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/investor")
@CrossOrigin(origins = "http://localhost:4200")
public class InvestorController {

    private static final Logger logger = LoggerFactory.getLogger(InvestorController.class);

    @PostMapping("/add")
    public ResponseEntity<?> addInvestor(@RequestBody InvestorBean investor) {
        InvestorDatabase investorDatabase = new InvestorDatabase();
        logger.info("Adding new investor data: {}", investor.toString());

        // Add the new investor to the database
        boolean isInvestorAdded = investorDatabase.addInvestor(investor);
        if (!isInvestorAdded) {
            logger.error("Failed to add investor to database.");
            return new ResponseEntity<>("Failed to add investor to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new education to the database
        boolean isEducationAdded = investorDatabase.addEducation(investor.getUuid(), investor.getInstitution(), investor.getDegree(),
                investor.getMajor(), investor.getYear_of_completion());
        if (!isEducationAdded) {
            logger.error("Failed to add education to database");
            return new ResponseEntity<>("Failed to add education to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Add the new work experience to the database
        boolean isWorkExperienceAdded = investorDatabase.addWorkExperience(investor.getUuid(), investor.getWork_experience());
        if (!isWorkExperienceAdded) {
            logger.error("Failed to add work experience to database");
            return new ResponseEntity<>("Failed to add work experience to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        logger.info("Investor record added successfully.");
        return new ResponseEntity<>(Status.buildStatus("ADDINV001","Added Investor succesfully"), HttpStatus.OK);
    }

    @PutMapping("/update/{UUID}")
    public ResponseEntity<String> updateInvestor(@PathVariable String UUID, @RequestBody InvestorBean investor) {
        InvestorDatabase investorDatabase = new InvestorDatabase();
        logger.info("Updating investor data: {}", UUID);

        // Update the investor in the database
        boolean isInvestorUpdated = investorDatabase.updateInvestor(UUID, investor);
        if (!isInvestorUpdated) {
            logger.error("Failed to update investor {} in database.", UUID);
            return new ResponseEntity<>("Failed to update investor in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Investor record updated successfully.");
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{UUID}")
    public ResponseEntity<String> deleteInvestor(@PathVariable String UUID) {
        InvestorDatabase investorDatabase = new InvestorDatabase();
        logger.info("Deleting investor: {}", UUID);

        // Delete the investor from the database
        boolean isInvestorDeleted = investorDatabase.deleteInvestor(UUID);
        if (!isInvestorDeleted) {
            logger.error("Failed to delete investor {}.", UUID);
            return new ResponseEntity<>("Failed to delete investor from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Deleted investor {} successfully.", UUID);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<InvestorBean> getInvestor(@PathVariable String UUID) {
        InvestorDatabase investorDatabase = new InvestorDatabase();
        logger.info("Getting investor data: {}", UUID);

        // Retrieve the investor from the database
        InvestorBean investor = investorDatabase.getInvestor(UUID);
        if (investor == null) {
            logger.error("Investor {} not found in database.", UUID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the investor data as a JSON response
        logger.info("Investor {} details fetched successfully.", UUID);
        return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<InvestorBean>> getAllInvestors() {
        InvestorDatabase investorDatabase = new InvestorDatabase();
        logger.info("Getting all investors");

        List<InvestorBean> allInvestors = investorDatabase.getAllInvestors();

        logger.info("All investors fetched successfully");
        return new ResponseEntity<>(allInvestors, HttpStatus.OK);
    }
}
