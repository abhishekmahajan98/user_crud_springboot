package com.example.user_crud_springboot.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        CustomErrorResponse response = new CustomErrorResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        CustomErrorResponse response = new CustomErrorResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errors = new ArrayList<>();
        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            errors.add(error.getDefaultMessage());
        }
        CustomErrorResponse response = new CustomErrorResponse(new Date(),errors.get(0),request.getDescription(false));
        return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
    }

}
