package com.example.gestion_ue.model;


import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ues")
public class Ue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Code is mandatory")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Title is mandatory")
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code ;
    }

    public void setCode(String code) {
        this.code = code ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUserId() {
        return createdBy != null ? createdBy.getId() : null;
    }


}
