package com.ipd.eduplus.upload.photos;

import org.springframework.web.multipart.MultipartFile;

import com.ipd.eduplus.upload.dto.UploadResponse;
import com.ipd.eduplus.upload.service.FileUploadService;

public class UpdatePhoto {

    private final FileUploadService fileUploadService;

    public UpdatePhoto(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    public UploadResponse uploadPhoto(Long etudiantId, MultipartFile file) {
        return fileUploadService.uploadPhoto(etudiantId, file);
    }
}
