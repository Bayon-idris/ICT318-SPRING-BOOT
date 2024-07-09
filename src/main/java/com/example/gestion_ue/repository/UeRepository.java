package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Ue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UeRepository extends JpaRepository<Ue, Long> {
    boolean existsByCode(String code);

    List<Ue> findByCreatedById(Long userId);

}
