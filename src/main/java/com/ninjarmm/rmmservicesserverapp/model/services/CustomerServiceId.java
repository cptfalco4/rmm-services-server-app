package com.ninjarmm.rmmservicesserverapp.model.services;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CustomerServiceId implements Serializable {
    private static final long serialVersionUID = -3664831822020884997L;

    String customerId;
    String serviceName;
}
