package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ninjarmm.rmmservicesserverapp.model.User user = userRepository.findById(username)
                .get();
        Collection<? extends GrantedAuthority> roles = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));

        return new User(user.getUsername(), user.getPassword(), roles);
    }
}
