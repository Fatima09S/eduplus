package com.ipd.eduplus.inscription;

import com.ipd.eduplus.inscription.dto.InscriptionResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {

    public InscriptionResponseDTO toDTO(Inscription inscription) {
        return InscriptionResponseDTO.builder()
                .id(inscription.getId())
                .etudiantId(inscription.getEtudiantId())
                .coursId(inscription.getCours().getId())
                .titreCours(inscription.getCours().getTitre())
                .codeCours(inscription.getCours().getCode())
                .dateInscription(inscription.getDateInscription())
                .build();
    }
}