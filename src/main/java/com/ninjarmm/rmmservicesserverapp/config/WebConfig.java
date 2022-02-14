package com.ninjarmm.rmmservicesserverapp.config;

import com.ninjarmm.rmmservicesserverapp.converters.DeviceTypeToStringConverter;
import com.ninjarmm.rmmservicesserverapp.converters.ServiceNameToStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DeviceTypeToStringConverter());
        registry.addConverter(new ServiceNameToStringConverter());
    }
}
