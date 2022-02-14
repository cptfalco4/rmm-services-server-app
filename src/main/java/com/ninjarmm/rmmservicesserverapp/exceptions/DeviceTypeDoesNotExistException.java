package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeviceTypeDoesNotExistException extends RuntimeException {
    public DeviceTypeDoesNotExistException(String value) {
        super(String.format("%s is not a valid Device Type. Please enter 'Windows Workstation', 'Windows Server', or 'Mac'", value));
    }
}
