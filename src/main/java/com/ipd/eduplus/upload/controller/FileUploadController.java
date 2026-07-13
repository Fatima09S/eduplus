package com.ipd.eduplus.upload.controller;

import com.ipd.eduplus.upload.dto.UploadResponse;
import com.ipd.eduplus.upload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/etudiants/{id}/photo")
    public ResponseEntity<UploadResponse> uploadPhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        UploadResponse response = fileUploadService.uploadPhoto(id, file);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/etudiants/{id}/document")
    public ResponseEntity<UploadResponse> uploadDocument(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        UploadResponse response = fileUploadService.uploadDocument(id, file);

        return ResponseEntity.ok(response);
    }
}