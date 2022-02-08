package com.ninjarmm.rmmservicesserverapp.model.services;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Table(name = "customer_service")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CustomerService {
    @EmbeddedId
    CustomerServiceId id;
}
