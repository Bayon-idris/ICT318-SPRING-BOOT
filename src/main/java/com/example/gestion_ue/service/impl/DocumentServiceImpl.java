package com.example.gestion_ue.service.impl;

import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.model.Document;
import com.example.gestion_ue.repository.DocumentRepository;
import com.example.gestion_ue.service.DocumentService;
import com.example.gestion_ue.utils.Constant;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;


    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void saveDocuments(MultipartFile[] files, Course course) throws IOException {
        for (MultipartFile file : files) {
            String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
            String fileName = Instant.now().toEpochMilli() + "_" + StringUtils.cleanPath(originalFileName);
            String filePath = Constant.FILE_PATH + File.separator + fileName;

            File newFile = new File(filePath);
            newFile.getParentFile().mkdirs();

            try (FileOutputStream fout = new FileOutputStream(newFile)) {
                fout.write(file.getBytes());
                Document document = new Document();
                document.setName(fileName);
                document.setUrl(filePath.toString());
                document.setCourse(course);
//                documentRepository.save(document);
            } catch (IOException e) {
                throw new IOException("Failed to save course file " + fileName, e);
            }
        }
    }
}
