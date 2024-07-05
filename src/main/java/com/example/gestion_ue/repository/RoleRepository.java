package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}