package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.models.devices.Device;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import com.ninjarmm.rmmservicesserverapp.util.DeviceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeviceRepositoryIT extends BaseIT {
    @Autowired
    private DeviceRepository testObject;

    @BeforeEach
    public void setup() {
        Device device1 = DeviceUtil.buildDevice(CUSTOMER_1, "device1", DeviceType.MAC);
        Device device2 = DeviceUtil.buildDevice(CUSTOMER_1, "device2", DeviceType.WINDOWS_SERVER);
        Device device3 = DeviceUtil.buildDevice(CUSTOMER_1, "device3", DeviceType.MAC);
        Device device4 = DeviceUtil.buildDevice(CUSTOMER_2, "device1", DeviceType.WINDOWS_WORKSTATION);

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
        Device updatedDevice = DeviceUtil.buildDevice(CUSTOMER_1, "device1", DeviceType.WINDOWS_SERVER);
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
