package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jumpstartup.Model.LoginDetails;
import org.junit.jupiter.api.Test;

public class LoginDetailsBeanTest {

    @Test
    public void testGetUUID() {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUUID("1234");
        assertEquals("1234", loginDetails.getUUID());
    }

    @Test
    public void testGetUsername() {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUsername("username");
        assertEquals("username", loginDetails.getUsername());
    }

    @Test
    public void testGetType() {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setType("type");
        assertEquals("type", loginDetails.getType());
    }

    @Test
    public void testGetEmail() {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setEmail("email@example.com");
        assertEquals("email@example.com", loginDetails.getEmail());
    }

    @Test
    public void testBuildLoginDetails() {
        LoginDetails loginDetails = LoginDetails.buildLoginDetails("username", "type", "1234", "email@example.com");
        assertEquals("username", loginDetails.getUsername());
        assertEquals("type", loginDetails.getType());
        assertEquals("1234", loginDetails.getUUID());
        assertEquals("email@example.com", loginDetails.getEmail());
    }
}
