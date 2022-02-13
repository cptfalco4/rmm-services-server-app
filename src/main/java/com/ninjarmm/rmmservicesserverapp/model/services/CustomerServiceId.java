package com.ninjarmm.rmmservicesserverapp.model.services;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CustomerServiceId implements Serializable {
    String customerId;
    String serviceName;
}
