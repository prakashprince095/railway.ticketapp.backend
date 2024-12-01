package com.sparx.railway.ticketapp.backend.security;

import com.sparx.railway.ticketapp.backend.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthenticationService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailUtil emailUtil;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

//    public AuthenticationService(
//            UserRepository userRepository,
//            AuthenticationManager authenticationManager,
//            PasswordEncoder passwordEncoder
//    ) {
//        logger.info("AuthenticationService constructor is being invoked ");
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public User signup(RegisterUserDTO input) {
        try{
            logger.info("the sinup method of the Authentication Service is being called ");
            User user = new User();
            user.setFullName(input.getFullName());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setEmail(input.getEmail());


            User savedUser= userRepository.save(user);
            emailUtil.sendWelcomeEmail(savedUser.getFullName(), savedUser.getEmail());
            return savedUser;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    public User authenticate(LoginUserDTO input) {
        logger.info("the authenticcate method of the Authentication Service is being called ");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
//        return userRepository.findByEmail(input.getEmail());
    }
}
