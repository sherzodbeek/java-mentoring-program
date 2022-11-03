package com.jmp.reactive.sportreactive.controller;

import com.jmp.reactive.sportreactive.exception.ServerErrorException;
import com.jmp.reactive.sportreactive.exception.UniqueNameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UniqueNameException.class)
    ResponseEntity<String> uniqueNameException(UniqueNameException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(ServerErrorException.class)
    ResponseEntity<String> serverErrorException(ServerErrorException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
