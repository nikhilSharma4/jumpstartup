package com.jumpstartup.jumpstartupjava;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntrepreneurBeanTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        EntrepreneurBean entrepreneur = new EntrepreneurBean();

        String uuid = "123";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "555-1234";
        String domain = "tech";
        String linkedinLink = "https://www.linkedin.com/in/johndoe";
        String institution = "University of California";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String yearOfCompletion = "2020";
        String workExperience = "Software Engineer at Google";
        String companyName = "Acme Corp";
        String isRegistered = "Yes";
        String stakeholder = "CEO";
        String companySize = "Small";
        String fundingStatus = "Seed";
        String equityOffered = "10%";
        String assets = "$100,000";
        String openToNegotiations = "Yes";
        String profitsInLastFy = "$50,000";
        String pitch = "We are building the future of AI.";

        // Act
        entrepreneur.setUuid(uuid);
        entrepreneur.setFirstName(firstName);
        entrepreneur.setLastName(lastName);
        entrepreneur.setPhone_number(phoneNumber);
        entrepreneur.setDomain(domain);
        entrepreneur.setLinkedin_link(linkedinLink);
        entrepreneur.setInstitution(institution);
        entrepreneur.setDegree(degree);
        entrepreneur.setMajor(major);
        entrepreneur.setYear_of_completion(yearOfCompletion);
        entrepreneur.setWork_experience(workExperience);
        entrepreneur.setCompany_name(companyName);
        entrepreneur.setIs_registered(isRegistered);
        entrepreneur.setStakeholder(stakeholder);
        entrepreneur.setCompany_size(companySize);
        entrepreneur.setFunding_status(fundingStatus);
        entrepreneur.setEquity_offered(equityOffered);
        entrepreneur.setAssets(assets);
        entrepreneur.setOpen_to_negotiations(openToNegotiations);
        entrepreneur.setProfits_in_last_fy(profitsInLastFy);
        entrepreneur.setPitch(pitch);

        // Assert
        Assertions.assertEquals(uuid, entrepreneur.getUuid());
        Assertions.assertEquals(firstName, entrepreneur.getFirstName());
        Assertions.assertEquals(lastName, entrepreneur.getLastName());
        Assertions.assertEquals(phoneNumber, entrepreneur.getPhone_number());
        Assertions.assertEquals(domain, entrepreneur.getDomain());
        Assertions.assertEquals(linkedinLink, entrepreneur.getLinkedin_link());
        Assertions.assertEquals(institution, entrepreneur.getInstitution());
        Assertions.assertEquals(degree, entrepreneur.getDegree());
        Assertions.assertEquals(major, entrepreneur.getMajor());
        Assertions.assertEquals(yearOfCompletion, entrepreneur.getYear_of_completion());
        Assertions.assertEquals(workExperience, entrepreneur.getWork_experience());
        Assertions.assertEquals(companyName, entrepreneur.getCompany_name());
        Assertions.assertEquals(isRegistered, entrepreneur.getIs_registered());
        Assertions.assertEquals(stakeholder, entrepreneur.getStakeholder());
        Assertions.assertEquals(companySize, entrepreneur.getCompany_size());
        Assertions.assertEquals(fundingStatus, entrepreneur.getFunding_status());
        Assertions.assertEquals(equityOffered, entrepreneur.getEquity_offered());
        Assertions.assertEquals(assets, entrepreneur.getAssets());
        Assertions.assertEquals(openToNegotiations, entrepreneur.getOpen_to_negotiations());
        Assertions.assertEquals(profitsInLastFy, entrepreneur.getProfits_in_last_fy());
        Assertions.assertEquals(pitch, entrepreneur.getPitch());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String uuid = "123";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "555-1234";
        String domain = "tech";
        String linkedinLink = "https://www.linkedin.com/in/johndoe";
        String institution = "University of California";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String yearOfCompletion = "2020";
        String workExperience = "Software Engineer at Google";
        String companyName = "Acme Corp";
        String isRegistered = "Yes";
        String stakeholder = "CEO";
        String companySize = "Small";
        String fundingStatus = "Seed";
        String equityOffered = "10%";
        String assets = "$100,000";
        String openToNegotiations = "Yes";
        String profitsInLastFy = "$50,000";
        String pitch = "We are building the future of AI.";

        // Act
        EntrepreneurBean entrepreneur = new EntrepreneurBean(uuid, firstName, lastName, phoneNumber, domain, linkedinLink, institution, degree, major, yearOfCompletion, workExperience, companyName, isRegistered, stakeholder, companySize, fundingStatus, equityOffered, assets, openToNegotiations, profitsInLastFy, pitch);

        // Assert
        Assertions.assertEquals(uuid, entrepreneur.getUuid());
        Assertions.assertEquals(firstName, entrepreneur.getFirstName());
        Assertions.assertEquals(lastName, entrepreneur.getLastName());
        Assertions.assertEquals(phoneNumber, entrepreneur.getPhone_number());
        Assertions.assertEquals(domain, entrepreneur.getDomain());
        Assertions.assertEquals(linkedinLink, entrepreneur.getLinkedin_link());
        Assertions.assertEquals(institution, entrepreneur.getInstitution());
        Assertions.assertEquals(degree, entrepreneur.getDegree());
        Assertions.assertEquals(major, entrepreneur.getMajor());
        Assertions.assertEquals(yearOfCompletion, entrepreneur.getYear_of_completion());
        Assertions.assertEquals(workExperience, entrepreneur.getWork_experience());
        Assertions.assertEquals(companyName, entrepreneur.getCompany_name());
        Assertions.assertEquals(isRegistered, entrepreneur.getIs_registered());
        Assertions.assertEquals(stakeholder, entrepreneur.getStakeholder());
        Assertions.assertEquals(companySize, entrepreneur.getCompany_size());
        Assertions.assertEquals(fundingStatus, entrepreneur.getFunding_status());
        Assertions.assertEquals(equityOffered, entrepreneur.getEquity_offered());
        Assertions.assertEquals(assets, entrepreneur.getAssets());
        Assertions.assertEquals(openToNegotiations, entrepreneur.getOpen_to_negotiations());
        Assertions.assertEquals(profitsInLastFy, entrepreneur.getProfits_in_last_fy());
        Assertions.assertEquals(pitch, entrepreneur.getPitch());
    }
}