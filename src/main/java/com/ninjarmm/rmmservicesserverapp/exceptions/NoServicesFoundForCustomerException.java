package com.ninjarmm.rmmservicesserverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoServicesFoundForCustomerException extends RuntimeException {
    public NoServicesFoundForCustomerException(String customerId) {
        super(String.format("Customer with id %s has no services registered", customerId));
    }
}
