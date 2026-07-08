package com.ipd.eduplus.inscription;

import com.ipd.eduplus.cours.Cours;
import com.ipd.eduplus.cours.CoursRepository;
import com.ipd.eduplus.inscription.dto.InscriptionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final CoursRepository coursRepository;
    private final InscriptionMapper inscriptionMapper;

    public List<InscriptionResponseDTO> findAll() {
        return inscriptionRepository.findAll()
                .stream()
                .map(inscriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InscriptionResponseDTO findById(Long id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec l'id : " + id));
        return inscriptionMapper.toDTO(inscription);
    }

    public List<InscriptionResponseDTO> findByEtudiantId(Long etudiantId) {
        return inscriptionRepository.findByEtudiantId(etudiantId)
                .stream()
                .map(inscriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InscriptionResponseDTO inscrire(Long etudiantId, Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + coursId));

        if (inscriptionRepository.existsByEtudiantIdAndCoursId(etudiantId, coursId)) {
            throw new RuntimeException("Cet étudiant est déjà inscrit à ce cours");
        }

        long nbInscrits = inscriptionRepository.countByCoursId(coursId);
        if (nbInscrits >= cours.getCapacite()) {
            throw new RuntimeException("Le cours a atteint sa capacité maximale (" + cours.getCapacite() + " places)");
        }

        Inscription inscription = Inscription.builder()
                .etudiantId(etudiantId)
                .cours(cours)
                .build();

        return inscriptionMapper.toDTO(inscriptionRepository.save(inscription));
    }

    public void annuler(Long id) {
        inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec l'id : " + id));
        inscriptionRepository.deleteById(id);
    }
}