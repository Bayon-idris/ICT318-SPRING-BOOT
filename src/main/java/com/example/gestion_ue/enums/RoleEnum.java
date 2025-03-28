package com.example.gestion_ue.enums;

public enum RoleEnum {
    ROLE_TEACHER("Enseignant"),
    ROLE_STUDENT("Élève");

    private final String displayName;

    RoleEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
