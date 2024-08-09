package com.jumpstartup.Model;

public class LoginDetails {
    private String UUID;
    private String username;
    private String type;
    private String email;
    public String getUUID() {
        return UUID;
    }

    public LoginDetails setUUID(String UUID) {
        this.UUID = UUID;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static LoginDetails buildLoginDetails(String username, String type, String uuid, String email){
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setType(type);
        loginDetails.setUUID(uuid);
        loginDetails.setUsername(username);
        loginDetails.setEmail(email);
        return loginDetails;
    }
}
