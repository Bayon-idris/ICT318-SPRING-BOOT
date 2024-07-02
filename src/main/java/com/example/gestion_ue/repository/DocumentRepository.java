package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
