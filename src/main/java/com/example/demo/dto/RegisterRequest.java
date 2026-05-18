package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Username wymagany")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Hasło wymagane")
    @Size(min = 6, message = "Min 6 znaków")
    private String password;

    @Email(message = "Niepoprawny email")
    @NotBlank(message = "Email wymagany")
    private String email;

    @NotBlank(message = "Rola wymagana (USER/ADMIN)")
    private String role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}