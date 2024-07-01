package com.example.gestion_ue.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class UeDto {
    private Long id;

    // Getters and setters
    @NotEmpty(message = "Code de l'UE ne peut pas être vide")
    private String code;

    @NotEmpty(message = "Libellé de l'UE ne peut pas être vide")
    private String title;

    private String description;

}
