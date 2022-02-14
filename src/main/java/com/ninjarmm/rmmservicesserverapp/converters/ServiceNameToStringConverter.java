package com.ninjarmm.rmmservicesserverapp.converters;

import com.ninjarmm.rmmservicesserverapp.models.services.ServiceName;
import org.springframework.core.convert.converter.Converter;

public class ServiceNameToStringConverter implements Converter<String, ServiceName> {
    @Override
    public ServiceName convert(String s) {
        return ServiceName.find(s);
    }
}
