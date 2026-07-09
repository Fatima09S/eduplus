package com.ipd.eduplus.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    private String fileName;

    private String fileDownloadUri;

    private String contentType;

    private long size;

    private String message;
}