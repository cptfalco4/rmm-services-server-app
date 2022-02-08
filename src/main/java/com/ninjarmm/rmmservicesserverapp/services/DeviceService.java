package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDevice;
import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerDeviceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DeviceService {
    private final CustomerDeviceRepository deviceRepository;

    public DeviceService(CustomerDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Flux<Device> getDevicesByCustomerId(String customerId) {
        return deviceRepository.findAllById_CustomerId(customerId)
                .map(customerDevice -> Device.builder()
                        .deviceId(customerDevice.getId().getDeviceId())
                        .systemName(customerDevice.getSystemName())
                        .type(customerDevice.getType())
                        .build());
    }

    public Mono<CustomerDevice> addDeviceToCustomerId(String customerId, DeviceDetails deviceDetails) {
        return saveDeviceByDetailsAndId(customerId, UUID.randomUUID().toString(), deviceDetails);
    }

    public Mono<Void> deleteDeviceFromCustomer(String customerId, String deviceId) {
        return deviceRepository.deleteById(new CustomerDeviceId(customerId, deviceId));
    }

    public Mono<CustomerDevice> updateDeviceOnCustomer(String customerId, String deviceId, DeviceDetails deviceDetails) {
        return saveDeviceByDetailsAndId(customerId, deviceId, deviceDetails);
    }

    private Mono<CustomerDevice> saveDeviceByDetailsAndId(String customerId, String deviceId, DeviceDetails deviceDetails) {
        return deviceRepository.save(CustomerDevice.builder()
                .id(new CustomerDeviceId(customerId, deviceId))
                .systemName(deviceDetails.getSystemName())
                .type(deviceDetails.getType())
                .build());
    }

}
