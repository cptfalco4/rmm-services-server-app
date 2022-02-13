package com.ninjarmm.rmmservicesserverapp.model.costs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
public class CustomerServiceCost {
    String customerId;
    String serviceName;
    Integer price;
}
