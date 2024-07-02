package com.example.gestion_ue.service;

import com.example.gestion_ue.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    void saveDocuments(MultipartFile[] files, Course course) throws IOException;
}
