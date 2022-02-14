package com.ninjarmm.rmmservicesserverapp.models.devices;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeviceDto {
    String deviceId;
    String systemName;
    String type;
}
