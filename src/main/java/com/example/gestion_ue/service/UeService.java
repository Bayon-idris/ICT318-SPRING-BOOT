package com.example.gestion_ue.service;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.model.User;

import java.util.List;

public interface UeService {
    void saveUe(UeDto ueDto);

    Boolean checkIfCodeExists(String code);

    User getUserByUsername(String username);

    List<Ue> getAllUes();

    boolean deleteUe(Long id);

    boolean updateUe(Long id, String code, String title, String description);

}
