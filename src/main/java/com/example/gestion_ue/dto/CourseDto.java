package com.example.gestion_ue.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CourseDto {
    private Long id;

    @NotBlank(message = "Le titre du cours ne peut pas être vide")
    private String title;

    @NotBlank(message = "La description du cours ne peut pas être vide")
    private String description;

    private Long ue_id;
}