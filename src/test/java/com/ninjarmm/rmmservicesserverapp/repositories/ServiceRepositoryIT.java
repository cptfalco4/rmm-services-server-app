package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceId;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceRepositoryIT extends CustomerDependentRepositoryITBase {
    @Autowired
    private ServiceRepository testObject;

    @BeforeEach
    void setup() {
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.PSA.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        System.out.println("------------------------");
        testObject.findAll().forEach(System.out::println);
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_1, ServiceName.ANTIVIRUS_MAC.getName()))
                .customer(CUSTOMER_1_ENTITY)
                .build());
        System.out.println("------------------------");
        testObject.findAll().forEach(System.out::println);
        testObject.save(Service.builder()
                .id(new ServiceId(CUSTOMER_2, ServiceName.PSA.getName()))
                .customer(CUSTOMER_2_ENTITY)
                .build());
        System.out.println("------------------------");
        testObject.findAll().forEach(System.out::println);
    }

    @Test
    void findAllById_CustomerId() {
        System.out.println("------------------------");
        testObject.findAll().forEach(System.out::println);
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
