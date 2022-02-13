package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Set<DeviceDto> getDevicesByCustomerId(String customerId) {
        Set<Device> customerDevices = deviceRepository.findAllById_CustomerId(customerId);
        checkIfEmpty(customerId, customerDevices);
        return customerDevices.stream()
                .map(customerDevice -> DeviceDto.builder()
                        .deviceId(customerDevice.getId().getDeviceId())
                        .systemName(customerDevice.getSystemName())
                        .type(customerDevice.getType())
                        .build()).collect(Collectors.toSet());
    }

    private void checkIfEmpty(String customerId, Set<Device> customerDevices) {
        if(customerDevices.isEmpty()){
            throw new NoDevicesFoundForCustomerException(customerId);
        }
    }

    public Device addDeviceToCustomerId(String customerId, DeviceDetails deviceDetails) {
        return saveDeviceByDetailsAndId(customerId, UUID.randomUUID().toString(), deviceDetails);
    }

    public void deleteDeviceFromCustomer(String customerId, String deviceId) {
        deviceRepository.deleteById(new DeviceId(customerId, deviceId));
    }

    public Device updateDeviceOnCustomer(String customerId, String deviceId, DeviceDetails deviceDetails) {
        return saveDeviceByDetailsAndId(customerId, deviceId, deviceDetails);
    }

    private Device saveDeviceByDetailsAndId(String customerId, String deviceId, DeviceDetails deviceDetails) {
        return deviceRepository.save(Device.builder()
                .id(new DeviceId(customerId, deviceId))
                .systemName(deviceDetails.getSystemName())
                .type(deviceDetails.getType().getName())
                .customer(Customer.builder().id(customerId).build())
                .build());
    }

}
