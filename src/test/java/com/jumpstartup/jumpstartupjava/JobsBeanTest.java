package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import com.jumpstartup.Jobs.JobsBean;
import org.junit.jupiter.api.Test;

public class JobsBeanTest {

    @Test
    public void testDefaultConstructor() {
        JobsBean job = new JobsBean();
        assertNotNull(job.getJobUuid());
    }

    @Test
    public void testParameterizedConstructor() {
        String entrepreneurUuid = UUID.randomUUID().toString();
        String description = "Some description";
        String isActive = "true";
        String numberOfOpenings = "5";
        String skills = "Java";
        String payEstimate = "$50000";
        String type = "Full-time";
        String postingDate = "2022-01-01";

        JobsBean job = new JobsBean(entrepreneurUuid, description, isActive, numberOfOpenings,
                skills, payEstimate, type, postingDate);

        assertNotNull(job.getJobUuid());
        assertEquals(entrepreneurUuid, job.getEntrepreneurUuid());
        assertEquals(description, job.getDescription());
        assertEquals(isActive, job.getIsActive());
        assertEquals(numberOfOpenings, job.getNumberOfOpenings());
        assertEquals(skills, job.getSkills());
        assertEquals(payEstimate, job.getPayEstimate());
        assertEquals(type, job.getType());
        assertEquals(postingDate, job.getPostingDate());
    }

    @Test
    public void testGettersAndSetters() {
        JobsBean job = new JobsBean();

        String jobUuid = UUID.randomUUID().toString();
        String entrepreneurUuid = UUID.randomUUID().toString();
        String description = "Some description";
        String isActive = "true";
        String numberOfOpenings = "5";
        String skills = "Java";
        String payEstimate = "$50000";
        String type = "Full-time";
        String postingDate = "2022-01-01";

        job.setJobUuid(jobUuid);
        job.setEntrepreneurUuid(entrepreneurUuid);
        job.setDescription(description);
        job.setIsActive(isActive);
        job.setNumberOfOpenings(numberOfOpenings);
        job.setSkills(skills);
        job.setPayEstimate(payEstimate);
        job.setType(type);
        job.setPostingDate(postingDate);

        assertEquals(jobUuid, job.getJobUuid());
        assertEquals(entrepreneurUuid, job.getEntrepreneurUuid());
        assertEquals(description, job.getDescription());
        assertEquals(isActive, job.getIsActive());
        assertEquals(numberOfOpenings, job.getNumberOfOpenings());
        assertEquals(skills, job.getSkills());
        assertEquals(payEstimate, job.getPayEstimate());
        assertEquals(type, job.getType());
        assertEquals(postingDate, job.getPostingDate());
    }
}
