package com.jumpstartup.jumpstartupjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.Encryption.PasswordEncryption;
import com.jumpstartup.LoginBody.LoginRequest;
import com.jumpstartup.Model.LoginDetails;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginDatabase loginDatabase;

    @Mock
    private Connection connection;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    @Order(1)
    public void testSignupSubmit_PASS() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "firstName", "lastName", "email", "hashpass", "type");
        PasswordEncryption encryption = new PasswordEncryption();
        loginRequest.setHashpass(encryption.encryptPassword(loginRequest.getHashpass()));
        when(loginDatabase.newUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/login/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void testSignupSubmit_FAIL() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "firstName", "lastName", "email", "hashpass", "type");
        PasswordEncryption encryption = new PasswordEncryption();
        loginRequest.setHashpass(encryption.encryptPassword(loginRequest.getHashpass()));
        when(loginDatabase.newUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/login/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void testLogin_PASS() throws Exception {
        String username = "username";
        LoginRequest loginRequest = new LoginRequest(username, "firstName", "lastName", "email", "hashpass", "type");
        when(loginDatabase.getDetails(any(String.class))).thenReturn(loginRequest);

        MvcResult result = mockMvc.perform(get("/login/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void testLogin_FAIL() throws Exception {
        String username = "username";
        LoginRequest loginRequest = new LoginRequest(username, "firstName", "lastName", "email", "hashpass", "type");
        when(loginDatabase.getDetails(any(String.class))).thenReturn(null);

        MvcResult result = mockMvc.perform(get("/login/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    // TO DO - figure out how to use mockito on static methods/classes

//    @Test
//    @Order(3)
//    public void testLoginSubmit() throws Exception {
//        String username = "johndoe";
//        String hashpass = "hashpass";
//
//        LoginRequest loginRequest = new LoginRequest(username, "john", "doe", "johndoe@gmail.com", "hashpass", "Freelancer");
//
//        LoginDetails loginDetails = new LoginDetails();
//        loginDetails.setUUID("1234");
//        loginDetails.setUsername(username);
//        loginDetails.setEmail("johndoe@gmail.com");
//        loginDetails.setType("Freelancer");
//
//        when(loginDatabase.getDetails(username)).thenReturn(loginRequest);
//        try (MockedStatic<PasswordEncryption> passwordEncryption = mockStatic(PasswordEncryption.class)) {
//            passwordEncryption.when(() -> PasswordEncryption.decryptPassword(any(String.class), any(String.class))).thenReturn(loginDetails);
//            assertThat(PasswordEncryption.decryptPassword(username, hashpass)).isEqualTo(loginDetails);
//        }
//    }

    @Test
    @Order(5)
    public void testUpdateUserDetails_PASS() throws Exception {

        LoginRequest loginRequest = new LoginRequest("username", "firstName", "lastName", "email", "hashpass", "type");

        when(loginDatabase.updateDetails(any(String.class), any(String.class), any(String.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/login/updateUser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void testUpdateUserDetails_FAIL() throws Exception {

        LoginRequest loginRequest = new LoginRequest("username", "firstName", "lastName", "email", "hashpass", "type");

        when(loginDatabase.updateDetails(any(String.class), any(String.class), any(String.class))).thenReturn(false);

        MvcResult result = mockMvc.perform(put("/login/updateUser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertEquals(500, result.getResponse().getStatus());
    }

    // authenticate is not used in our backend, so need not run these testcases.

//    @Test
//    @Order(7)
//    public void testAuthenticate_PASS() throws Exception {
//        String username = "username";
//        String hashpass = "hashpass";
//        when(loginDatabase.authenticate(any(String.class), any(String.class))).thenReturn(true);
//        boolean result = loginDatabase.authenticate(username, hashpass);
//        assertTrue(result);
//    }
//
//    @Test
//    @Order(8)
//    public void testAuthenticate_FAIL() throws Exception {
//        String username = "username";
//        String hashpass = "hashpass";
//        when(loginDatabase.authenticate(any(String.class), any(String.class))).thenReturn(false);
//        boolean result = loginDatabase.authenticate(username, hashpass);
//        assertFalse(result);
//    }
}
