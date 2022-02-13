package com.ninjarmm.rmmservicesserverapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    public String demo(){
        return "Hello from Nikkis Controller";
    }
}
