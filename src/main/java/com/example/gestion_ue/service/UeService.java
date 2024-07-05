package com.example.gestion_ue.service;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UeService {
    void saveUe(UeDto ueDto);

    Boolean checkIfCodeExists(String code);

    User getUserByUsername(String username);

    Page<Ue> getAllUes(Pageable pageable);

    boolean deleteUe(Long id);

    boolean updateUe(Long id, String code, String title, String description);

    Ue findById(Long id);

}
