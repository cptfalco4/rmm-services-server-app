
package com.ninjarmm.rmmservicesserverapp.model.devices;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "customer_device")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CustomerDevice {
    @EmbeddedId
    CustomerDeviceId id;

    String systemName;
    DeviceType type;
}


