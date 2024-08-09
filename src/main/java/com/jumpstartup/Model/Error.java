package com.jumpstartup.Model;

public class Error {

    private String errorCode;

    private String errorMessage;

    public Error(){
    }

    public Error(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        errorMessage = errorMessage;
    }

    public static Error buildError(String errorCode,String errorMessage){
        return new Error(errorCode,errorMessage);
    }

}
