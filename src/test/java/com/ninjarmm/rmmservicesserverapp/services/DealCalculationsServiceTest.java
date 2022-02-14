package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.models.costs.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;
import com.ninjarmm.rmmservicesserverapp.models.services.ServiceName;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DealCalculationsServiceTest {
    @Mock
    private DeviceService deviceService;
    @Mock
    private CustomerServicesService customerServicesService;

    private DealCalculationsService testObject;

    @BeforeEach
    public void setUp(){
        testObject = new DealCalculationsService(deviceService, customerServicesService);
    }

    @Test
    public void calculateBillForCustomer(){
        DeviceDto windowsDevice1 = DeviceDto.builder()
                .deviceId("device1")
                .systemName("system1")
                .type(DeviceType.WINDOWS_WORKSTATION.getName())
                .build();
        DeviceDto windowsDevice2 = DeviceDto.builder()
                .deviceId("device2")
                .systemName("system1")
                .type(DeviceType.WINDOWS_WORKSTATION.getName())
                .build();

        DeviceDto macDevice1 = DeviceDto.builder()
                .deviceId("device3")
                .systemName("system1")
                .type(DeviceType.MAC.getName())
                .build();
        DeviceDto macDevice2 = DeviceDto.builder()
                .deviceId("device4")
                .systemName("system1")
                .type(DeviceType.MAC.getName())
                .build();
        DeviceDto macDevice3 = DeviceDto.builder()
                .deviceId("device5")
                .systemName("system1")
                .type(DeviceType.MAC.getName())
                .build();

        String customer1 = "customer1";
        given(deviceService.getDevicesByCustomerId(customer1))
               .willReturn(Sets.newHashSet(Arrays.asList(windowsDevice1, windowsDevice2, macDevice1, macDevice2, macDevice3)));

        CustomerServiceCost customerServiceCost1 = new CustomerServiceCost(customer1, ServiceName.ANTIVIRUS_WINDOWS.getName(),5);
        CustomerServiceCost customerServiceCost2 = new CustomerServiceCost(customer1, ServiceName.ANTIVIRUS_MAC.getName(),7);
        CustomerServiceCost customerServiceCost3 = new CustomerServiceCost(customer1, ServiceName.CLOUDBERRY.getName(),3);
        CustomerServiceCost customerServiceCost4 = new CustomerServiceCost(customer1, ServiceName.TEAM_VIEWER.getName(),1);

       given(customerServicesService.getServiceCostsByCustomerId(customer1))
               .willReturn(Arrays.asList(customerServiceCost1, customerServiceCost2, customerServiceCost3, customerServiceCost4));

       assertEquals(71, testObject.calculateBillForCustomer(customer1));
    }

    @Test
    void calculateBillForCustomerThatDNE(){
        String customer2 = "customer2";

        given(deviceService.getDevicesByCustomerId(customer2))
                .willThrow(NoDevicesFoundForCustomerException.class);

        assertThrows(NoDevicesFoundForCustomerException.class,
                () -> testObject.calculateBillForCustomer(customer2),
                "CustomerId customer2 has no registered devices");
    }
}
