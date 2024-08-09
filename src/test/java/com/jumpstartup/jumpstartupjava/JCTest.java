package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Database.JobsDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
import com.jumpstartup.Jobs.JobsBean;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JCTest {

    private MockMvc mockMvc;

    @InjectMocks
    private JobsController jobsController;


    @Mock
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private JobsDatabase jobsDatabase;

    @BeforeEach
    public void init() throws SQLException {
        connection = databaseConnector.getConnection();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobsController).build();
    }

    @Test
    @Order(1)
    public void testAddJobData_PASS() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior C++ developer for a well funded startup in blockchain fintech algo trading.");
        job.setSkills("C++");
        job.setType("full-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.addJob(any(JobsBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/jobs/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).addJob(ArgumentMatchers.refEq(job));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testAddJobData_FAIL() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior C++ developer for a well funded startup in blockchain fintech algo trading.");
        job.setSkills("C++");
        job.setType("full-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.addJob(any(JobsBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/jobs/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).addJob(ArgumentMatchers.refEq(job));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testUpdateJobData_PASS() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job.setSkills("Java");
        job.setType("part-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.updateJob(any(String.class), any(JobsBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/jobs/update/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).updateJob(ArgumentMatchers.refEq(job.getJobUuid()), ArgumentMatchers.refEq(job));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void testUpdateJobData_FAIL() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job.setSkills("Java");
        job.setType("part-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.updateJob(any(String.class), any(JobsBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/jobs/update/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).updateJob(ArgumentMatchers.refEq(job.getJobUuid()), ArgumentMatchers.refEq(job));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(5)
    public void testDeleteJobData_PASS() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");

        when(jobsDatabase.deleteJob(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/jobs/delete/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).deleteJob(ArgumentMatchers.refEq(job.getJobUuid()));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testDeleteJobData_FAIL() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");

        when(jobsDatabase.deleteJob(any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/jobs/delete/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).deleteJob(ArgumentMatchers.refEq(job.getJobUuid()));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void testGetJobDetails_PASS() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job.setSkills("Java");
        job.setType("part-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.getJob(any(String.class))).thenReturn(job);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/jobs/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).getJob(ArgumentMatchers.refEq(job.getJobUuid()));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testGetJobDetails_FAIL() throws Exception {
        JobsBean job = new JobsBean();
        job.setJobUuid("_1234");
        job.setEntrepreneurUuid("1234");
        job.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job.setSkills("Java");
        job.setType("part-time");
        job.setIsActive("Yes");
        job.setNumberOfOpenings("5");
        job.setPayEstimate("CAD 500,000");
        job.setPostingDate("March 31, 2023");

        when(jobsDatabase.getJob(any(String.class))).thenReturn(null);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/jobs/{UUID}", job.getJobUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(job)))
                .andExpect(status().isNotFound())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).getJob(ArgumentMatchers.refEq(job.getJobUuid()));

        // Verify that the controller method returns a valid response
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    @Order(9)
    public void testGetAllJobs_PASS() throws Exception {
        List<JobsBean> jobs = new ArrayList<>();
        JobsBean job1 = new JobsBean();
        job1.setJobUuid("_1234");
        job1.setEntrepreneurUuid("1234");
        job1.setDescription("Need a senior C++ developer for a well funded startup in blockchain fintech algo trading.");
        job1.setSkills("C++");
        job1.setType("full-time");
        job1.setIsActive("Yes");
        job1.setNumberOfOpenings("5");
        job1.setPayEstimate("CAD 500,000");
        job1.setPostingDate("March 31, 2023");
        jobs.add(job1);

        JobsBean job2 = new JobsBean();
        job2.setJobUuid("__1234");
        job2.setEntrepreneurUuid("1234");
        job2.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job2.setSkills("Java");
        job2.setType("part-time");
        job2.setIsActive("Yes");
        job2.setNumberOfOpenings("5");
        job2.setPayEstimate("CAD 550,000");
        job2.setPostingDate("March 29, 2023");
        jobs.add(job2);

        when(jobsDatabase.getAllJobs()).thenReturn(jobs);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).getAllJobs();

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    // For getting all jobs by entrepreneur UUID - NEED TO FIX, not working yet
    @Test
    @Order(10)
    public void testGetJobs_PASS() throws Exception {
        List<JobsBean> jobs = new ArrayList<>();
        JobsBean job1 = new JobsBean();
        job1.setJobUuid("_1234");
        job1.setEntrepreneurUuid("1234");
        job1.setDescription("Need a senior C++ developer for a well funded startup in blockchain fintech algo trading.");
        job1.setSkills("C++");
        job1.setType("full-time");
        job1.setIsActive("Yes");
        job1.setNumberOfOpenings("5");
        job1.setPayEstimate("CAD 500,000");
        job1.setPostingDate("March 31, 2023");
        jobs.add(job1);

        JobsBean job2 = new JobsBean();
        job2.setJobUuid("__1234");
        job2.setEntrepreneurUuid("1234");
        job2.setDescription("Need a senior Java developer for a well funded startup in Android.");
        job2.setSkills("Java");
        job2.setType("part-time");
        job2.setIsActive("Yes");
        job2.setNumberOfOpenings("5");
        job2.setPayEstimate("CAD 550,000");
        job2.setPostingDate("March 29, 2023");
        jobs.add(job2);

        when(jobsDatabase.getJobs(any(String.class))).thenReturn(jobs);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/jobs/entrepreneur/{UUID}", job1.getEntrepreneurUuid()))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(jobsDatabase).getJobs(ArgumentMatchers.refEq(job1.getEntrepreneurUuid()));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

}