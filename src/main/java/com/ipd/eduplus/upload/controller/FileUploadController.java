package com.ipd.eduplus.upload.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ipd.eduplus.upload.dto.UploadResponse;
import com.ipd.eduplus.upload.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
@Tag(name = "Uploads", description = "Endpoints pour l'upload de photos et de documents étudiants")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "Uploader une photo de profil", description = "Enregistre une image JPG/PNG liée à un étudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo uploadée avec succès"),
            @ApiResponse(responseCode = "400", description = "Fichier invalide ou taille dépassée"),
            @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @PostMapping("/etudiants/{id}/photo")
    public ResponseEntity<UploadResponse> uploadPhoto(
            @Parameter(description = "Identifiant de l'étudiant") @PathVariable Long id,
            @Parameter(description = "Fichier image à uploader", required = true) @RequestParam("file") MultipartFile file) {

        UploadResponse response = fileUploadService.uploadPhoto(id, file);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Uploader un document PDF", description = "Enregistre un document PDF lié à un étudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document uploadé avec succès"),
            @ApiResponse(responseCode = "400", description = "Fichier invalide ou taille dépassée"),
            @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @PostMapping("/etudiants/{id}/document")
    public ResponseEntity<UploadResponse> uploadDocument(
            @Parameter(description = "Identifiant de l'étudiant") @PathVariable Long id,
            @Parameter(description = "Fichier PDF à uploader", required = true) @RequestParam("file") MultipartFile file) {

        UploadResponse response = fileUploadService.uploadDocument(id, file);
        return ResponseEntity.ok(response);
    }
}