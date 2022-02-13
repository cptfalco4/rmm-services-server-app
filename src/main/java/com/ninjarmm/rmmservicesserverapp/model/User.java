package com.ninjarmm.rmmservicesserverapp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    @NotNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
