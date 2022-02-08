package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.model.services.CustomerService;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.services.CustomerServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/devices/{customerId}")
public class ServiceController {
    @Autowired
    CustomerServicesService service;

    @GetMapping
    private Flux<ServiceName> getDevicesForACustomer(@PathVariable String customerId) {
        return service.getServicesByCustomerId(customerId);
    }

    @PostMapping
    private Mono<CustomerService> addDeviceToCustomer(@PathVariable String customerId, @RequestBody ServiceName serviceName) {
        return service.addServiceToCustomerId(customerId, serviceName);
    }

    @DeleteMapping("/{serviceName}")
    private Mono<Void> deleteDeviceFromCustomerId(@PathVariable String customerId, @PathVariable ServiceName serviceName) {
        return service.deleteDeviceFromCustomer(customerId, serviceName);
    }
}
