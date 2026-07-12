package com.ipd.eduplus.upload.validator;

import com.ipd.eduplus.exception.InvalidFileTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator {

    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2 Mo

    public void validatePhoto(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new InvalidFileTypeException("Le fichier est vide.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidFileTypeException("La taille maximale autorisée est de 2 Mo.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("image/jpeg")
                        || contentType.equals("image/png"))) {

            throw new InvalidFileTypeException(
                    "Seuls les fichiers JPG et PNG sont autorisés.");
        }
    }

    public void validateDocument(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new InvalidFileTypeException("Le fichier est vide.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidFileTypeException("La taille maximale autorisée est de 2 Mo.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !contentType.equals("application/pdf")) {

            throw new InvalidFileTypeException(
                    "Seuls les fichiers PDF sont autorisés.");
        }
    }
}