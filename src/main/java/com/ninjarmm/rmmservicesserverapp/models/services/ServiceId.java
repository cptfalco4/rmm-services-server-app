
package com.ninjarmm.rmmservicesserverapp.models.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceId implements Serializable {
    private static final long serialVersionUID = 8051522449291379380L;
    String customerId;
    String serviceName;
}
