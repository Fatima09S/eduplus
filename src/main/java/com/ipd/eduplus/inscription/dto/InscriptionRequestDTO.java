package com.ipd.eduplus.inscription.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InscriptionRequestDTO {

    @NotNull(message = "L'identifiant de l'étudiant est obligatoire")
    private Long etudiantId;

    @NotNull(message = "L'identifiant du cours est obligatoire")
    private Long coursId;
}