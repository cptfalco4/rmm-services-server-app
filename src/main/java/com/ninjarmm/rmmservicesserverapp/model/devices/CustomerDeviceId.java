package com.ninjarmm.rmmservicesserverapp.model.devices;

import lombok.Value;

import java.io.Serializable;

@Value
public class CustomerDeviceId implements Serializable {
    String customerId;
    String deviceId;
}
