package com.ninjarmm.rmmservicesserverapp.util;

import com.ninjarmm.rmmservicesserverapp.models.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.models.devices.Device;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;

public class DeviceUtil {
    public static Device buildDevice(String customerId, String deviceId, DeviceType deviceType) {
        return Device.builder()
                .id(new DeviceId(customerId, deviceId))
                .systemName("systemName")
                .type(deviceType.getName())
                .customer(Customer.builder().id(customerId).build())
                .build();
    }
}
