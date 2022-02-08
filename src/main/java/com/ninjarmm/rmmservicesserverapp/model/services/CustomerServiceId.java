package com.ninjarmm.rmmservicesserverapp.model.services;

import lombok.Value;

import java.io.Serializable;

@Value
public class CustomerServiceId implements Serializable {
    String customerId;
    ServiceName serviceName;
}
