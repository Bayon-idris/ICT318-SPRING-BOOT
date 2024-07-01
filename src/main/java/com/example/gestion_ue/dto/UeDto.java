package com.example.gestion_ue.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UeDto {
    private Long id;

    @NotBlank(message = "Le code de l'UE ne peut pas être vide")
    private String code;

    @NotBlank(message = "Le libellé de l'UE ne peut pas être vide")
    private String title;

    private String description;

}
