package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceNameDoesNotExistException extends RuntimeException{
    public ServiceNameDoesNotExistException(String value) {
        super(String.format("%s is not a valid Service Name. Valid services include 'Antivirus', 'Cloudberry', 'PSA', and 'TeamViewer'", value));
    }
}
