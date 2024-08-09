package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.Encryption.PasswordEncryption;
import com.jumpstartup.LoginBody.LoginRequest;
import com.jumpstartup.Exception.UserDetailsNotValid;
import com.jumpstartup.Model.Error;
import com.jumpstartup.Model.LoginDetails;
import com.jumpstartup.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")

public class LoginController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/{username}")
    public ResponseEntity<?> login(@PathVariable String username) {
        LoginDatabase loginDatabase = new LoginDatabase();

        LoginRequest loginRequest = null;

        loginRequest = loginDatabase.getDetails(username);

        if (loginRequest == null) {
            logger.warn("User with username {} not found.", username);
            return new ResponseEntity<>(Error.buildError("ERROO4", "User with username not found"), HttpStatus.NOT_FOUND);
        }
        logger.info("User with username {} exists.", username);
        return new ResponseEntity<>(loginRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> loginSubmit(@RequestBody LoginRequest loginRequest) {
        try {
            LoginDetails loginDetails = PasswordEncryption.decryptPassword(loginRequest.getUsername(), loginRequest.getHashpass());
            logger.info("User {} has been authorized.", loginRequest.getUsername());
            return new ResponseEntity<>(loginDetails,HttpStatus.OK);
        }
        catch (UserDetailsNotValid u) {
            logger.warn("Failed to authorize user {}.", loginRequest.getUsername());
            return new ResponseEntity<>(u.getError(),HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginDetails> signupSubmit(@RequestBody LoginRequest loginRequest) {
        loginRequest.setHashpass(PasswordEncryption.encryptPassword(loginRequest.getHashpass()));
        boolean success = signup(loginRequest.getUuid(), loginRequest.getUsername(), loginRequest.getFirstName(), loginRequest.getLastName(), loginRequest.getHashpass(), loginRequest.getEmail(), loginRequest.getType());
        if (success) {
            logger.info("User {} signed up successfully.", loginRequest.getUsername());
            LoginDetails loginDetails = LoginDetails.buildLoginDetails(loginRequest.getUsername(), loginRequest.getType(), loginRequest.getUuid(), loginRequest.getEmail());
            return new ResponseEntity<>(loginDetails, HttpStatus.OK);
        } else {
            logger.warn("Failed to sign up user {}.", loginRequest.getUsername());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUserDetails(@RequestBody LoginRequest loginRequest){
        LoginDatabase loginDatabase = new LoginDatabase();
        boolean updated = loginDatabase.updateDetails(loginRequest.getFirstName(), loginRequest.getLastName(), loginRequest.getUuid());
        if (updated) {
            return new ResponseEntity<>(Status.buildStatus("JSUP001", "Updated Successfuly"), HttpStatus.OK);
        }
        return new ResponseEntity<>(Error.buildError("ERR003", "Error Updating the User"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean authenticate(String username, String password) {
        LoginDatabase loginDatabase = new LoginDatabase();
        return loginDatabase.authenticate(username,password);
    }

    private boolean signup(String UUID, String username, String firstName, String lastName, String password, String email, String type) {
        LoginDatabase loginDatabase = new LoginDatabase();
        return loginDatabase.newUser(UUID, username, firstName, lastName, email, password, type);
    }
}
