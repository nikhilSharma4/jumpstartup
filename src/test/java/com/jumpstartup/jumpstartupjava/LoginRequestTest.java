package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.LoginBody.LoginRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class LoginRequestTest {

    @Test
    public void testDefaultConstructor() {
        LoginRequest request = new LoginRequest();
        assertNotNull(request.getUuid());
    }

    @Test
    public void testParameterizedConstructor() {
        String username = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String hashpass = "password123";
        String type = "admin";

        LoginRequest request = new LoginRequest(username, firstName, lastName, email, hashpass, type);

        assertEquals(username, request.getUsername());
        assertEquals(firstName, request.getFirstName());
        assertEquals(lastName, request.getLastName());
        assertEquals(email, request.getEmail());
        assertEquals(hashpass, request.getHashpass());
        assertEquals(type, request.getType());
        assertNotNull(request.getUuid());
    }

    @Test
    public void testGettersAndSetters() {
        LoginRequest request = new LoginRequest();

        String username = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String hashpass = "password123";
        String type = "admin";
        String uuid = UUID.randomUUID().toString();

        request.setUsername(username);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setHashpass(hashpass);
        request.setType(type);
        request.setUuid(uuid);

        assertEquals(username, request.getUsername());
        assertEquals(firstName, request.getFirstName());
        assertEquals(lastName, request.getLastName());
        assertEquals(email, request.getEmail());
        assertEquals(hashpass, request.getHashpass());
        assertEquals(type, request.getType());
        assertEquals(uuid, request.getUuid());
    }

}
