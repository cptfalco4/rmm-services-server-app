package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoRoleOnUserException;
import com.ninjarmm.rmmservicesserverapp.exceptions.NoUserFoundException;
import com.ninjarmm.rmmservicesserverapp.model.Role;
import com.ninjarmm.rmmservicesserverapp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        com.ninjarmm.rmmservicesserverapp.model.User user = userRepository.findById(username)
                .orElseThrow(() -> new NoUserFoundException(username));
        Role role = user.getRole();
        confirmRole(username, role);

        Collection<? extends GrantedAuthority> roles = Collections.singleton(new SimpleGrantedAuthority(role.getName()));

        return new User(user.getUsername(), user.getPassword(), roles);
    }

    private void confirmRole(String username, Role role) {
        if(role == null) {
            throw new NoRoleOnUserException(username);
        }
    }
}
