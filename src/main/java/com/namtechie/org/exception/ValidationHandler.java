package com.namtechie.org.exception;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
    // define this class run when have the exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidation(MethodArgumentNotValidException exception) {
        String message = "";

        //when have the exception define => get the message
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            //name, studentCode, score
            message += fieldError.getField() + ":" + fieldError.getDefaultMessage();
        }
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntity.class)
    public ResponseEntity handleDuplicate(DuplicateEntity exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}