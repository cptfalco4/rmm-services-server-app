package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDevicesFoundForCustomerException extends RuntimeException {
    public NoDevicesFoundForCustomerException(String customerId) {
        super(String.format("CustomerId %s has no registered devices", customerId));
    }
}
