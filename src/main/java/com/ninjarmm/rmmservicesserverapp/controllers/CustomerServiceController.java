package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.services.CustomerServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/services")
public class CustomerServiceController {
    @Autowired
    CustomerServicesService service;

    @GetMapping
    private Set<String> getDevicesForACustomer(@PathVariable String customerId) {
        return service.getServicesByCustomerId(customerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private Service addDeviceToCustomer(@PathVariable String customerId, @RequestBody ServiceName serviceName) {
        return service.addServiceToCustomerId(customerId, serviceName);
    }

    @DeleteMapping("/{serviceName}")
    private void deleteDeviceFromCustomerId(@PathVariable String customerId, @PathVariable ServiceName serviceName) {
        service.deleteDeviceFromCustomer(customerId, serviceName);
    }
}
