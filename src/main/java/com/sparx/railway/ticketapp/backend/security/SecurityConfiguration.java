package com.sparx.railway.ticketapp.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private Logger logger= LoggerFactory.getLogger(SecurityConfiguration.class);

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        logger.info("SecurityConfiguration class initialized");
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        logger.info("the security filter chain is being executed ");
//        http
//                .csrf(csrf -> csrf.disable()) // Optional: Disable CSRF for simplicity in non-browser clients
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/signup", "/auth/login").permitAll() // Allow both endpoints
//                        .anyRequest().authenticated() // Secure all other endpoints
//                )
//                .httpBasic(Customizer.withDefaults()) // Allow HTTP Basic authentication
//                .formLogin(Customizer.withDefaults()); // Allow form login
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Disable CSRF for simplicity, enable in production
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll() // Allow public access to /public/**
//                .anyRequest().authenticated() // All other requests require authentication
//                .and()
//                .formLogin() // Enable form login
//                .loginPage("/login") // Custom login page
//                .permitAll() // Allow access to login page
//                .and()
//                .logout() // Enable logout
//                .permitAll(); // Allow access to logout
//
//        return http.build(); // Build and return the SecurityFilterChain
//    }
////    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/sinup").permitAll()
//                        .requestMatchers("/auth/login").permitAll()
//
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//                return http.build();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        logger.info("the cors Configuaration source  is being executed ");

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8004"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
