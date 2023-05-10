package com.cox.coxDemo.handler;

import com.cox.coxDemo.exception.EmptyInputException;
import com.cox.coxDemo.exception.NoValueFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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

//    @Override
//    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
//        return new ResponseEntity<Object>("Please change your http request", HttpStatus.BAD_REQUEST);
//    }
}
