package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDevice;
import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDeviceId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerDeviceRepository extends ReactiveCrudRepository<CustomerDevice, CustomerDeviceId> {
    Flux<CustomerDevice> findAllById_CustomerId(String customerId);
}
