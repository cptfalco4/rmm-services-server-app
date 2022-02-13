package com.ninjarmm.rmmservicesserverapp.model.costs;

import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;

@Table(name = "service_cost")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCost {
    @Id
    String serviceName;
    int price;
}
