package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.model.CustomerServiceCost;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerServicesCostsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DealCalculationsService {
    private final CustomerServicesCostsView customerServicesCostsView;
    private final DeviceService deviceService;

    public DealCalculationsService(CustomerServicesCostsView customerServicesCostsView,
                                   DeviceService deviceService) {
        this.customerServicesCostsView = customerServicesCostsView;
        this.deviceService = deviceService;
    }

    public Mono<Integer> calculateBill(String customerId) {
        return deviceService.getDevicesByCustomerId(customerId).collectList()
                .map(customerDevices -> Math.multiplyExact(customerDevices.size(), 4))
                .flatMap(deviceCost -> customerServicesCostsView.findAllByCustomerId(customerId)
                        .map(CustomerServiceCost::getPrice)
                        .collectList()
                        .map(costsList -> costsList.stream().mapToInt(i->i).sum())
                        .map(servicesCostTotal -> Math.addExact(servicesCostTotal, deviceCost)));
    }
}
