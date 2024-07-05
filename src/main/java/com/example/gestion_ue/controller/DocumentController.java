package com.example.gestion_ue.controller;

import com.example.gestion_ue.model.Document;
import com.example.gestion_ue.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/attachments")
class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<Document> getAttachmentsForCourse(@PathVariable Long courseId) {
        return documentService.getAttachmentsByCourseId(courseId);
    }
}