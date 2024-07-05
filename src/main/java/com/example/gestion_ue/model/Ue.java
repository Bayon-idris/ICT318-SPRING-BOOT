package com.example.gestion_ue.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
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



    public Long getUserId() {
        return createdBy != null ? createdBy.getId() : null;
    }

    @OneToMany(mappedBy = "ue", cascade = CascadeType.ALL)
    private List<Course> courses;
}
