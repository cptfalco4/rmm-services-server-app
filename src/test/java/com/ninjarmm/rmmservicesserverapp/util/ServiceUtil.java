package com.ninjarmm.rmmservicesserverapp.util;

import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceId;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceId;

public class ServiceUtil {
    public static Service buildService(String customerId, String serviceName) {
        return Service.builder()
                .id(new ServiceId(customerId, serviceName))
                .customer(Customer.builder().id(customerId).build())
                .build();
    }
}
