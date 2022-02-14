package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.model.devices.Device;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping
    private Set<DeviceDto> getDevicesForACustomer(@PathVariable String customerId) {
        return deviceService.getDevicesByCustomerId(customerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private Device addDeviceToCustomer(@PathVariable String customerId, @RequestBody DeviceDetails deviceDetails) {
        return deviceService.addDeviceToCustomerId(customerId, deviceDetails);
    }

    @DeleteMapping("/{deviceId}")
    private void deleteDeviceFromCustomerId(@PathVariable String customerId, @PathVariable String deviceId) {
        deviceService.deleteDeviceFromCustomer(customerId, deviceId);
    }

    @PutMapping("/{deviceId}")
    private Device updateDeviceOnCustomer(@PathVariable String customerId, @PathVariable String deviceId,
                                          @RequestBody DeviceDetails deviceDetails) {
        return deviceService.updateDeviceOnCustomer(customerId, deviceId, deviceDetails);
    }
}
