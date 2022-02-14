package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.models.devices.Device;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.models.services.Service;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceName;
import com.ninjarmm.rmmservicesserverapp.repositories.DeviceRepository;
import com.ninjarmm.rmmservicesserverapp.repositories.ServiceRepository;
import com.ninjarmm.rmmservicesserverapp.util.BaseIT;
import com.ninjarmm.rmmservicesserverapp.util.DeviceUtil;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillCalculationControllerIT extends BaseIT {
    private final static String PATH = "/customers/{customerId}/calculate";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DeviceRepository deviceRepository;
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
        Device device = DeviceUtil.buildDevice(CUSTOMER_1, "device1", DeviceType.WINDOWS_WORKSTATION);
        Service service = ServiceUtil.buildService(CUSTOMER_1, ServiceName.ANTIVIRUS_WINDOWS.getName());
        deviceRepository.save(device);
        serviceRepository.save(service);
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), Integer.class)
                .getStatusCode().is2xxSuccessful());
    }

    @Test
    void noDevicesOnCustomer() {
        Service service = ServiceUtil.buildService(CUSTOMER_1, ServiceName.ANTIVIRUS_WINDOWS.getName());
        serviceRepository.save(service);
        assertTrue(this.restTemplate.getForEntity(uriComponents.toUriString(), NoDevicesFoundForCustomerException.class)
                .getStatusCode().is4xxClientError());
    }

    @AfterEach
    private void cleanUp(){
        deviceRepository.deleteAll();
        serviceRepository.deleteAll();
    }
}
