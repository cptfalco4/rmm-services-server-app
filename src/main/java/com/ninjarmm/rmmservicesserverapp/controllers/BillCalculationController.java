package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.services.DealCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/{customerId}/calculate")
public class BillCalculationController {
    @Autowired
    private DealCalculationsService dealCalculationsService;

    @GetMapping
    private Integer calculateCustomersMonthlyBill(@PathVariable String customerId) {
        return dealCalculationsService.calculateBillForCustomer(customerId);
    }

}
