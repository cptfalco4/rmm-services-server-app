package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.services.CustomerService;
import com.ninjarmm.rmmservicesserverapp.model.services.CustomerServiceId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerServiceRepository extends ReactiveCrudRepository<CustomerService, CustomerServiceId> {
    Flux<CustomerService> findAllById_CustomerId(String customerId);
}
