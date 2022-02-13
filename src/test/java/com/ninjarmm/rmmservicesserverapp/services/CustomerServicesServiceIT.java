package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoServicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.exceptions.ServiceAlreadyExistsException;
import com.ninjarmm.rmmservicesserverapp.model.costs.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.model.costs.ServiceCost;
import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerDependentRepositoryITBase;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceCostRepository;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServicesServiceIT extends CustomerDependentRepositoryITBase {
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
        serviceRepository.save(new Service(new ServiceId(CUSTOMER_1, ServiceName.ANTIVIRUS_MAC.getName()),
                CUSTOMER_1_ENTITY));
    }

    @Test
    public void getServicesByCustomerId(){
        Set<String> actualServices = testObject.getServicesByCustomerId(CUSTOMER_1);
        Set<String> expectedResult = Sets.newHashSet(Arrays.asList(ServiceName.PSA.getName(),
                ServiceName.ANTIVIRUS_MAC.getName()));
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
        testObject.addServiceToCustomerId(CUSTOMER_1, ServiceName.TEAM_VIEWER);
        Set<String> expectedResult = Sets.newHashSet(Arrays.asList(ServiceName.PSA.getName(),
                ServiceName.ANTIVIRUS_MAC.getName(),
                ServiceName.TEAM_VIEWER.getName()));
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_1));
    }

    @Test
    public void addServiceToCustomerThatAlreadyExists(){
        serviceRepository.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.PSA.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        assertThrows(ServiceAlreadyExistsException.class,
                ()-> testObject.addServiceToCustomerId(CUSTOMER_1, ServiceName.PSA),
                "Service PSA already exists for customer with id customer1");
    }

    @Test
    public void deleteDeviceFromCustomer(){
        testObject.deleteDeviceFromCustomer(CUSTOMER_1, ServiceName.PSA);
        Set<String> expectedResult = Sets.newHashSet(Collections.singleton(ServiceName.ANTIVIRUS_MAC.getName()));
        assertEquals(expectedResult, testObject.getServicesByCustomerId(CUSTOMER_1));
    }

    @Test
    public void getServiceCostsByCustomerId(){
        serviceCostRepository.save(new ServiceCost(ServiceName.PSA.getName(), 2));
        serviceCostRepository.save(new ServiceCost(ServiceName.ANTIVIRUS_MAC.getName(), 7));

        CustomerServiceCost customerServiceCost1 = new CustomerServiceCost(CUSTOMER_1, ServiceName.PSA.getName(), 2);
        CustomerServiceCost customerServiceCost2 = new CustomerServiceCost(CUSTOMER_1, ServiceName.ANTIVIRUS_MAC.getName(), 7);
        List<CustomerServiceCost> actualServiceCostsByCustomer = testObject.getServiceCostsByCustomerId(CUSTOMER_1);
        assertTrue(actualServiceCostsByCustomer.containsAll(Arrays.asList(customerServiceCost1, customerServiceCost2)));
    }

    @AfterEach
    public void cleanUp(){
        serviceRepository.deleteAll();
        serviceCostRepository.deleteAll();
    }
}
