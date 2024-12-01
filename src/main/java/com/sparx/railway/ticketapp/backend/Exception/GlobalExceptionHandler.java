package com.sparx.railway.ticketapp.backend.Exception;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ServiceResponseDTO> handleUserNotFoundException(ResourceNotFoundException userNotFoundException){
        ServiceResponseDTO errorResponseDTO=ServiceResponseDTO.builder().response(userNotFoundException.getMessage()).status(userNotFoundException.getStatus()).code(userNotFoundException.getCode())
                .timestamp(Instant.now()).build();
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.OK);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ServiceResponseDTO> handleNoHandlerFoundException(NoHandlerFoundException ex){
        logger.error("handleNoHandlerFoundException is executed . No handler found for this request: {}", ex.getRequestURL());
        ServiceResponseDTO errorResponseDTO=ServiceResponseDTO.builder().response("No handler found for this request"+ex.getMessage()).status(HttpStatus.NOT_FOUND.value()).code(HttpStatus.OK.value())
                .timestamp(Instant.now()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);

    }

    // Handle AuthenticationException (401 for missing or invalid token)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ServiceResponseDTO> handleAuthenticationException(AuthenticationException ex) {
        logger.error("Authentication error: {}", ex.getMessage());
        ServiceResponseDTO errorResponseDTO = ServiceResponseDTO.builder()
                .response("Unauthorized: " + ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .code(HttpStatus.UNAUTHORIZED.value())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ServiceResponseDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("Method not allowed error: {}", ex.getMessage());

        ServiceResponseDTO errorResponseDTO = ServiceResponseDTO.builder()
                .response("Method Not Allowed: " + ex.getMessage())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }
    // Generic exception handler for any unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponseDTO> handleGenericException(Exception ex) {
        logger.error("Unhandled exception: ", ex); // Log the full exception for debugging

        // You can customize the error message as needed
        ServiceResponseDTO errorResponseDTO = ServiceResponseDTO.builder()
                .response("An unexpected error occurred: " + ex.getMessage())  // Customize error message
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(Instant.now())
                .build();

        // You can change the HttpStatus here as well if needed (default is 500 for internal errors)
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
