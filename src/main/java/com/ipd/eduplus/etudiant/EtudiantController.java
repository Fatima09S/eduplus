package com.ipd.eduplus.etudiant;

import com.ipd.eduplus.etudiant.dto.EtudiantRequestDTO;
import com.ipd.eduplus.etudiant.dto.EtudiantResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final EtudiantMapper etudiantMapper;

    // GET /etudiants?page=0&size=10&sort=nom
    @GetMapping
    public ResponseEntity<Page<EtudiantResponseDTO>> findAll(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<EtudiantResponseDTO> etudiants = etudiantService.findAll(pageable)
                .map(etudiantMapper::toDTO);
        return ResponseEntity.ok(etudiants);
    }

    // GET /etudiants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantResponseDTO> findById(@PathVariable Long id) {
        Etudiant etudiant = etudiantService.findById(id);
        return ResponseEntity.ok((EtudiantResponseDTO) etudiantMapper.toDTO(etudiant));
    }

    // POST /etudiants
    @PostMapping
    public ResponseEntity<EtudiantResponseDTO> create(@Valid @RequestBody EtudiantRequestDTO dto) {
        EtudiantResponseDTO created = etudiantService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}