package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoServicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.exceptions.ServiceAlreadyExistsException;
import com.ninjarmm.rmmservicesserverapp.models.costs.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.models.costs.ServiceCost;
import com.ninjarmm.rmmservicesserverapp.models.services.Service;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceNameDto;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceCostRepository;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServicesServiceIT extends BaseIT {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCostRepository serviceCostRepository;
    @Autowired
    private CustomerServicesService testObject;

    @BeforeEach
    public void setUp(){
        serviceRepository.save(new Service(new ServiceId(CUSTOMER_1, ServiceName.PSA.getName()),
                CUSTOMER_1_ENTITY));
        serviceRepository.save(new Service(new ServiceId(CUSTOMER_1, ServiceName.CLOUDBERRY.getName()),
                CUSTOMER_1_ENTITY));
    }

    @Test
    public void getServicesByCustomerId(){
        Set<String> actualServices = testObject.getServicesByCustomerId(CUSTOMER_1);
        Set<String> expectedResult = Sets.newHashSet(Arrays.asList(ServiceName.PSA.getName(),
                ServiceName.CLOUDBERRY.getName()));
        assertEquals(expectedResult, actualServices);
    }

    @Test
    public void getServicesByCustomerIdWithNoServicesRegistered(){
        assertThrows(NoServicesFoundForCustomerException.class,
                ()-> testObject.getServicesByCustomerId(CUSTOMER_2),
                "Customer with id customer2 has no services registered");
    }

    @Test
    public void addServiceToCustomerId(){
        testObject.addServiceToCustomerId(CUSTOMER_1, ServiceNameDto.TEAM_VIEWER);
        Set<String> expectedResult = Sets.newHashSet(Arrays.asList(ServiceName.PSA.getName(),
                ServiceName.CLOUDBERRY.getName(),
                ServiceName.TEAM_VIEWER.getName()));
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_1));
    }

    @Test
    public void addServiceToCustomerId_Antivirus(){
        testObject.addServiceToCustomerId(CUSTOMER_2, ServiceNameDto.ANTIVIRUS);
        Set<String> expectedResult = Collections.singleton("Antivirus");
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_2));
    }

    @Test
    public void addServiceToCustomerThatAlreadyExists(){
        serviceRepository.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.PSA.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        assertThrows(ServiceAlreadyExistsException.class,
                ()-> testObject.addServiceToCustomerId(CUSTOMER_1, ServiceNameDto.PSA),
                "Service PSA already exists for customer with id customer1");
    }

    @Test
    public void deleteServiceFromCustomer(){
        testObject.deleteServiceFromCustomer(CUSTOMER_1, ServiceNameDto.PSA);
        Set<String> expectedResult = Sets.newHashSet(Collections.singleton(ServiceName.CLOUDBERRY.getName()));
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_1));
    }

    @Test
    public void deleteServiceFromCustomer_Antivirus(){
        testObject.addServiceToCustomerId(CUSTOMER_1, ServiceNameDto.ANTIVIRUS);
        Set<String> expectedServices = Sets.newHashSet(Arrays.asList(
                ServiceName.CLOUDBERRY.getName(),
                ServiceName.PSA.getName(),
                "Antivirus"));
        assertEquals(expectedServices, testObject.getServicesByCustomerId(CUSTOMER_1));
        testObject.deleteServiceFromCustomer(CUSTOMER_1, ServiceNameDto.ANTIVIRUS);

        Set<String> expectedResult = Sets.newHashSet(Arrays.asList(
                ServiceName.CLOUDBERRY.getName(),
                ServiceName.PSA.getName()));
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_1));
    }

    @Test
    public void getServiceCostsByCustomerId(){
        serviceCostRepository.save(new ServiceCost(ServiceName.PSA.getName(), 2));
        serviceCostRepository.save(new ServiceCost(ServiceName.CLOUDBERRY.getName(), 7));

        CustomerServiceCost customerServiceCost1 = new CustomerServiceCost(CUSTOMER_1, ServiceName.PSA.getName(), 2);
        CustomerServiceCost customerServiceCost2 = new CustomerServiceCost(CUSTOMER_1, ServiceName.CLOUDBERRY.getName(), 7);
        List<CustomerServiceCost> actualServiceCostsByCustomer = testObject.getServiceCostsByCustomerId(CUSTOMER_1);
        assertTrue(actualServiceCostsByCustomer.containsAll(Arrays.asList(customerServiceCost1, customerServiceCost2)));
    }

    @AfterEach
    public void cleanUp(){
        serviceRepository.deleteAll();
        serviceCostRepository.deleteAll();
    }
}
