package com.ninjarmm.rmmservicesserverapp.model.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ninjarmm.rmmservicesserverapp.model.devices.DeviceType;

import java.util.stream.Stream;

public enum ServiceName {
    ANTIVIRUS_WINDOWS("Antivirus Windows"),
    ANTIVIRUS_MAC("Antivirus Mac"),
    CLOUDBERRY("Cloudberry"),
    PSA("PSA"),
    TEAM_VIEWER("Team Viewer");

    private String name;

    ServiceName(String name) { this.name = name; }

    public String getName() { return name; }

    @JsonCreator
    public static ServiceName find(String value) {
        return Stream.of(values())
                .filter(serviceName -> serviceName.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
