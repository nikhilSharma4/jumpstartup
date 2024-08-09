package com.jumpstartup.Model;

public class Status {

    private String statusCode;
    private String statusMessage;

    public Status(){

    }

    public Status (String statusCode,String statusMessage){
        this.statusCode=statusCode;
        this.statusMessage=statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public static Status buildStatus(String statusCode,String statusMessage){
        return new Status(statusCode,statusMessage);
    }
}
