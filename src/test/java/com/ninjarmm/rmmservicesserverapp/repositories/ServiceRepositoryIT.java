package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.models.services.Service;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceRepositoryIT extends BaseIT {
    @Autowired
    private ServiceRepository testObject;
    @Autowired
    private ServiceCostRepository serviceCostRepository;

    @BeforeEach
    void setup() {
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.PSA.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.ANTIVIRUS_MAC.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_2, ServiceName.PSA.getName()))
                .customer(CUSTOMER_2_ENTITY)
                .build());
    }

    @Test
    void findAllById_CustomerId() {
        Set<Service> actualServices = testObject.findAllById_CustomerId(CUSTOMER_1);
        assertEquals(2, actualServices.size());
    }

    @Test
    void customerIdNotFound() {
        Set<Service> actualServices = testObject.findAllById_CustomerId("customer3");
        assertEquals(Collections.emptySet(), actualServices);
    }

    @AfterEach
    public void cleanup() {
        testObject.deleteAll();
    }
}
