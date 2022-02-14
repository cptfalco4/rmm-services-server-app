package com.ninjarmm.rmmservicesserverapp.models.devices;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ninjarmm.rmmservicesserverapp.exceptions.DeviceTypeDoesNotExistException;

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
                .orElseThrow(() -> new DeviceTypeDoesNotExistException(value));
    }
}
