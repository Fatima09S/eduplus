package com.ipd.eduplus.upload.util;

import com.ipd.eduplus.exception.FileStorageException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileStorageUtil {
    private static final String PHOTO_DIRECTORY = "uploads/photos";

    private static final String DOCUMENT_DIRECTORY = "uploads/documents";

    private String generateFileName(String originalFilename) {

        String extension = "";

        int index = originalFilename.lastIndexOf(".");

        if (index != -1) {
            extension = originalFilename.substring(index);
        }

        return UUID.randomUUID() + extension;
    }

    private void createDirectory(String directory) {

        try {

            Path path = Paths.get(directory);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

        } catch (IOException e) {

            throw new FileStorageException(
                    "Impossible de créer le dossier de stockage."
            );
        }
    }

    public String savePhoto(MultipartFile file) {

        createDirectory(PHOTO_DIRECTORY);

        String fileName = generateFileName(file.getOriginalFilename());

        Path path = Paths.get(PHOTO_DIRECTORY, fileName);

        try {

            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {

            throw new FileStorageException(
                    "Erreur lors de l'enregistrement de la photo."
            );
        }
    }

    public String saveDocument(MultipartFile file) {

        createDirectory(DOCUMENT_DIRECTORY);

        String fileName = generateFileName(file.getOriginalFilename());

        Path path = Paths.get(DOCUMENT_DIRECTORY, fileName);

        try {

            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {

            throw new FileStorageException(
                    "Erreur lors de l'enregistrement du document."
            );
        }
    }

    public void deleteFile(String filePath) {

        try {

            Files.deleteIfExists(Paths.get(filePath));

        } catch (IOException e) {

            throw new FileStorageException(
                    "Impossible de supprimer le fichier."
            );
        }
    }

}