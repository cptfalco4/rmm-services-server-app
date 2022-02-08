package com.ninjarmm.rmmservicesserverapp.model.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "service_cost")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCost {
    @Id
    ServiceName serviceName;

    int price;
}
