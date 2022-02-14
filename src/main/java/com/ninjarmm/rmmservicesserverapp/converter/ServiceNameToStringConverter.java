package com.ninjarmm.rmmservicesserverapp.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ninjarmm.rmmservicesserverapp.model.services.ServiceName;
import org.springframework.core.convert.converter.Converter;

public class ServiceNameToStringConverter implements Converter<String, ServiceName> {
    @Override
    public ServiceName convert(String s) {
        return ServiceName.find(s);
    }
}
