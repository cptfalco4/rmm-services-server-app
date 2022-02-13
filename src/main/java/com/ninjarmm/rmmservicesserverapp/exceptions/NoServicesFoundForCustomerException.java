package com.ninjarmm.rmmservicesserverapp.exceptions;

public class NoServicesFoundForCustomerException extends RuntimeException {
    public NoServicesFoundForCustomerException(String message) {
        super(message);
    }
}
