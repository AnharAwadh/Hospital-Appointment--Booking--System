package com.example.hospitalappointmentbookingsystem.advice;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.excption.InvalidIdException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviseHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message=methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));

    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Api> handleDataIntegrity(DataIntegrityViolationException dataIntegrityViolationException){
        String message=dataIntegrityViolationException.getRootCause().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
    }

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<Api> handleDataIntegrity(InvalidIdException invalidIDException){
        String message=invalidIDException.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api> handleException(Exception exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("SERVER ERROR !",500));
    }



}
