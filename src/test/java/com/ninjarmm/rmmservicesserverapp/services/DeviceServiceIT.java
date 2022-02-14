package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import com.ninjarmm.rmmservicesserverapp.util.DeviceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeviceServiceIT extends BaseIT {
    @Autowired
    private DeviceService testObject;
    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void deleteDevice(){
        deviceRepository.save(DeviceUtil.buildDevice(CUSTOMER_1, "d1", DeviceType.WINDOWS_WORKSTATION));
        deviceRepository.save(DeviceUtil.buildDevice(CUSTOMER_1, "d2", DeviceType.WINDOWS_WORKSTATION));
        deviceRepository.save(DeviceUtil.buildDevice(CUSTOMER_1, "d3", DeviceType.WINDOWS_WORKSTATION));
        deviceRepository.save(DeviceUtil.buildDevice(CUSTOMER_1, "d4", DeviceType.WINDOWS_WORKSTATION));

        testObject.deleteDeviceFromCustomer(CUSTOMER_1, "d1");
        assertEquals(3, deviceRepository.findAllById_CustomerId(CUSTOMER_1).size());
    }
}
