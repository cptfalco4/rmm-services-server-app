package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeviceRepository extends CrudRepository<Device, DeviceId> {
    Set<Device> findAllById_CustomerId(String customerId);
}
