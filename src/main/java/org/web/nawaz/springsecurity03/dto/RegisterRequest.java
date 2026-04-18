package org.web.nawaz.springsecurity03.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "username is not required")
    @Size(min = 3, max = 20, message = "UserName must be between 5 - 20 Characters")
    String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 5, max = 20, message = "password must be between 10 - 29 characters")
    String password;

    @Size(min = 10, max = 20, message = "Full Name can contain almost 50 characters")
    private String fullName;

    public RegisterRequest() {}

    public RegisterRequest(String username, String email, String password, String fullName)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
