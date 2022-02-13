package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerHasNoDevicesRegisteredException extends RuntimeException {
    public CustomerHasNoDevicesRegisteredException(String message) {
        super(message);
    }
}
