package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
