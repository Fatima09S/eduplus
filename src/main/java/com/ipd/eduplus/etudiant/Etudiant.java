// src/main/java/com/ipd/eduplus/etudiant/Etudiant.java
package com.ipd.eduplus.etudiant;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    private LocalDate dateNaissance;

    private String photoProfil;

    private String documentPdf;

    @Column(name = "numero_etudiant", unique = true)
    private String numeroEtudiant;
}