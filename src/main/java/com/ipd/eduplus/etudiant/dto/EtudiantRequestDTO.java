package com.ipd.eduplus.etudiant.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EtudiantRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email n'est pas valide")
    private String email;

    private String telephone;
    private LocalDate dateNaissance;
    private String numeroEtudiant;

    public EtudiantRequestDTO() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getNumeroEtudiant() { return numeroEtudiant; }
    public void setNumeroEtudiant(String numeroEtudiant) { this.numeroEtudiant = numeroEtudiant; }
}