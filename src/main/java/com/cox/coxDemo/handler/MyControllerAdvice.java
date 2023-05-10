package com.cox.coxDemo.handler;

import com.cox.coxDemo.exception.EmptyInputException;
import com.cox.coxDemo.exception.InvalidInputException;
import com.cox.coxDemo.exception.NoValueFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<?> handleEmptyInput(EmptyInputException exception){
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoValueFoundException.class)
    public ResponseEntity<?> handleEmptyInput(NoValueFoundException exception){
        return new ResponseEntity<>("No values found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(MethodNotAllowedException ex) {
        return new ResponseEntity<String>("Method not allowed, Please change the method type",HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidInputException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
