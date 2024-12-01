package com.sparx.railway.ticketapp.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger.info("ApplicationConfiguration class initialized");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("UserDetailsService class initialized");

        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        userRepository.findByEmail(username);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        logger.info("BCryptPasswordEncoder class initialized");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        logger.info("AuthenticationManager class initialized");
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        logger.info("AuthenticationProvider class initialized");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("The security filter chain is being executed");

        // Disable CSRF as we're using JWT and are stateless
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",

                                "/swagger-resources/**", "/webjars/**",
                                "/auth/signup", "/auth/login", "/users/**", "/demo/test"
                        ).permitAll()  // Allow Swagger UI and related resources publicly
                        .anyRequest().authenticated()  // Secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults())  // Enable Basic Auth (Optional, may be removed if using JWT only)
                .formLogin().disable()  // Disable form login as we don't need a login form for Swagger UI
                .authenticationManager(authenticationManager(null)) // Pass the AuthenticationManager
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Stateless (JWT-based authentication)

        // Ensure JWT filter is not applied to Swagger and public resources
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        logger.info("The security filter chain is being executed");
//
//        http.csrf(csrf -> csrf.disable())
//
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(
//                                "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**","/demo/test"
//                                ,"/auth/signup", "/auth/login", "/users/**").permitAll()  // Public endpoints
//                        .anyRequest().authenticated()  // Secure all other endpoints
//                )
//                .httpBasic(Customizer.withDefaults())  // Basic Auth
//                .formLogin(Customizer.withDefaults())  // Form login
//                .authenticationManager(authenticationManager(null)) // Pass the AuthenticationManager
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Disable sessions
//
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();  // Assuming JwtAuthenticationFilter is your custom filter
    }
}
