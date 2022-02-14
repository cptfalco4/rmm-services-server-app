package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoDevicesFoundForCustomerException;
import com.ninjarmm.rmmservicesserverapp.model.costs.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceDto;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DealCalculationsService {
    public static final int DEVICE_FLAT_PRICE = 4;

    private final DeviceService deviceService;
    private final CustomerServicesService customerServicesService;

    public DealCalculationsService(DeviceService deviceService,
                                   CustomerServicesService customerServicesService) {
        this.deviceService = deviceService;
        this.customerServicesService = customerServicesService;
    }

    public int calculateBillForCustomer(String customerId) {
        Set<DeviceDto> devicesByCustomerId = deviceService.getDevicesByCustomerId(customerId);
        List<CustomerServiceCost> serviceCosts = customerServicesService.getServiceCostsByCustomerId(customerId);
        Integer servicesCostAggregated = serviceCosts.stream()
                .map(customerServiceCost -> multiplyServicesCost(customerServiceCost, devicesByCustomerId))
                .reduce(0, Math::addExact);

        int generalDevicesCost = Math.multiplyExact(devicesByCustomerId.size(), DEVICE_FLAT_PRICE);

        return Math.addExact(servicesCostAggregated, generalDevicesCost);
    }

    private int multiplyServicesCost(CustomerServiceCost customerServiceCost, Set<DeviceDto> devicesByCustomerId) {
        Set<DeviceDto> macDevices = devicesByCustomerId.stream()
                .filter(deviceDto -> deviceDto.getType().equals(DeviceType.MAC.getName())).collect(Collectors.toSet());
        Set<DeviceDto> windowsDevices = devicesByCustomerId.stream()
                .filter(deviceDto -> !deviceDto.getType().equals(DeviceType.MAC.getName())).collect(Collectors.toSet());

        int calculation = 0;
        switch(customerServiceCost.getServiceName()) {
            case "AntivirusMac": calculation = Math.multiplyExact(customerServiceCost.getPrice(), macDevices.size()); break;
            case "AntivirusWindows" :  calculation = Math.multiplyExact(customerServiceCost.getPrice(), windowsDevices.size()); break;
            default: calculation = Math.multiplyExact(customerServiceCost.getPrice(), devicesByCustomerId.size()); break;
        }

        return calculation;
    }


}
