package com.jumpstartup.jumpstartupjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jumpstartup.Model.Error;

class ErrorBeanTest {

    @Test
    void testGetErrorCode() {
        Error error = new Error("code", "message");
        assertEquals("code", error.getErrorCode());
    }

    @Test
    void testGetErrorMessage() {
        Error error = new Error("code", "message");
        assertEquals("message", error.getErrorMessage());
    }

    @Test
    void testDefaultConstructorAndErrorCode() {
        Error error = new Error();
        error.setErrorCode("404");
        assertEquals("404",error.getErrorCode());
        error.setErrorMessage("NOT FOUND");
        assertEquals("NOT FOUND",error.getErrorMessage());

    }
    @Test
    void testBuildError() {
        Error error = Error.buildError("code", "message");
        assertNotNull(error);
        assertEquals("code", error.getErrorCode());
        assertEquals("message", error.getErrorMessage());
    }

}
