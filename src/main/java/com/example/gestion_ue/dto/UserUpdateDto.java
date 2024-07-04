package com.example.gestion_ue.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserUpdateDto {

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;

    @Email(message = "Adresse email non valide")
    private String email;

}
