package com.ipd.eduplus.cours;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursService {

    private final CoursRepository coursRepository;

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Cours findById(Long id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + id));
    }

    public Cours save(Cours cours) {
        if (coursRepository.existsByCode(cours.getCode())) {
            throw new RuntimeException("Un cours avec le code " + cours.getCode() + " existe déjà");
        }
        return coursRepository.save(cours);
    }

    public Cours update(Long id, Cours cours) {
        Cours existing = findById(id);
        existing.setTitre(cours.getTitre());
        existing.setDescription(cours.getDescription());
        existing.setCapacite(cours.getCapacite());
        existing.setCode(cours.getCode());
        return coursRepository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        coursRepository.deleteById(id);
    }
}