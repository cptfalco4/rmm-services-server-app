package com.ninjarmm.rmmservicesserverapp.controllers;

import com.ninjarmm.rmmservicesserverapp.services.DealCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/{customerId}")
public class DealCalculationController {
    @Autowired
    private DealCalculationsService dealCalculationsService;

    @GetMapping
    private Mono<Integer> calculateCustomersMonthlyBill(@PathVariable String customerId) {
        return dealCalculationsService.calculateBill(customerId);
    }

}
