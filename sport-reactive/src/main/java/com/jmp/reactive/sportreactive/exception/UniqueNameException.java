package com.jmp.reactive.sportreactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UniqueNameException extends ResponseStatusException {

    public UniqueNameException() {
        super(HttpStatus.BAD_REQUEST, "Sport name should be unique");
    }
}
