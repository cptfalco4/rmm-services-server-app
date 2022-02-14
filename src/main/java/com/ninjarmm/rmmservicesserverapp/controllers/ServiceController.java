package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.models.services.Service;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceNameDto;
import com.ninjarmm.rmmservicesserverapp.services.CustomerServicesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/services")
public class ServiceController {
    private CustomerServicesService service;

    public ServiceController(CustomerServicesService service) {
        this.service = service;
    }

    @GetMapping
    private Set<String> getServicesForACustomer(@PathVariable String customerId) {
        return service.getServicesByCustomerId(customerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private Service addServiceToCustomer(@PathVariable String customerId, @RequestBody ServiceNameDto serviceName) {
        return service.addServiceToCustomerId(customerId, serviceName);
    }

    @DeleteMapping("/{serviceName}")
    private void deleteServiceFromCustomer(@PathVariable String customerId, @PathVariable String serviceName) {
        service.deleteServiceFromCustomer(customerId, ServiceNameDto.find(serviceName));
    }
}
