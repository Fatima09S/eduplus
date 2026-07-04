package com.ipd.eduplus.etudiant;

import com.ipd.eduplus.etudiant.dto.EtudiantRequestDTO;
import com.ipd.eduplus.etudiant.dto.EtudiantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    // Liste paginée
    public Page<Etudiant> findAll(Pageable pageable) {
        return etudiantRepository.findAll(pageable);
    }

    // Recherche par id
    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable avec l'id : " + id));
    }

    // Création avec DTO
    public EtudiantResponseDTO create(EtudiantRequestDTO dto) {
        if (etudiantRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un etudiant avec cet email existe deja : " + dto.getEmail());
        }
        Etudiant etudiant = etudiantMapper.toEntity(dto);
        Etudiant saved = etudiantRepository.save(etudiant);
        return (EtudiantResponseDTO) etudiantMapper.toDTO(saved);
    }

    // Mise à jour
    public Etudiant update(Long id, Etudiant etudiantMisAJour) {
        Etudiant etudiant = findById(id);
        etudiant.setNom(etudiantMisAJour.getNom());
        etudiant.setPrenom(etudiantMisAJour.getPrenom());
        etudiant.setEmail(etudiantMisAJour.getEmail());
        etudiant.setTelephone(etudiantMisAJour.getTelephone());
        etudiant.setDateNaissance(etudiantMisAJour.getDateNaissance());
        return etudiantRepository.save(etudiant);
    }

    // Suppression
    public void delete(Long id) {
        Etudiant etudiant = findById(id);
        etudiantRepository.delete(etudiant);
    }
}