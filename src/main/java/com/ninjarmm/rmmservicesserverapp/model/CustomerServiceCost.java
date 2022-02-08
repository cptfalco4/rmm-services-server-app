package com.ninjarmm.rmmservicesserverapp.model;

import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "customer_services_costs")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CustomerServiceCost {
    @Id
    String customerId;

    ServiceName serviceName;
    int price;
}
