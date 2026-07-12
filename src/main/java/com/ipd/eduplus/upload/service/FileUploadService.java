package com.ipd.eduplus.upload.service;

import com.ipd.eduplus.upload.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    UploadResponse uploadPhoto(Long etudiantId, MultipartFile file);

    UploadResponse uploadDocument(Long etudiantId, MultipartFile file);

}