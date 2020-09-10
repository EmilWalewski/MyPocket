package com.mypocket.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException ex) {

        ErrorMessage apiError = new ErrorMessage(HttpStatus.NOT_FOUND, ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
<<<<<<< HEAD
<<<<<<< HEAD
    protected ResponseEntity<Object> badCredentials(BadCredentialsException ex) {
=======
    protected ResponseEntity<Object> handleEntityNotFound(BadCredentialsException ex) {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
    protected ResponseEntity<Object> handleEntityNotFound(BadCredentialsException ex) {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

        ErrorMessage apiError = new ErrorMessage(HttpStatus.NOT_FOUND, ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
