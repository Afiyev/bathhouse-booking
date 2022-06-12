package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
