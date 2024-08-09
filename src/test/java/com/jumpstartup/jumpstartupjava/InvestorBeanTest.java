package com.jumpstartup.jumpstartupjava;



import com.jumpstartup.Investor.InvestorBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestorBeanTest {

    @Test
    void constructorAndGetters() {
        InvestorBean investor = new InvestorBean("uuid", "John", "Doe", "123-456-7890", "Tech", "linkedin.com/johndoe", "$1000000", "Google, Amazon", "Harvard University", "Bachelor of Science", "Computer Science", "2020", "Software Engineer");

        assertEquals("uuid", investor.getUuid());
        assertEquals("John", investor.getFirstName());
        assertEquals("Doe", investor.getLastName());
        assertEquals("123-456-7890", investor.getPhone_number());
        assertEquals("Tech", investor.getDomain());
        assertEquals("linkedin.com/johndoe", investor.getLinkedin_link());
        assertEquals("$1000000", investor.getFunding_available());
        assertEquals("Google, Amazon", investor.getBrands_built());
        assertEquals("Harvard University", investor.getInstitution());
        assertEquals("Bachelor of Science", investor.getDegree());
        assertEquals("Computer Science", investor.getMajor());
        assertEquals("2020", investor.getYear_of_completion());
        assertEquals("Software Engineer", investor.getWork_experience());
    }

    @Test
    void setters() {
        InvestorBean investor = new InvestorBean();

        investor.setUuid("uuid");
        assertEquals("uuid", investor.getUuid());

        investor.setFirstName("John");
        assertEquals("John", investor.getFirstName());

        investor.setLastName("Doe");
        assertEquals("Doe", investor.getLastName());

        investor.setPhone_number("123-456-7890");
        assertEquals("123-456-7890", investor.getPhone_number());

        investor.setDomain("Tech");
        assertEquals("Tech", investor.getDomain());

        investor.setLinkedin_link("linkedin.com/johndoe");
        assertEquals("linkedin.com/johndoe", investor.getLinkedin_link());

        investor.setFunding_available("$1000000");
        assertEquals("$1000000", investor.getFunding_available());

        investor.setBrands_built("Google, Amazon");
        assertEquals("Google, Amazon", investor.getBrands_built());

        investor.setInstitution("Harvard University");
        assertEquals("Harvard University", investor.getInstitution());

        investor.setDegree("Bachelor of Science");
        assertEquals("Bachelor of Science", investor.getDegree());

        investor.setMajor("Computer Science");
        assertEquals("Computer Science", investor.getMajor());

        investor.setYear_of_completion("2020");
        assertEquals("2020", investor.getYear_of_completion());

        investor.setWork_experience("Software Engineer");
        assertEquals("Software Engineer", investor.getWork_experience());
    }
}
