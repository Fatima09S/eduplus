package com.ipd.eduplus.cours;

import com.ipd.eduplus.cours.dto.CoursRequestDTO;
import com.ipd.eduplus.cours.dto.CoursResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursService {

    private final CoursRepository coursRepository;
    private final CoursMapper coursMapper;

    public List<CoursResponseDTO> findAll() {
        return coursRepository.findAll()
                .stream()
                .map(coursMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CoursResponseDTO findById(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + id));
        return coursMapper.toDTO(cours);
    }

    public CoursResponseDTO save(CoursRequestDTO dto) {
        if (coursRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Un cours avec le code " + dto.getCode() + " existe déjà");
        }
        Cours cours = coursMapper.toEntity(dto);
        return coursMapper.toDTO(coursRepository.save(cours));
    }

    public CoursResponseDTO update(Long id, CoursRequestDTO dto) {
        Cours existing = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + id));
        existing.setTitre(dto.getTitre());
        existing.setDescription(dto.getDescription());
        existing.setCapacite(dto.getCapacite());
        existing.setCode(dto.getCode());
        return coursMapper.toDTO(coursRepository.save(existing));
    }

    public void delete(Long id) {
        coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + id));
        coursRepository.deleteById(id);
    }

    public Cours findEntityById(Long id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'id : " + id));
    }
}