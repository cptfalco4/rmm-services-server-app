package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoServicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.exceptions.ServiceNameDoesNotExistException;
import com.ninjarmm.rmmservicesserverapp.model.services.Service;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import com.ninjarmm.rmmservicesserverapp.util.ServiceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerIT extends BaseIT {
    private final static String PATH = "/customers/{customerId}/services";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ServiceRepository serviceRepository;

    private UriComponents uriComponents;

    @BeforeEach
    private void setUp() {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + port).path(PATH).buildAndExpand(CUSTOMER_1);
    }

    @Test
    void getServicesForACustomer() {
        Service service = ServiceUtil.buildService(CUSTOMER_1, ServiceName.PSA.getName());
        serviceRepository.save(service);
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), Set.class)
                .getStatusCode().is2xxSuccessful());
    }

    @Test
    void getServicesForACustomer_servicesNotFound() {
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), NoServicesFoundForCustomerException.class)
                .getStatusCode().is4xxClientError());
    }

    @Test
    void addDeviceToCustomer_BadServiceName(){
        String deviceJson = "{\"BadServiceName\"}";
        assertTrue(this.restTemplate.postForEntity(uriComponents.toUriString(), deviceJson, ServiceNameDoesNotExistException.class)
                .getStatusCode().is4xxClientError());
    }

    @Test
    void deleteServiceFromCustomer(){
        Service service = ServiceUtil.buildService(CUSTOMER_1, ServiceName.PSA.getName());
        serviceRepository.save(service);
        UriComponents deleteUriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + port).path(PATH + "/{serviceName}").buildAndExpand(CUSTOMER_1, "PSA");
        this.restTemplate.delete(deleteUriComponents.toUriString());
        assertEquals(Collections.EMPTY_SET, serviceRepository.findAllById_CustomerId(CUSTOMER_1));
    }

    @AfterEach
    private void cleanUp(){
        serviceRepository.deleteAll();
    }

}
