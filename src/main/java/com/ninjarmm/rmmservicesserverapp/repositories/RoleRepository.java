package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.Role;
import com.ninjarmm.rmmservicesserverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
