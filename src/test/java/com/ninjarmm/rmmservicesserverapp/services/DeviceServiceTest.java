package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    private static final String CUSTOMER_ID = "customer1";
    @Mock
    private DeviceRepository deviceRepository;

    private DeviceService testObject;

    @BeforeEach
    public void setUp() {
        testObject = new DeviceService(deviceRepository);
    }

    @Test
    public void getDevicesByCustomerId() {
        String deviceId = "device1";
        String systemName = "systemName";
        String type = "type";
        Device device = Device.builder()
                .id(new DeviceId(CUSTOMER_ID, deviceId))
                .type(type)
                .systemName(systemName)
                .customer(Customer.builder().id(CUSTOMER_ID).build())
                .build();
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
                ()->testObject.getDevicesByCustomerId(CUSTOMER_ID),
                "CustomerId customer1 has no registered devices");
    }

}
