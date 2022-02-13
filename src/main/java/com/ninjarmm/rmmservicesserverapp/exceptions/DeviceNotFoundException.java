package com.ninjarmm.rmmservicesserverapp.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String customerId, String deviceId) {
        super(String.format("Device with id %s not found for customer id %s", deviceId, customerId));
    }
}
