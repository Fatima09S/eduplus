package com.ipd.eduplus.inscription.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InscriptionResponseDTO {

    private Long id;
    private Long etudiantId;
    private Long coursId;
    private String titreCours;
    private String codeCours;
    private LocalDateTime dateInscription;
}