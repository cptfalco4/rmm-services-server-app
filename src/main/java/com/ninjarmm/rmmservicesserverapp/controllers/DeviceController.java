package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.model.devices.CustomerDevice;
import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/devices/{customerId}")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping
    private Flux<Device> getDevicesForACustomer(@PathVariable String customerId) {
        return deviceService.getDevicesByCustomerId(customerId);
    }

    @PostMapping
    private Mono<CustomerDevice> addDeviceToCustomer(@PathVariable String customerId, @RequestBody DeviceDetails deviceDetails) {
        return deviceService.addDeviceToCustomerId(customerId, deviceDetails);
    }

    @DeleteMapping("/{deviceId}")
    private Mono<Void> deleteDeviceFromCustomerId(@PathVariable String customerId, @PathVariable String deviceId) {
        return deviceService.deleteDeviceFromCustomer(customerId, deviceId);
    }

    @PutMapping("/{deviceId}")
    private Mono<CustomerDevice> updateDeviceOnCustomer(@PathVariable String customerId, @PathVariable String deviceId,
                                                        @RequestBody DeviceDetails deviceDetails) {
        return deviceService.updateDeviceOnCustomer(customerId, deviceId, deviceDetails);
    }
}
