package com.ipd.eduplus.cours;

import com.ipd.eduplus.cours.dto.CoursRequestDTO;
import com.ipd.eduplus.cours.dto.CoursResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CoursMapper {

    public Cours toEntity(CoursRequestDTO dto) {
        return Cours.builder()
                .titre(dto.getTitre())
                .description(dto.getDescription())
                .capacite(dto.getCapacite())
                .code(dto.getCode())
                .build();
    }

    public CoursResponseDTO toDTO(Cours cours, Integer nombreInscrits) {
        return CoursResponseDTO.builder()
                .id(cours.getId())
                .titre(cours.getTitre())
                .description(cours.getDescription())
                .capacite(cours.getCapacite())
                .code(cours.getCode())
                .nombreInscrits(nombreInscrits)
                .build();
    }

    public CoursResponseDTO toDTO(Cours cours) {
        return toDTO(cours, 0);
    }
}