
package com.ninjarmm.rmmservicesserverapp.models.devices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjarmm.rmmservicesserverapp.models.customers.Customer;
import lombok.*;

import javax.persistence.*;

@Table(name = "devices")
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Device {
    @EmbeddedId
    DeviceId id;

    String systemName;
    String type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @MapsId("customerId")
    private Customer customer;

}


