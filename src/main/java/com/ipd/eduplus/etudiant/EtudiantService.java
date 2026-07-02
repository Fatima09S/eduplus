package com.ipd.eduplus.etudiant;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    // ── Liste paginée (utilisée au J3 par le controller) ──
    public Page<Etudiant> findAll(Pageable pageable) {
        return etudiantRepository.findAll(pageable);
    }

    // ── Liste complète (utile pour tests/debug) ──
    public List<Etudiant> findAllList() {
        return etudiantRepository.findAll();
    }

    // ── Recherche par id ──
    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'id : " + id));
    }

    // ── Création (utilisée au J4 par le controller) ──
    public Etudiant create(Etudiant etudiant) {
        if (etudiantRepository.existsByEmail(etudiant.getEmail())) {
            throw new RuntimeException("Un étudiant avec cet email existe déjà : " + etudiant.getEmail());
        }
        return etudiantRepository.save(etudiant);
    }

    // ── Mise à jour (utilisée au J5 par le controller) ──
    public Etudiant update(Long id, Etudiant etudiantMisAJour) {
        Etudiant etudiant = findById(id);

        etudiant.setNom(etudiantMisAJour.getNom());
        etudiant.setPrenom(etudiantMisAJour.getPrenom());
        etudiant.setEmail(etudiantMisAJour.getEmail());
        etudiant.setTelephone(etudiantMisAJour.getTelephone());
        etudiant.setDateNaissance(etudiantMisAJour.getDateNaissance());

        return etudiantRepository.save(etudiant);
    }

    // ── Suppression (utilisée au J5 par le controller) ──
    public void delete(Long id) {
        Etudiant etudiant = findById(id);
        etudiantRepository.delete(etudiant);
    }
}