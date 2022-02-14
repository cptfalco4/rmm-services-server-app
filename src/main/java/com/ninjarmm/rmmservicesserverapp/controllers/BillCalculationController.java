package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.services.DealCalculationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/{customerId}/calculate")
public class BillCalculationController {
    private DealCalculationsService dealCalculationsService;

    public BillCalculationController(DealCalculationsService dealCalculationsService) {
        this.dealCalculationsService = dealCalculationsService;
    }

    @GetMapping
    private Integer calculateCustomersMonthlyBill(@PathVariable String customerId) {
        return dealCalculationsService.calculateBillForCustomer(customerId);
    }

}
