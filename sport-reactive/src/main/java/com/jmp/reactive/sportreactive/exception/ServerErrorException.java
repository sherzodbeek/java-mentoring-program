package com.jmp.reactive.sportreactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServerErrorException extends ResponseStatusException {

    public ServerErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Sport name should be unique");
    }
}
