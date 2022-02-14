package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException(String userId) {
        super(String.format("%s is not a known registered user", userId));
    }
}
