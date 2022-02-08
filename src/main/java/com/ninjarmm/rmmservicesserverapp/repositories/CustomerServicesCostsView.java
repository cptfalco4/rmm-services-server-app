package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDevice;
import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDeviceId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerServicesCostsView extends ReactiveCrudRepository<CustomerServiceCost, String> {
    Flux<CustomerServiceCost> findAllByCustomerId(String customerId);
}
