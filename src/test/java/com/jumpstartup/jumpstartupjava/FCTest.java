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
import com.jumpstartup.Database.FreeLancerDatabase;
import com.jumpstartup.Freelancer.FreelancerBean;
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
class FCTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FreeLancerController freeLancerController;

    @Mock
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private FreeLancerDatabase freeLancerDatabase;

    @BeforeEach
    public void init() throws SQLException {
        connection = databaseConnector.getConnection();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(freeLancerController).build();
    }

    @Test
    @Order(1)
    public void testAddFreelancerData_PASS() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).addFreelancer(ArgumentMatchers.refEq(freelancer));
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());
        verify(freeLancerDatabase).addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testAddFreelancerData_FAIL() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(false);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).addFreelancer(ArgumentMatchers.refEq(freelancer));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testAddEducation_FAIL() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).addFreelancer(ArgumentMatchers.refEq(freelancer));
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add education to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void testAddWorkExperience_FAIL() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setInstitution("University of Waterloo");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.addFreelancer(any(FreelancerBean.class))).thenReturn(true);
        when(freeLancerDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(freeLancerDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/freelancer/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).addFreelancer(ArgumentMatchers.refEq(freelancer));
        verify(freeLancerDatabase).addEducation(freelancer.getUuid(), freelancer.getInstitution(),
                freelancer.getDegree(), freelancer.getMajor(), freelancer.getYear_of_completion());
        verify(freeLancerDatabase).addWorkExperience(freelancer.getUuid(), freelancer.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add work experience to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(5)
    public void testUpdateFreelancerData_PASS() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Machine Learning");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.updateFreelancer(any(String.class), any(FreelancerBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/freelancer/update/{UUID}", freelancer.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).updateFreelancer(ArgumentMatchers.refEq(freelancer.getUuid()), ArgumentMatchers.refEq(freelancer));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testUpdateFreelancerData_FAIL() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Machine Learning");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.updateFreelancer(any(String.class), any(FreelancerBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/freelancer/update/{UUID}", freelancer.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(freelancer)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(freeLancerDatabase).updateFreelancer(ArgumentMatchers.refEq(freelancer.getUuid()), ArgumentMatchers.refEq(freelancer));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void testGetFreelancerData_PASS() throws Exception {
        FreelancerBean freelancer = new FreelancerBean();
        freelancer.setUuid("1234");
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setPhone_number("1234567890");
        freelancer.setSkills("Machine Learning");
        freelancer.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        freelancer.setInstitution("ABC University");
        freelancer.setDegree("Bachelor of Science");
        freelancer.setMajor("Computer Science");
        freelancer.setYear_of_completion("2020");
        freelancer.setWork_experience("5 years");

        when(freeLancerDatabase.getFreelancer(any(String.class))).thenReturn(freelancer);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/freelancer/{UUID}", freelancer.getUuid()))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).getFreelancer(freelancer.getUuid());

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testGetFreelancerData_FAIL() throws Exception {
        String uuid = "1234";
        FreelancerBean freelancerBean = null;

        when(freeLancerDatabase.getFreelancer(any(String.class))).thenReturn(null);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/freelancer/{UUID}", uuid))
                .andExpect(status().isNotFound())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).getFreelancer(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testDeleteFreelancerData_PASS() throws Exception {
        String uuid = "1234";

        when(freeLancerDatabase.deleteFreelancer(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/freelancer/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).deleteFreelancer(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(9)
    public void testDeleteFreelancerData_FAIL() throws Exception {
        String uuid = "1234";

        when(freeLancerDatabase.deleteFreelancer(any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/freelancer/delete/{uuid}", uuid))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).deleteFreelancer(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(10)
    public void testGetAllFreelancers_PASS() throws Exception {
        List<FreelancerBean> freelancers = new ArrayList<>();
        FreelancerBean freelancer1 = new FreelancerBean();
        freelancer1.setUuid("1234");
        freelancer1.setFirstName("John");
        freelancer1.setLastName("Doe");
        freelancer1.setPhone_number("1234567890");
        freelancer1.setSkills("Machine Learning");
        freelancer1.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        freelancer1.setInstitution("ABC University");
        freelancer1.setDegree("Bachelor of Science");
        freelancer1.setMajor("Computer Science");
        freelancer1.setYear_of_completion("2020");
        freelancer1.setWork_experience("3 years");
        freelancers.add(freelancer1);

        FreelancerBean freelancer2 = new FreelancerBean();
        freelancer2.setUuid("1234");
        freelancer2.setFirstName("Jane");
        freelancer2.setLastName("Doe");
        freelancer2.setPhone_number("1234567890");
        freelancer2.setSkills("Machine Learning");
        freelancer2.setLinkedin_link("https://www.linkedin.com/in/janedoe");
        freelancer2.setInstitution("DEF University");
        freelancer2.setDegree("Bachelor of Arts");
        freelancer2.setMajor("Economics");
        freelancer2.setYear_of_completion("2022");
        freelancer2.setWork_experience("1 years");
        freelancers.add(freelancer2);

        when(freeLancerDatabase.getAllFreelancers()).thenReturn(freelancers);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/freelancer"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(freeLancerDatabase).getAllFreelancers();

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

}