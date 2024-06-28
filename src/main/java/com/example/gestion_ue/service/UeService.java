package com.example.gestion_ue.service;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.model.User;

public interface UeService {
    void saveUe(UeDto ueDto);

    Boolean checkIfCodeExists(String code);

    User getUserByUsername(String username);


}
