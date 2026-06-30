package com.ipd.eduplus.inscription;

import com.ipd.eduplus.cours.Cours;
import com.ipd.eduplus.cours.CoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final CoursRepository coursRepository;

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Inscription findById(Long id) {
        return inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée avec l'id : " + id));
    }

    public List<Inscription> findByEtudiantId(Long etudiantId) {
        return inscriptionRepository.findByEtudiantId(etudiantId);
    }

    public Inscription inscrire(Long etudiantId, Long coursId) {
        // Vérifier que le cours existe
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + coursId));

        // Vérifier que l'étudiant n'est pas déjà inscrit à ce cours
        if (inscriptionRepository.existsByEtudiantIdAndCoursId(etudiantId, coursId)) {
            throw new RuntimeException("Cet étudiant est déjà inscrit à ce cours");
        }

        // Vérifier que le cours n'a pas atteint sa capacité maximale
        long nbInscrits = inscriptionRepository.countByCoursId(coursId);
        if (nbInscrits >= cours.getCapacite()) {
            throw new RuntimeException("Le cours a atteint sa capacité maximale (" + cours.getCapacite() + " places)");
        }

        Inscription inscription = Inscription.builder()
                .etudiantId(etudiantId)
                .cours(cours)
                .build();

        return inscriptionRepository.save(inscription);
    }

    public void annuler(Long id) {
        findById(id);
        inscriptionRepository.deleteById(id);
    }
}