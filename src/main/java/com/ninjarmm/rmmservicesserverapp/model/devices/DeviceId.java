package com.ninjarmm.rmmservicesserverapp.model.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceId implements Serializable {
    private static final long serialVersionUID = 8260197099418399822L;

    String customerId;
    String deviceId;
}
