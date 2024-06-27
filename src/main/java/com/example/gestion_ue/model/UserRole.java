package com.example.gestion_ue.model;

import jakarta.persistence.*;


@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private String roleId;

    public UserRole() {
    }

    public UserRole(Long userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
