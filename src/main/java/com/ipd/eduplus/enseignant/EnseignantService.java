// src/main/java/com/ipd/eduplus/enseignant/EnseignantService.java
package com.ipd.eduplus.enseignant;

import com.ipd.eduplus.enseignant.dto.EnseignantRequestDTO;
import com.ipd.eduplus.enseignant.dto.EnseignantResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;

    public EnseignantService(EnseignantRepository enseignantRepository,
                             EnseignantMapper enseignantMapper) {
        this.enseignantRepository = enseignantRepository;
        this.enseignantMapper = enseignantMapper;
    }


    // Remplace l'ancienne méthode findAll()
    public Page<EnseignantResponseDTO> findAll(Pageable pageable) {
        return enseignantRepository.findAll(pageable)
                .map(enseignantMapper::toDTO);
    }

    public EnseignantResponseDTO findById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec l'id : " + id));
        return enseignantMapper.toDTO(enseignant);
    }

    public EnseignantResponseDTO create(EnseignantRequestDTO dto) {
        if (enseignantRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un enseignant avec cet email existe deja : " + dto.getEmail());
        }
        Enseignant enseignant = enseignantMapper.toEntity(dto);
        return enseignantMapper.toDTO(enseignantRepository.save(enseignant));
    }

    public EnseignantResponseDTO update(Long id, EnseignantRequestDTO dto) {
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec l'id : " + id));
        enseignant.setNom(dto.getNom());
        enseignant.setPrenom(dto.getPrenom());
        enseignant.setEmail(dto.getEmail());
        enseignant.setTelephone(dto.getTelephone());
        enseignant.setSpecialite(dto.getSpecialite());
        enseignant.setDateEmbauche(dto.getDateEmbauche());
        return enseignantMapper.toDTO(enseignantRepository.save(enseignant));
    }

    public void delete(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec l'id : " + id));
        enseignantRepository.delete(enseignant);
    }
}