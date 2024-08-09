package com.jumpstartup.jumpstartupjava;


import com.jumpstartup.Exception.UserDetailsNotValid;
import com.jumpstartup.Model.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionTests {

    private static final String ERROR_CODE = "ERR001";
    private static final String ERROR_MESSAGE = "User details are not valid";

    @Test
    public void testConstructorAndGetError() {
        // given
        Error error = new Error(ERROR_CODE, ERROR_MESSAGE);

        // when
        UserDetailsNotValid exception = new UserDetailsNotValid(error);

        // then
        Assertions.assertEquals(error, exception.getError());
    }

    @Test
    public void testGetError() {
        // given
        Error error = new Error(ERROR_CODE, ERROR_MESSAGE);
        UserDetailsNotValid exception = new UserDetailsNotValid(error);

        // when
        Error retrievedError = exception.getError();

        // then
        Assertions.assertEquals(ERROR_CODE, retrievedError.getErrorCode());
        Assertions.assertEquals(ERROR_MESSAGE, retrievedError.getErrorMessage());
    }
}
