package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Ue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UeRepository extends JpaRepository<Ue, Long> {

    boolean existsByCode(String code);

}
