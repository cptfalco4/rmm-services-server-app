package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.models.services.Service;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceRepository extends CrudRepository<Service, ServiceId> {
    Set<Service> findAllById_CustomerId(String customerId);
}
