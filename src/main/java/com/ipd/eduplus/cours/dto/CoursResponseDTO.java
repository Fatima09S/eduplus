package com.ipd.eduplus.cours.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoursResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private Integer capacite;
    private String code;
    private Integer nombreInscrits;
}