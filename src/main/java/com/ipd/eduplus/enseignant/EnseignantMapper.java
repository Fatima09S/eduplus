// src/main/java/com/ipd/eduplus/enseignant/EnseignantMapper.java
package com.ipd.eduplus.enseignant;

import com.ipd.eduplus.enseignant.dto.EnseignantRequestDTO;
import com.ipd.eduplus.enseignant.dto.EnseignantResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class EnseignantMapper {

    public Enseignant toEntity(EnseignantRequestDTO dto) {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(dto.getNom());
        enseignant.setPrenom(dto.getPrenom());
        enseignant.setEmail(dto.getEmail());
        enseignant.setTelephone(dto.getTelephone());
        enseignant.setSpecialite(dto.getSpecialite());
        enseignant.setDateEmbauche(dto.getDateEmbauche());
        return enseignant;
    }

    public EnseignantResponseDTO toDTO(Enseignant enseignant) {
        EnseignantResponseDTO dto = new EnseignantResponseDTO();
        dto.setId(enseignant.getId());
        dto.setNom(enseignant.getNom());
        dto.setPrenom(enseignant.getPrenom());
        dto.setEmail(enseignant.getEmail());
        dto.setTelephone(enseignant.getTelephone());
        dto.setSpecialite(enseignant.getSpecialite());
        dto.setDateEmbauche(enseignant.getDateEmbauche());
        return dto;
    }
}