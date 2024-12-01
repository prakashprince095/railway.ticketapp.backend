package com.sparx.railway.ticketapp.backend.util;

import com.sparx.railway.ticketapp.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private final JwtService jwtService;

    public String getUserNameFromTheToken(String token){

        return jwtService.extractUsername(token.substring(7));
    }
}
