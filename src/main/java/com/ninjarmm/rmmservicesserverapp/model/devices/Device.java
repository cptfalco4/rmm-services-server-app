
package com.ninjarmm.rmmservicesserverapp.model.devices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @MapsId("customerId")
    private Customer customer;

}


