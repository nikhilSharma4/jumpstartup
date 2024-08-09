package com.jumpstartup.Exception;

import com.jumpstartup.Model.Error;

public class UserDetailsNotValid extends Exception{

    private static final long serialVersionUID = 7718828512143293558L;

    private final Error error;

    public UserDetailsNotValid(Error error) {
        super();
        this.error = error;

    }

    public Error getError() {
        return this.error;
    }
}
