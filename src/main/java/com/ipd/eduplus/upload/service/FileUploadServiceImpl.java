package com.ipd.eduplus.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ipd.eduplus.etudiant.Etudiant;
import com.ipd.eduplus.etudiant.EtudiantService;
import com.ipd.eduplus.exception.ResourceNotFoundException;
import com.ipd.eduplus.upload.dto.UploadResponse;
import com.ipd.eduplus.upload.util.FileStorageUtil;
import com.ipd.eduplus.upload.validator.FileValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final EtudiantService etudiantService;
    private final FileValidator fileValidator;
    private final FileStorageUtil fileStorageUtil;

    @Override
    @Transactional
    public UploadResponse uploadPhoto(Long etudiantId, MultipartFile file) {
        try {
            Etudiant etudiant = etudiantService.findById(etudiantId);
            fileValidator.validatePhoto(file);

            String fileName = fileStorageUtil.savePhoto(file);
            etudiant.setPhotoProfil(fileName);
            etudiantService.update(etudiantId, etudiant);

            return UploadResponse.builder()
                    .fileName(fileName)
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .message("Photo uploadée avec succès.")
                    .build();
        } catch (RuntimeException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("Etudiant introuvable")) {
                throw new ResourceNotFoundException("Étudiant introuvable avec l'id : " + etudiantId);
            }
            throw ex;
        }
    }

    @Override
    @Transactional
    public UploadResponse uploadDocument(Long etudiantId, MultipartFile file) {
        try {
            Etudiant etudiant = etudiantService.findById(etudiantId);
            fileValidator.validateDocument(file);

            String fileName = fileStorageUtil.saveDocument(file);
            etudiant.setDocumentPdf(fileName);
            etudiantService.update(etudiantId, etudiant);

            return UploadResponse.builder()
                    .fileName(fileName)
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .message("Document uploadé avec succès.")
                    .build();
        } catch (RuntimeException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("Etudiant introuvable")) {
                throw new ResourceNotFoundException("Étudiant introuvable avec l'id : " + etudiantId);
            }
            throw ex;
        }
    }
}