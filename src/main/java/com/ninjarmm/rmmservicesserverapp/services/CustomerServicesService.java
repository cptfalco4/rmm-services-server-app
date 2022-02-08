package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.model.services.CustomerService;
import com.ninjarmm.rmmservicesserverapp.model.services.CustomerServiceId;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerServiceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServicesService {
    private final CustomerServiceRepository serviceRepository;

    public CustomerServicesService(CustomerServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Flux<ServiceName> getServicesByCustomerId(String customerId) {
        return serviceRepository.findAllById_CustomerId(customerId)
                .map(customerService -> customerService.getId().getServiceName());
    }

    public Mono<CustomerService> addServiceToCustomerId(String customerId, ServiceName serviceName) {
        return serviceRepository.save(CustomerService.builder()
                .id(new CustomerServiceId(customerId, serviceName))
                .build());
    }

    public Mono<Void> deleteDeviceFromCustomer(String customerId, ServiceName serviceName) {
        return serviceRepository.deleteById(new CustomerServiceId(customerId, serviceName));
    }
}
