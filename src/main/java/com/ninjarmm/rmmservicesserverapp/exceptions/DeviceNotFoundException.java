package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String customerId, String deviceId) {
        super(String.format("Device with id %s not found for customer id %s", deviceId, customerId));
    }
}
