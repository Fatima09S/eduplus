package com.ipd.eduplus.etudiant;

import com.ipd.eduplus.etudiant.dto.EtudiantRequestDTO;
import com.ipd.eduplus.etudiant.dto.EtudiantResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public Etudiant toEntity(EtudiantRequestDTO dto) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setEmail(dto.getEmail());
        etudiant.setTelephone(dto.getTelephone());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setNumeroEtudiant(dto.getNumeroEtudiant());
        return etudiant;
    }

    public EtudiantResponseDTO toDTO(Etudiant etudiant) {
        EtudiantResponseDTO dto = new EtudiantResponseDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setEmail(etudiant.getEmail());
        dto.setTelephone(etudiant.getTelephone());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setNumeroEtudiant(etudiant.getNumeroEtudiant());
        return dto;
    }
}