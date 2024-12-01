package com.sparx.railway.ticketapp.backend.controller;


import com.sparx.railway.ticketapp.backend.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

//    private  final JwtService jwtService;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationService authenticationService;



    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken =  jwtService.generateToken(authenticatedUser);

//        LoginResponse loginResponse=LoginResponse.builder().token(jwtToken)
//                .expiresIn(jwtService.getExpirationTime())
//                .build();

        return ResponseEntity.ok(new LoginResponse(jwtToken, jwtService.getExpirationTime()));
    }
}
