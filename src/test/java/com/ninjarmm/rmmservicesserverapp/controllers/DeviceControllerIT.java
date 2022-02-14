package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.exceptions.DeviceTypeDoesNotExistException;
import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.models.devices.Device;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceDetails;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import com.ninjarmm.rmmservicesserverapp.util.DeviceUtil;
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
public class DeviceControllerIT extends BaseIT {
    private final static String PATH = "/customers/{customerId}/devices";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DeviceRepository deviceRepository;

    private UriComponents uriComponents;

    @BeforeEach
    private void setUp() {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + port).path(PATH).buildAndExpand(CUSTOMER_1);
    }

    @Test
    void getDevicesForACustomer() {
        Device device1 = DeviceUtil.buildDevice(CUSTOMER_1, "device1", DeviceType.WINDOWS_WORKSTATION);
        deviceRepository.save(device1);
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), Set.class)
                .getStatusCode().is2xxSuccessful());
    }

    @Test
    void getDevicesForACustomer_NoDevices() {
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), NoDevicesFoundForCustomerException.class)
                .getStatusCode().is4xxClientError());
    }

    @Test
    void addDeviceToCustomer(){
        DeviceDetails deviceDetails = new DeviceDetails("systemName", DeviceType.MAC);
        assertTrue(this.restTemplate.postForEntity(uriComponents.toUriString(), deviceDetails, DeviceDetails.class)
                .getStatusCode().is2xxSuccessful());
    }

    @Test
    void addDeviceToCustomer_BadDeviceType(){
        String deviceJson = "{\"systemName\":\"system1\", \"type\":\"BadDeviceType\"}";
        assertTrue(this.restTemplate.postForEntity(uriComponents.toUriString(), deviceJson, DeviceTypeDoesNotExistException.class)
                .getStatusCode().is4xxClientError());
    }

    @Test
    void deleteDeviceFromCustomer(){
        Device device1 = DeviceUtil.buildDevice(CUSTOMER_1, "device1", DeviceType.MAC);
        deviceRepository.save(device1);
        UriComponents deleteUriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + port).path(PATH + "/{deviceId}").buildAndExpand(CUSTOMER_1, "device1");
        this.restTemplate.delete(deleteUriComponents.toUriString());
        assertEquals(Collections.EMPTY_SET, deviceRepository.findAllById_CustomerId(CUSTOMER_1));
    }

    @AfterEach
    private void cleanUp(){
        deviceRepository.deleteAll();
    }

}
