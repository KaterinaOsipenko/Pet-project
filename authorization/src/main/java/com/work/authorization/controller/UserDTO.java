package com.work.authorization.controller;

import com.work.authorization.validator.EmailValid;
import com.work.authorization.validator.ValidPassword;
import org.hibernate.annotations.Type;

import javax.validation.constraints.*;

public class UserDTO {

    @NotNull
    @NotEmpty(message = "First name must not be empty.")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last name must not be empty.")
    private String lastName;

    @NotNull
    @NotEmpty (message = "Email must not be empty.")
    @EmailValid
    private String email;

    @NotEmpty(message = "Username must not be empty.")
    private String username;

    @NotNull
    @NotEmpty(message = "Password must not be empty.")
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotNull
    @Type(type = "yes_no")
    private boolean admin;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
