package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCost extends ReactiveCrudRepository<ServiceCost, ServiceName> {
}
