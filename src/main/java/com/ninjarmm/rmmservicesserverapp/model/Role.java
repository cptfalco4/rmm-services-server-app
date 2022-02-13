package com.ninjarmm.rmmservicesserverapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {
    @Id
    String name;
    @OneToMany(targetEntity = User.class, mappedBy = "role",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<User> users;

}
