package com.sparx.railway.ticketapp.backend.security;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private  HandlerExceptionResolver handlerExceptionResolver;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  UserDetailsService userDetailsService;

    private Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);

//    public JwtAuthenticationFilter(
//            JwtService jwtService,
//            UserDetailsService userDetailsService,
//            HandlerExceptionResolver handlerExceptionResolver
//    ) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//        this.handlerExceptionResolver = handlerExceptionResolver;
//    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("the do authentication filter is being called ");
        final String authHeader = request.getHeader("Authorization");
        logger.info("the data that is coming with the header is {}",request);
        logger.info("the do internal filter method is being executed,{}", authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("The control is in the else block means the token is not present");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else{
                    System.out.println("The control is in the else block means the token is not valid");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }

            }
            logger.info("the do filter method is being executed right now");
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            System.out.println("The control is in the else block means the token is not valid");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String token = extractToken(request);
//
//        if (token != null && jwtService.validateToken(token)) {
//            try {
//                // Extract the username from the token
//                String username = jwtService.extractUsername(token);
//
//                // Load the user details
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                // Validate token with the loaded user details
//                if (jwtService.isTokenValid(token, userDetails)) {
//                    // If token is valid, authenticate the user
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                } else {
//                    // Token is invalid, throw an exception to send 401 Unauthorized
//                    throw new AuthenticationException("Invalid token or expired token.") {};
//                }
//            } catch (AuthenticationException ex) {
//                // If there's an authentication exception, respond with 401 Unauthorized
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Unauthorized: " + ex.getMessage());
//                return;  // Don't proceed with the filter chain, immediately return the response
//            }
//        } else {
//            // Token is null or invalid, respond with 401 Unauthorized
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized: Invalid or missing token.");
//            return;  // Don't proceed with the filter chain
//        }
//
//        // Proceed with the filter chain if token is valid
//        filterChain.doFilter(request, response);
//    }
//
//    // Helper method to extract token from the Authorization header
//    private String extractToken(HttpServletRequest request) {
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);  // Remove "Bearer " prefix
//        }
//        return null;
//    }

}