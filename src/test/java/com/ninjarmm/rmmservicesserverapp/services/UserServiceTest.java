package com.ninjarmm.rmmservicesserverapp.services;

import com.ninjarmm.rmmservicesserverapp.exceptions.NoRoleOnUserException;
import com.ninjarmm.rmmservicesserverapp.exceptions.NoUserFoundException;
import com.ninjarmm.rmmservicesserverapp.model.Role;
import com.ninjarmm.rmmservicesserverapp.model.User;
import com.ninjarmm.rmmservicesserverapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService testObject;

    @Test
    void loadUserByUsername(){
        Role userRole = new Role();
        userRole.setName("USER");

        String username = "user1";
        User expectedUser = new User(username, "password", userRole);
        given(userRepository.findById(username))
                .willReturn(Optional.of(expectedUser));

        assertEquals(expectedUser.getUsername(), testObject.loadUserByUsername(username).getUsername());
        assertEquals(expectedUser.getPassword(), testObject.loadUserByUsername(username).getPassword());
    }

    @Test
    void usernameNotFound(){
        String username = "user1";
        given(userRepository.findById(username))
                .willReturn(Optional.empty());

        assertThrows(NoUserFoundException.class,
                () -> testObject.loadUserByUsername(username),
                "user1 is not a known registered user");
    }

    @Test
    void noRoleOnUser(){
        String username = "user1";
        User expectedUser = new User(username, "password", null);
        given(userRepository.findById(username))
                .willReturn(Optional.of(expectedUser));

        assertThrows(NoRoleOnUserException.class,
                () -> testObject.loadUserByUsername(username),
                "User with username user1 has no roles");
    }
}
