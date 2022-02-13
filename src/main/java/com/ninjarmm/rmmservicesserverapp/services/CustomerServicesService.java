package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;

import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class CustomerServicesService {
    private final ServiceRepository serviceRepository;

    public CustomerServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Set<String> getServicesByCustomerId(String customerId) {
        return new HashSet<>();

//                serviceRepository.findAllByCustomer_Id(customerId).stream()
//                .map(customerService -> customerService.getId().getServiceName())
//                .collect(Collectors.toSet());
    }

    public Service addServiceToCustomerId(String customerId, ServiceName serviceName) {
        return null;
//                serviceRepository.save(CustomerService.builder()
//                .id(new CustomerServiceId(customerId, serviceName.getName()))
//                .build());
    }

    public void deleteDeviceFromCustomer(String customerId, ServiceName serviceName) {
        serviceRepository.deleteById(new ServiceId(customerId, serviceName.getName()));
    }
}
