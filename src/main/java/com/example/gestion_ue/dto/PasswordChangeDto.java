package com.example.gestion_ue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto {
    private String currentPassword;
    @NotEmpty(message = "Nouveau mot de passe requis")
    @Size(min = 8, message = "Le mot de passe doit comporter au moins 8 caractères")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).*$",
            message = "Le mot de passe doit contenir au moins un chiffre, une lettre et un caractère spécial")
    private String newPassword;
    @NotEmpty(message = "Confirmation du mot de passe requise")
    private String confirmPassword;
}
