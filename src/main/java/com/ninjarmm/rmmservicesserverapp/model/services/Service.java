package com.ninjarmm.rmmservicesserverapp.model.services;

import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import lombok.*;

import javax.persistence.*;


@Table(name = "services")
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@ToString
public class Service {
    @EmbeddedId
    private ServiceId id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    @MapsId("customerId")
    private Customer customer;
}
