package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Database.InvestorDatabase;
import com.jumpstartup.Investor.InvestorBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;

import com.jumpstartup.Investor.InvestorBean;

public class InvestorDBTest {

    private InvestorDatabase investorDb;

    @BeforeEach
    public void setUp() throws SQLException {
        investorDb = new InvestorDatabase();
        // Set up test database connection here if needed
    }

    @AfterEach
    public void tearDown() throws SQLException {
        investorDb = null;
        // Close test database connection here if needed
    }

//    @Test
//    public void testAddInvestor() {
//        // Create a new InvestorBean with test data
//        InvestorBean testInvestor = new InvestorBean();
//        testInvestor.setUuid("testUuid");
//        testInvestor.setPhone_number("1234567890");
//        testInvestor.setDomain("testDomain");
//        testInvestor.setFunding_available("$1000000");
//        testInvestor.setBrands_built("testBrand");
//
//        // Test adding the investor to the database
//        assertTrue(investorDb.addInvestor(testInvestor));
//    }
//
//    @Test
//    public void testAddEducation() {
//        // Test adding education to an investor in the database
//        assertTrue(investorDb.addEducation("testUuid", "testInstitution", "testDegree", "testMajor", "2022"));
//    }
//
//    @Test
//    public void testAddWorkExperience() {
//        // Test adding work experience to an investor in the database
//        assertTrue(investorDb.addWorkExperience("testUuid", "testWorkExperience"));
//    }
//
//    @Test
//    public void testUpdateInvestor() {
//        // Create a new InvestorBean with test data
//        InvestorBean testInvestor = new InvestorBean();
//        testInvestor.setPhone_number("0987654321");
//        testInvestor.setDomain("newTestDomain");
//        testInvestor.setFunding_available("$5000000");
//        testInvestor.setBrands_built("newTestBrand");
//        testInvestor.setInstitution("newTestInstitution");
//        testInvestor.setDegree("newTestDegree");
//        testInvestor.setMajor("newTestMajor");
//        testInvestor.setYear_of_completion("2023");
//        testInvestor.setWork_experience("newTestWorkExperience");
//
//        // Test updating the investor in the database
//        assertTrue(investorDb.updateInvestor("testUuid", testInvestor));
//    }
//
//    @Test
//    public void testDeleteInvestor() {
//        // Test deleting an investor from the database
//        assertTrue(investorDb.deleteInvestor("testUuid"));
//
//        // Test deleting a non-existent investor from the database
//        assertFalse(investorDb.deleteInvestor("nonExistentUuid"));
//    }
}
