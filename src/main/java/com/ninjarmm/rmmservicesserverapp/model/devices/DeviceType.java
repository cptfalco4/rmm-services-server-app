package com.ninjarmm.rmmservicesserverapp.model.devices;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum DeviceType {
    WINDOWS_WORKSTATION("Windows Workstation"),
    WINDOWS_SERVER("Windows Server"),
    MAC("Mac");

    private String name;

    DeviceType(String name) { this.name = name; }

    public String getName() { return name; }

    @JsonCreator
    public static DeviceType find(String value) {
        return Stream.of(values())
                .filter(deviceType -> deviceType.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
