package com.ninjarmm.rmmservicesserverapp.models.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ninjarmm.rmmservicesserverapp.models.customers.Customer;
import lombok.*;

import javax.persistence.*;


@Table(name = "services")
@Entity
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
public class Service {
    @EmbeddedId
    private ServiceId id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    @MapsId("customerId")
    private Customer customer;
}
