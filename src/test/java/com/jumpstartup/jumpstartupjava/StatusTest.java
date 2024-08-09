package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jumpstartup.Model.Status;
import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void testStatusCode() {
        Status status = new Status();
        status.setStatusCode("200");
        assertEquals("200", status.getStatusCode());
    }

    @Test
    public void testStatusMessage() {
        Status status = new Status();
        status.setStatusMessage("Success");
        assertEquals("Success", status.getStatusMessage());
    }

    @Test
    public void testBuildStatus() {
        Status status = Status.buildStatus("404", "Not Found");
        assertEquals("404", status.getStatusCode());
        assertEquals("Not Found", status.getStatusMessage());
    }

    @Test
    public void testConstructor() {
        Status status = new Status("500", "Internal Server Error");
        assertEquals("500", status.getStatusCode());
        assertEquals("Internal Server Error", status.getStatusMessage());
    }
}
