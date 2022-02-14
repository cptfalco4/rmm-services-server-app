package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.models.costs.ServiceCost;
import org.springframework.data.repository.CrudRepository;

public interface ServiceCostRepository extends CrudRepository<ServiceCost, String> {
}
