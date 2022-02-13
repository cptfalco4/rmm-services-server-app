package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoRoleOnUserException extends RuntimeException {
    public NoRoleOnUserException(String username) {
        super(String.format("User with username %s has no roles", username));
    }
}
