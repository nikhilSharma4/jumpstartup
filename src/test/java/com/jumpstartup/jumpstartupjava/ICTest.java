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
import com.jumpstartup.Database.InvestorDatabase;
import com.jumpstartup.Investor.InvestorBean;
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
class ICTest {

    private MockMvc mockMvc;

    @InjectMocks
    private InvestorController investorController;

    @Mock
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private InvestorDatabase investorDatabase;

    @BeforeEach
    public void init() throws SQLException {
        connection = databaseConnector.getConnection();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(investorController).build();
    }

    @Test
    @Order(1)
    public void testAddInvestorData_PASS() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Google, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.addInvestor(any(InvestorBean.class))).thenReturn(true);
        when(investorDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(investorDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/investor/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).addInvestor(ArgumentMatchers.refEq(investor));
        verify(investorDatabase).addEducation(investor.getUuid(), investor.getInstitution(),
                investor.getDegree(), investor.getMajor(), investor.getYear_of_completion());
        verify(investorDatabase).addWorkExperience(investor.getUuid(), investor.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testAddInvestorData_FAIL() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Google, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.addInvestor(any(InvestorBean.class))).thenReturn(false);
        when(investorDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(investorDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/investor/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).addInvestor(ArgumentMatchers.refEq(investor));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testAddEducation_FAIL() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Google, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.addInvestor(any(InvestorBean.class))).thenReturn(true);
        when(investorDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/investor/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).addInvestor(ArgumentMatchers.refEq(investor));
        verify(investorDatabase).addEducation(investor.getUuid(), investor.getInstitution(),
                investor.getDegree(), investor.getMajor(), investor.getYear_of_completion());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add education to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void testAddWorkExperience_FAIL() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Google, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.addInvestor(any(InvestorBean.class))).thenReturn(true);
        when(investorDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(investorDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/investor/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).addInvestor(ArgumentMatchers.refEq(investor));
        verify(investorDatabase).addEducation(investor.getUuid(), investor.getInstitution(),
                investor.getDegree(), investor.getMajor(), investor.getYear_of_completion());
        verify(investorDatabase).addWorkExperience(investor.getUuid(), investor.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add work experience to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(5)
    public void testUpdateInvestorData_PASS() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("Jim");
        investor.setLastName("Diaz");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Meta, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/jimdiaz");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2016");
        investor.setWork_experience("5 years");

        when(investorDatabase.updateInvestor(any(String.class), any(InvestorBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/investor/update/{UUID}", investor.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).updateInvestor(ArgumentMatchers.refEq(investor.getUuid()), ArgumentMatchers.refEq(investor));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testUpdateInvestorData_FAIL() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("Jim");
        investor.setLastName("Diaz");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Meta, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/jimdiaz");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2016");
        investor.setWork_experience("5 years");

        when(investorDatabase.updateInvestor(any(String.class), any(InvestorBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/investor/update/{UUID}", investor.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(investor)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(investorDatabase).updateInvestor(ArgumentMatchers.refEq(investor.getUuid()), ArgumentMatchers.refEq(investor));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void testGetInvestorData_PASS() throws Exception {
        InvestorBean investor = new InvestorBean();
        investor.setUuid("1234");
        investor.setFirstName("John");
        investor.setLastName("Doe");
        investor.setPhone_number("1234567890");
        investor.setDomain("Machine Learning");
        investor.setBrands_built("Apple, Google, Netflix");
        investor.setFunding_available("CAD 10 BB");
        investor.setLinkedin_link("https://www.linkedin.com/in/johndoe");
        investor.setInstitution("ABC University");
        investor.setDegree("Bachelor of Science");
        investor.setMajor("Computer Science");
        investor.setYear_of_completion("2020");
        investor.setWork_experience("5 years");

        when(investorDatabase.getInvestor(any(String.class))).thenReturn(investor);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/investor/{UUID}", investor.getUuid()))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).getInvestor(investor.getUuid());

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testGetInvestorData_FAIL() throws Exception {
        String uuid = "1234";
        InvestorBean investor = null;

        when(investorDatabase.getInvestor(any(String.class))).thenReturn(null);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/investor/{UUID}", uuid))
                .andExpect(status().isNotFound())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).getInvestor(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testDeleteInvestorData_PASS() throws Exception {
        String uuid = "1234";

        when(investorDatabase.deleteInvestor(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/investor/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).deleteInvestor(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(9)
    public void testDeleteInvestorData_FAIL() throws Exception {
        String uuid = "1234";

        when(investorDatabase.deleteInvestor(any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/investor/delete/{uuid}", uuid))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).deleteInvestor(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(10)
    public void testGetAllInvestors_PASS() throws Exception {
        List<InvestorBean> investors = new ArrayList<>();
        InvestorBean investor1 = new InvestorBean();
        investor1.setUuid("1234");
        investor1.setFirstName("Jim");
        investor1.setLastName("Diaz");
        investor1.setPhone_number("1234567890");
        investor1.setDomain("Machine Learning");
        investor1.setBrands_built("Apple, Meta, Netflix");
        investor1.setFunding_available("CAD 10 BB");
        investor1.setLinkedin_link("https://www.linkedin.com/in/jimdiaz");
        investor1.setInstitution("ABC University");
        investor1.setDegree("Bachelor of Science");
        investor1.setMajor("Computer Science");
        investor1.setYear_of_completion("2020");
        investor1.setWork_experience("5 years");
        investors.add(investor1);

        InvestorBean investor2 = new InvestorBean();
        investor2.setUuid("1234");
        investor2.setFirstName("Jim");
        investor2.setLastName("Diaz");
        investor2.setPhone_number("1234567890");
        investor2.setDomain("Machine Learning");
        investor2.setBrands_built("Apple, Meta, Netflix");
        investor2.setFunding_available("CAD 10 BB");
        investor2.setLinkedin_link("https://www.linkedin.com/in/jimdiaz");
        investor2.setInstitution("ABC University");
        investor2.setDegree("Bachelor of Science");
        investor2.setMajor("Computer Science");
        investor2.setYear_of_completion("2020");
        investor2.setWork_experience("5 years");
        investors.add(investor2);

        when(investorDatabase.getAllInvestors()).thenReturn(investors);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/investor"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(investorDatabase).getAllInvestors();

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

}