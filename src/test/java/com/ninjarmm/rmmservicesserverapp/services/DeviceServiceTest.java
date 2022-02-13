package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.DeviceNotFoundException;
import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.model.devices.*;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    private static final String CUSTOMER_ID = "customer1";
    @Mock
    private DeviceRepository deviceRepository;

    private DeviceService testObject;
    private String deviceId;
    private String systemName;
    private String type;
    private Device device;

    @BeforeEach
    public void setUp() {
        testObject = new DeviceService(deviceRepository);
        deviceId = "device1";
        systemName = "systemName";
        type = DeviceType.WINDOWS_WORKSTATION.getName();
        device = Device.builder()
                .id(new DeviceId(CUSTOMER_ID, deviceId))
                .type(type)
                .systemName(systemName)
                .customer(Customer.builder().id(CUSTOMER_ID).build())
                .build();
    }

    @Test
    public void getDevicesByCustomerId() {
        Set<Device> singleDevice = Collections.singleton(device);
        given(deviceRepository.findAllById_CustomerId(CUSTOMER_ID))
                .willReturn(singleDevice);
        DeviceDto actualDeviceReturned = testObject.getDevicesByCustomerId(CUSTOMER_ID)
                .stream()
                .findFirst()
                .get();
        DeviceDto expectedDto = DeviceDto.builder()
                .deviceId(deviceId)
                .systemName(systemName)
                .type(type)
                .build();
        assertEquals(expectedDto, actualDeviceReturned);
    }

    @Test
    public void getDevicesByCustomerId_customerDNE() {
        assertThrows(NoDevicesFoundForCustomerException.class,
                ()->testObject.getDevicesByCustomerId("customer2"),
                "CustomerId customer1 has no registered devices");
    }

    @Test
    void addDevice(){
        given(deviceRepository.save(any())).willReturn(device);
        assertEquals(device, testObject.addDeviceToCustomerId(CUSTOMER_ID, new DeviceDetails("system1", DeviceType.WINDOWS_WORKSTATION)));
    }

    @Test
    public void updatingDevice() {
        Device updatedDevice = Device.builder()
                .id(new DeviceId(CUSTOMER_ID, deviceId))
                .systemName("system2")
                .type(DeviceType.MAC.getName())
                .customer(Customer.builder().id(CUSTOMER_ID).build())
                .build();
        given(deviceRepository.findById(any())).willReturn(Optional.of(device));
        given(deviceRepository.save(any())).willReturn(updatedDevice);

        assertEquals(updatedDevice, testObject.updateDeviceOnCustomer(CUSTOMER_ID, "device1", new DeviceDetails("system2", DeviceType.MAC)));
    }

    @Test
    public void updatingDevice_DeviceDNE() {
        assertThrows(DeviceNotFoundException.class,
                ()->testObject.updateDeviceOnCustomer("customer2", "device1", null),
                "Device with id device1 not found for customer id customer2");
    }

}
