package com.ninjarmm.rmmservicesserverapp.exceptions;

public class NoServicesFoundForCustomerException extends RuntimeException {
    public NoServicesFoundForCustomerException(String customerId) {
        super(String.format("Customer with id %s has no services registered", customerId));
    }
}
