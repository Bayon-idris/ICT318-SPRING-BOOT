package com.example.gestion_ue.service;

import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void saveDocuments(MultipartFile[] files, Course course) throws IOException;

    public List<Document> getAttachmentsByCourseId(Long courseId);
}
