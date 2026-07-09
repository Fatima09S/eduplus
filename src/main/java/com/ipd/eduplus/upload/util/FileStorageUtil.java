package com.ipd.eduplus.upload.util;

import java.util.UUID;

public class FileStorageUtil {

    private FileStorageUtil() {
    }

    public static String generateFileName(String originalFileName) {

        String extension = "";

        int index = originalFileName.lastIndexOf(".");

        if (index > 0) {
            extension = originalFileName.substring(index);
        }

        return UUID.randomUUID() + extension;
    }

}