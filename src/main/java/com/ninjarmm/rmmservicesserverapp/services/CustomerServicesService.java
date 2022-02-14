package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoServicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.exceptions.ServiceAlreadyExistsException;
import com.ninjarmm.rmmservicesserverapp.model.costs.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceNameDto;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class CustomerServicesService {
    private final ServiceRepository serviceRepository;
    private final EntityManager entityManager;

    public CustomerServicesService(ServiceRepository serviceRepository, EntityManager entityManager) {
        this.serviceRepository = serviceRepository;
        this.entityManager = entityManager;
    }

    public Set<String> getServicesByCustomerId(String customerId) {
        Set<Service> customerServices = serviceRepository.findAllById_CustomerId(customerId);
        checkForEmpty(customerId, customerServices);
        return customerServices.stream()
                .map(customerService -> customerService.getId().getServiceName())
                .collect(Collectors.toSet());
    }

    public Service addServiceToCustomerId(String customerId, ServiceNameDto serviceName) {
        checkValidityOfRequest(customerId, serviceName);
        if (serviceName == ServiceNameDto.ANTIVIRUS) {
            Service windowsAntivirus = buildService(customerId, ServiceName.ANTIVIRUS_WINDOWS);
            Service macAntivirus = buildService(customerId, ServiceName.ANTIVIRUS_MAC);
            serviceRepository.saveAll(Arrays.asList(windowsAntivirus, macAntivirus));
            return windowsAntivirus;
        }
        return serviceRepository.save(buildService(customerId, ServiceName.find(serviceName.getName())));
    }

    public List<CustomerServiceCost> getServiceCostsByCustomerId(String customerId) {
        Query query = entityManager.createQuery("SELECT" +
                " NEW com.ninjarmm.rmmservicesserverapp.model.costs.CustomerServiceCost(s.id.customerId, c.serviceName, c.price)" +
                " FROM Service s INNER JOIN ServiceCost c" +
                " ON s.id.serviceName = c.serviceName WHERE s.id.customerId = ?1", CustomerServiceCost.class);
        query.setParameter(1, customerId);
        return query.getResultList();
    }

    public void deleteServiceFromCustomer(String customerId, ServiceNameDto serviceName) {
        if(serviceName == ServiceNameDto.ANTIVIRUS){
            serviceRepository.deleteById(new ServiceId(customerId, ServiceName.ANTIVIRUS_WINDOWS.getName()));
            serviceRepository.deleteById(new ServiceId(customerId, ServiceName.ANTIVIRUS_MAC.getName()));
        } else {
            serviceRepository.deleteById(new ServiceId(customerId, serviceName.getName()));
        }
    }

    private Service buildService(String customerId, ServiceName serviceName) {
        return Service.builder()
                .id(new ServiceId(customerId, serviceName.getName()))
                .customer(Customer.builder().id(customerId).build())
                .build();
    }

    private void checkValidityOfRequest(String customerId, ServiceNameDto serviceName) {
        Optional<Service> existingService = (serviceName == ServiceNameDto.ANTIVIRUS) ?
                serviceRepository.findById(new ServiceId(customerId, ServiceName.ANTIVIRUS_WINDOWS.getName())) :
                serviceRepository.findById(new ServiceId(customerId, serviceName.getName()));
        existingService.ifPresent(service -> {
            throw new ServiceAlreadyExistsException(
                    String.format("Service %s already exists for customer with id %s", serviceName.getName(), customerId));
        });
    }

    private void checkForEmpty(String customerId, Set<Service> customerServices) {
        if (customerServices.isEmpty()) {
            throw new NoServicesFoundForCustomerException(customerId);
        }
    }
}
