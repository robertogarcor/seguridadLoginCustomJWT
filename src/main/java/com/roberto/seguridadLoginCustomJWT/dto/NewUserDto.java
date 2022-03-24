package com.roberto.seguridadLoginCustomJWT.dto;

import com.roberto.seguridadLoginCustomJWT.models.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class NewUserDto {

    @NotBlank(message = "Username name cannot be empty.")
    private String username;
    @NotBlank(message = "Password cannot be empty.")
    private String password;
    @NotBlank(message = "Surname cannot be empty.")
    private String surname;
    @Email(message = "Email incorrect.")
    @NotBlank(message = "Email cannot be empty.")
    private String email;
    private Set<Role> roles = new HashSet<>();

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
