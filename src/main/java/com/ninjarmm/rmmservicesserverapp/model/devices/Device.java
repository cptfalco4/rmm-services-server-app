package com.ninjarmm.rmmservicesserverapp.model.devices;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Device {
    String deviceId;
    String systemName;
    DeviceType type;
}
