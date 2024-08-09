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
import com.jumpstartup.Company.CompanyBean;
import com.jumpstartup.Connection.DatabaseConnector;
import com.jumpstartup.Database.EntrepreneurDatabase;
import com.jumpstartup.Entrepreneur.EntrepreneurBean;
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
class ECTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EntrepreneurController entrepreneurController;

    @Mock
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private EntrepreneurDatabase entrepreneurDatabase;

    @BeforeEach
    public void init() throws SQLException {
        connection = databaseConnector.getConnection();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(entrepreneurController).build();
    }

    @Test
    @Order(1)
    public void testAddEntrepreneurData_PASS() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(true);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addCompanyDetails(any(EntrepreneurBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(ArgumentMatchers.refEq(entrepreneur));
        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        verify(entrepreneurDatabase).addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());
        verify(entrepreneurDatabase).addCompanyDetails(ArgumentMatchers.refEq(entrepreneur));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testAddEntrepreneurData_FAIL() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(false);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addCompanyDetails(any(EntrepreneurBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(ArgumentMatchers.refEq(entrepreneur));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testAddEducation_FAIL() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(true);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(ArgumentMatchers.refEq(entrepreneur));
        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add education to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void testAddWorkExperience_FAIL() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(true);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(ArgumentMatchers.refEq(entrepreneur));
        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        verify(entrepreneurDatabase).addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add work experience to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    public void testAddCompanyDetails_FAIL() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.addEntrepreneur(any(EntrepreneurBean.class))).thenReturn(true);
        when(entrepreneurDatabase.addEducation(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addWorkExperience(any(String.class), any(String.class))).thenReturn(true);
        when(entrepreneurDatabase.addCompanyDetails(any(EntrepreneurBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(post("/entrepreneur/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).addEntrepreneur(ArgumentMatchers.refEq(entrepreneur));
        verify(entrepreneurDatabase).addEducation(entrepreneur.getUuid(), entrepreneur.getInstitution(),
                entrepreneur.getDegree(), entrepreneur.getMajor(), entrepreneur.getYear_of_completion());
        verify(entrepreneurDatabase).addWorkExperience(entrepreneur.getUuid(), entrepreneur.getWork_experience());
        verify(entrepreneurDatabase).addCompanyDetails(ArgumentMatchers.refEq(entrepreneur));

        // Verify that the controller method returns a valid response
        assertEquals("Failed to add company details to database", result.getResponse().getContentAsString());
    }

    @Test
    @Order(5)
    public void testUpdateEntrepreneurData_PASS() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Toronto");
        entrepreneur.setDegree("Bachelor of Commerce");
        entrepreneur.setMajor("Finance");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Wolf of Wall Street");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Accounts");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.updateEntrepreneur(any(String.class), any(EntrepreneurBean.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/entrepreneur/update/{UUID}", entrepreneur.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).updateEntrepreneur(ArgumentMatchers.refEq(entrepreneur.getUuid()), ArgumentMatchers.refEq(entrepreneur));

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testUpdateEntrepreneurData_FAIL() throws Exception {
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Toronto");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.updateEntrepreneur(any(String.class), any(EntrepreneurBean.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(put("/entrepreneur/update/{UUID}", entrepreneur.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(entrepreneur)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database methods with correct parameters
        verify(entrepreneurDatabase).updateEntrepreneur(ArgumentMatchers.refEq(entrepreneur.getUuid()), ArgumentMatchers.refEq(entrepreneur));

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void testGetEntrepreneurData_PASS() throws Exception {
        String uuid = "1234";
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        when(entrepreneurDatabase.getEntrepreneur(any(String.class))).thenReturn(entrepreneur);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/entrepreneur/{UUID}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).getEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testGetEntrepreneurData_FAIL() throws Exception {
        String uuid = "1234";
        EntrepreneurBean entrepreneur = null;

        when(entrepreneurDatabase.getEntrepreneur(any(String.class))).thenReturn(null);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/entrepreneur/{UUID}", uuid))
                .andExpect(status().isNotFound())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).getEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    public void testGetAllCompanies_PASS() throws Exception {
        String uuid = "1234";
        EntrepreneurBean entrepreneur = new EntrepreneurBean();
        entrepreneur.setUuid("1234");
        entrepreneur.setFirstName("John");
        entrepreneur.setLastName("Doe");
        entrepreneur.setPhone_number("1234567890");
        entrepreneur.setInstitution("University of Waterloo");
        entrepreneur.setDegree("Bachelor of Science");
        entrepreneur.setMajor("Computer Science");
        entrepreneur.setYear_of_completion("2020");
        entrepreneur.setWork_experience("5 years");
        entrepreneur.setAssets("CAD 10 MM");
        entrepreneur.setStakeholder("Govt. of Canada");
        entrepreneur.setPitch("I am the next Steve Jobs");
        entrepreneur.setOpen_to_negotiations("Yes");
        entrepreneur.setIs_registered("Yes");
        entrepreneur.setEquity_offered("20%");
        entrepreneur.setFunding_status("Seed");
        entrepreneur.setCompany_name("AppGooNetZonBook");
        entrepreneur.setDomain("Marketing");
        entrepreneur.setOpen_to_negotiations("CAD 5 MM");
        entrepreneur.setCompany_size("100");

        List<CompanyBean> companies = new ArrayList<>();
        CompanyBean company1 = new CompanyBean();
        company1.setIs_registered("Yes");
        company1.setEquity_offered("20%");
        company1.setFunding_status("Seed");
        company1.setCompany_name("AppGooNetZonBook");
        company1.setOpen_to_negotiations("Yes");
        company1.setCompany_size("100");
        company1.setProfits_in_last_fy("CAD 2 MM");
        company1.setAssets("CAD 15 MM");
        company1.setStakeholder("Govt. of Canada");
        company1.setEntrepreneurUUID(entrepreneur.getUuid());
        company1.setPitch("We are the next FAANG company");
        companies.add(company1);

        CompanyBean company2 = new CompanyBean();
        company2.setIs_registered("Yes");
        company2.setEquity_offered("2%");
        company2.setFunding_status("Public");
        company2.setCompany_name("NotAppGooNetZonBook");
        company2.setOpen_to_negotiations("Yes");
        company2.setCompany_size("10000");
        company2.setProfits_in_last_fy("CAD 200 MM");
        company2.setAssets("CAD 1500 MM");
        company2.setStakeholder("Govt. of Canada");
        company2.setEntrepreneurUUID(entrepreneur.getUuid());
        company2.setPitch("We are not the next FAANG company");
        companies.add(company2);

        when(entrepreneurDatabase.getCompanies()).thenReturn(companies);

        // Call the controller method
        MvcResult result = mockMvc.perform(get("/entrepreneur"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).getCompanies();

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(9)
    public void testDeleteEntrepreneurData_PASS() throws Exception {
        String uuid = "1234";

        when(entrepreneurDatabase.deleteEntrepreneur(any(String.class))).thenReturn(true);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/entrepreneur/delete/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).deleteEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(10)
    public void testDeleteEntrepreneurData_FAIL() throws Exception {
        String uuid = "1234";

        when(entrepreneurDatabase.deleteEntrepreneur(any(String.class))).thenReturn(false);

        // Call the controller method
        MvcResult result = mockMvc.perform(delete("/entrepreneur/delete/{uuid}", uuid))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Verify that the controller method calls the database method with the correct parameter
        verify(entrepreneurDatabase).deleteEntrepreneur(uuid);

        // Verify that the controller method returns a valid response
        assertEquals(500, result.getResponse().getStatus());
    }

}