package com.ninjarmm.rmmservicesserverapp.model.customers;

import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Customer {
    @Id
    private String id;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Device.class)
    private Set<Device> devices;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Service.class)
    private Set<Service> services;
}


