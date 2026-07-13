package com.ipd.eduplus.inscription;

import com.ipd.eduplus.inscription.dto.InscriptionRequestDTO;
import com.ipd.eduplus.inscription.dto.InscriptionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @GetMapping
    public ResponseEntity<List<InscriptionResponseDTO>> findAll() {
        return ResponseEntity.ok(inscriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscriptionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(inscriptionService.findById(id));
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<InscriptionResponseDTO>> findByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(inscriptionService.findByEtudiantId(etudiantId));
    }

    @PostMapping
    public ResponseEntity<InscriptionResponseDTO> inscrire(@Valid @RequestBody InscriptionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inscriptionService.inscrire(dto.getEtudiantId(), dto.getCoursId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> annuler(@PathVariable Long id) {
        inscriptionService.annuler(id);
        return ResponseEntity.noContent().build();
    }
}