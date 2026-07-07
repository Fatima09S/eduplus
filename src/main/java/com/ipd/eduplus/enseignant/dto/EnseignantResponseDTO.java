// src/main/java/com/ipd/eduplus/enseignant/dto/EnseignantResponseDTO.java
package com.ipd.eduplus.enseignant.dto;

import java.time.LocalDate;

public class EnseignantResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String specialite;
    private LocalDate dateEmbauche;

    public EnseignantResponseDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }
}