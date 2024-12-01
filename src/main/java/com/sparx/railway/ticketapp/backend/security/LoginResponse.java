package com.sparx.railway.ticketapp.backend.security;

import lombok.*;



@Setter
@Getter
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;

    public LoginResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
