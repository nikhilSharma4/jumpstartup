package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Freelancer.FreelancerBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreelancerBeanTest {

    @Test
    void testConstructorAndGetters() {
        String UUID = "1234-5678";
        String firstName = "John";
        String lastName = "Doe";
        String phone_number = "555-1234";
        String skills = "Java, Python, SQL";
        String linkedin_link = "linkedin.com/johndoe";
        String institution = "University of California";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String year_of_completion = "2022";
        String work_experience = "Software Engineer";

        FreelancerBean freelancer = new FreelancerBean(UUID, firstName, lastName, phone_number, skills, linkedin_link, institution, degree, major, year_of_completion, work_experience);

        Assertions.assertEquals(UUID, freelancer.getUuid());
        Assertions.assertEquals(firstName, freelancer.getFirstName());
        Assertions.assertEquals(lastName, freelancer.getLastName());
        Assertions.assertEquals(phone_number, freelancer.getPhone_number());
        Assertions.assertEquals(skills, freelancer.getSkills());
        Assertions.assertEquals(linkedin_link, freelancer.getLinkedin_link());
        Assertions.assertEquals(institution, freelancer.getInstitution());
        Assertions.assertEquals(degree, freelancer.getDegree());
        Assertions.assertEquals(major, freelancer.getMajor());
        Assertions.assertEquals(year_of_completion, freelancer.getYear_of_completion());
        Assertions.assertEquals(work_experience, freelancer.getWork_experience());
    }

    @Test
    void testSetters() {
        FreelancerBean freelancer = new FreelancerBean();
        String UUID = "1234-5678";
        String firstName = "John";
        String lastName = "Doe";
        String phone_number = "555-1234";
        String skills = "Java, Python, SQL";
        String linkedin_link = "linkedin.com/johndoe";
        String institution = "University of California";
        String degree = "Bachelor of Science";
        String major = "Computer Science";
        String year_of_completion = "2022";
        String work_experience = "Software Engineer";

        freelancer.setUuid(UUID);
        freelancer.setFirstName(firstName);
        freelancer.setLastName(lastName);
        freelancer.setPhone_number(phone_number);
        freelancer.setSkills(skills);
        freelancer.setLinkedin_link(linkedin_link);
        freelancer.setInstitution(institution);
        freelancer.setDegree(degree);
        freelancer.setMajor(major);
        freelancer.setYear_of_completion(year_of_completion);
        freelancer.setWork_experience(work_experience);

        Assertions.assertEquals(UUID, freelancer.getUuid());
        Assertions.assertEquals(firstName, freelancer.getFirstName());
        Assertions.assertEquals(lastName, freelancer.getLastName());
        Assertions.assertEquals(phone_number, freelancer.getPhone_number());
        Assertions.assertEquals(skills, freelancer.getSkills());
        Assertions.assertEquals(linkedin_link, freelancer.getLinkedin_link());
        Assertions.assertEquals(institution, freelancer.getInstitution());
        Assertions.assertEquals(degree, freelancer.getDegree());
        Assertions.assertEquals(major, freelancer.getMajor());
        Assertions.assertEquals(year_of_completion, freelancer.getYear_of_completion());
        Assertions.assertEquals(work_experience, freelancer.getWork_experience());
    }
}
