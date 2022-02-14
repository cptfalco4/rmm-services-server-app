package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.DeviceNotFoundException;
import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.models.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.models.devices.Device;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerRepository;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;

    public DeviceService(DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
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

    public Device addDeviceToCustomerId(String customerId, DeviceDetails deviceDetails) {
        prepareCustomer(customerId);
        return saveDeviceByDetailsAndId(customerId, UUID.randomUUID().toString(), deviceDetails);
    }

    private void prepareCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            customerRepository.save(Customer.builder()
                    .id(customerId)
                    .build());
        }
    }

    public void deleteDeviceFromCustomer(String customerId, String deviceId) {
        deviceRepository.deleteById(new DeviceId(customerId, deviceId));
    }

    public Device updateDeviceOnCustomer(String customerId, String deviceId, DeviceDetails deviceDetails) {
        checkBeforeUpdatingDevice(customerId, deviceId);
        return saveDeviceByDetailsAndId(customerId, deviceId, deviceDetails);
    }

    private void checkBeforeUpdatingDevice(String customerId, String deviceId) {
        deviceRepository.findById(new DeviceId(customerId, deviceId))
                .orElseThrow(() -> new DeviceNotFoundException(customerId, deviceId));
    }

    private void checkIfEmpty(String customerId, Set<Device> customerDevices) {
        if (customerDevices.isEmpty()) {
            throw new NoDevicesFoundForCustomerException(customerId);
        }
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
