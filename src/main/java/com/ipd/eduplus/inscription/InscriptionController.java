package com.ipd.eduplus.inscription;

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
    public ResponseEntity<List<Inscription>> findAll() {
        return ResponseEntity.ok(inscriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(inscriptionService.findById(id));
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Inscription>> findByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(inscriptionService.findByEtudiantId(etudiantId));
    }

    @PostMapping
    public ResponseEntity<Inscription> inscrire(
            @RequestParam Long etudiantId,
            @RequestParam Long coursId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscriptionService.inscrire(etudiantId, coursId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> annuler(@PathVariable Long id) {
        inscriptionService.annuler(id);
        return ResponseEntity.noContent().build();
    }
}