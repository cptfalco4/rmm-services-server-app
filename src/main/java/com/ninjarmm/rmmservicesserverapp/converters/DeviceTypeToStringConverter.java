package com.ninjarmm.rmmservicesserverapp.converters;

import com.ninjarmm.rmmservicesserverapp.models.devices.DeviceType;
import org.springframework.core.convert.converter.Converter;

public class DeviceTypeToStringConverter implements Converter<String, DeviceType> {
    @Override
    public DeviceType convert(String s) {
        return DeviceType.find(s);
    }
}
