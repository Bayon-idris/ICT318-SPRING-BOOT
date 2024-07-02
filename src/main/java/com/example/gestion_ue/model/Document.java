package com.example.gestion_ue.model;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
