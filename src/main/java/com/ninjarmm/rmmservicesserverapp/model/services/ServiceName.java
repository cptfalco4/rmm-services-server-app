package com.ninjarmm.rmmservicesserverapp.model.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ninjarmm.rmmservicesserverapp.exceptions.ServiceNameDoesNotExistException;

import java.util.stream.Stream;

public enum ServiceName {
    ANTIVIRUS_WINDOWS("AntivirusWindows"),
    ANTIVIRUS_MAC("AntivirusMac"),
    CLOUDBERRY("Cloudberry"),
    PSA("PSA"),
    TEAM_VIEWER("TeamViewer");

    private String name;

    ServiceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static ServiceName find(String value) {
        return Stream.of(values())
                .filter(serviceName -> serviceName.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ServiceNameDoesNotExistException(value));
    }
}
