package com.ninjarmm.rmmservicesserverapp.models.costs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "service_cost")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCost {
    @Id
    String serviceName;
    int price;
}
