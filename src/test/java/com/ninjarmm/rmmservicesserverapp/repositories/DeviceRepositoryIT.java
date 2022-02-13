package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceRepositoryIT extends CustomerDependentRepositoryITBase{
    @Autowired
    private DeviceRepository testObject;

    @BeforeEach
    public void setup() {
        Device device1 = Device.builder()
                .id(new DeviceId(CUSTOMER_1, "device1"))
                .systemName("systemName")
                .type(DeviceType.MAC.getName())
                .customer(CUSTOMER_1_ENTITY)
                .build();
        Device device2 = Device.builder()
                .id(new DeviceId(CUSTOMER_1, "device2"))
                .systemName("systemName")
                .type(DeviceType.WINDOWS_SERVER.getName())
                .customer(CUSTOMER_1_ENTITY)
                .build();
        Device device3 = Device.builder()
                .id(new DeviceId(CUSTOMER_1, "device3"))
                .systemName("systemName")
                .type(DeviceType.MAC.getName())
                .customer(CUSTOMER_1_ENTITY)
                .build();
        Device device4 = Device.builder()
                .id(new DeviceId(CUSTOMER_2, "device1"))
                .systemName("systemName")
                .type(DeviceType.WINDOWS_WORKSTATION.getName())
                .customer(CUSTOMER_2_ENTITY)
                .build();

        testObject.save(device1);
        testObject.save(device2);
        testObject.save(device3);
        testObject.save(device4);
    }

    @Test
    void findAllById_CustomerId() {
        Set<Device> actualDevices = testObject.findAllById_CustomerId(CUSTOMER_1);
        assertEquals(3, actualDevices.size());
    }

    @Test
    void updateExistingDevice() {
        assertEquals(DeviceType.MAC.getName(), testObject.findById(new DeviceId(CUSTOMER_1, "device1")).get().getType());
        Device updatedDevice = Device.builder()
                .id(new DeviceId(CUSTOMER_1, "device1"))
                .systemName("systemName")
                .type(DeviceType.WINDOWS_SERVER.getName())
                .customer(CUSTOMER_1_ENTITY)
                .build();
        testObject.save(updatedDevice);
        assertEquals(DeviceType.WINDOWS_SERVER.getName(), testObject.findById(new DeviceId(CUSTOMER_1, "device1")).get().getType());
    }

    @Test
    void noDevicesFound() {
        Set<Device> actualDevices = testObject.findAllById_CustomerId("fakecustomer");
        assertEquals(Collections.emptySet(), actualDevices);
    }

    @AfterEach
    public void cleanUp(){
        testObject.deleteAll();
    }
}
