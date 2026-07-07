// src/main/java/com/ipd/eduplus/etudiant/EtudiantRepository.java
package com.ipd.eduplus.etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Recherche par email (utile pour vérifier les doublons à l'inscription)
    Optional<Etudiant> findByEmail(String email);

    // Recherche par numéro étudiant
    Optional<Etudiant> findByNumeroEtudiant(String numeroEtudiant);

    // Vérifie si un email existe déjà
    boolean existsByEmail(String email);
}