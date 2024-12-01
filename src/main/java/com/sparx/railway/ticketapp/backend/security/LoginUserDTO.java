package com.sparx.railway.ticketapp.backend.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class LoginUserDTO {
    private String email;

    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}