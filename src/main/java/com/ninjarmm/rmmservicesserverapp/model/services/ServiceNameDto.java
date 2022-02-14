package com.ninjarmm.rmmservicesserverapp.model.services;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum ServiceNameDto {
    ANTIVIRUS("Antivirus"),
    CLOUDBERRY("Cloudberry"),
    PSA("PSA"),
    TEAM_VIEWER("TeamViewer");

    private String name;

    ServiceNameDto(String name) { this.name = name; }

    public String getName() { return name; }

    @JsonCreator
    public static ServiceNameDto find(String value) {
        return Stream.of(values())
                .filter(serviceName -> serviceName.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
