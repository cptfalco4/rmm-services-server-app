package com.ninjarmm.rmmservicesserverapp.model.devices;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceId implements Serializable {
    String customerId;
    String deviceId;
}
