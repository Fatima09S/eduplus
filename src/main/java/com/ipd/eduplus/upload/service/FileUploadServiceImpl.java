package com.ipd.eduplus.upload.service;

import com.ipd.eduplus.etudiant.Etudiant;
import com.ipd.eduplus.etudiant.EtudiantService;
import com.ipd.eduplus.upload.dto.UploadResponse;
import com.ipd.eduplus.upload.util.FileStorageUtil;
import com.ipd.eduplus.upload.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final EtudiantService etudiantService;
    private final FileValidator fileValidator;
    private final FileStorageUtil fileStorageUtil;

    @Override
    public UploadResponse uploadPhoto(Long etudiantId, MultipartFile file) {

        // Vérifier que l'étudiant existe
        Etudiant etudiant = etudiantService.findById(etudiantId);

        // Valider le fichier
        fileValidator.validatePhoto(file);

        // Sauvegarder le fichier
        String fileName = fileStorageUtil.savePhoto(file);

        etudiant.setPhotoProfil(fileName);
        // etudiantService.update(...);

        return UploadResponse.builder()
                .fileName(fileName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .message("Photo uploadée avec succès.")
                .build();
    }

    @Override
    public UploadResponse uploadDocument(Long etudiantId, MultipartFile file) {

        Etudiant etudiant = etudiantService.findById(etudiantId);

        fileValidator.validateDocument(file);

        String fileName = fileStorageUtil.saveDocument(file);


        etudiant.setDocumentPdf(fileName);

        return UploadResponse.builder()
                .fileName(fileName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .message("Document uploadé avec succès.")
                .build();
    }
}