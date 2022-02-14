package com.ninjarmm.rmmservicesserverapp.models.costs;

import lombok.Value;

@Value
public class CustomerServiceCost {
    String customerId;
    String serviceName;
    Integer price;
}
