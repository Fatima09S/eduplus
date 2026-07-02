package com.ipd.eduplus.etudiant;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    // ── GET /etudiants?page=0&size=10&sort=nom ──
    @GetMapping
    public ResponseEntity<Page<Etudiant>> findAll(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<Etudiant> etudiants = etudiantService.findAll(pageable);
        return ResponseEntity.ok(etudiants);
    }

    // ── GET /etudiants/{id} ──
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> findById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.findById(id));
    }
}
