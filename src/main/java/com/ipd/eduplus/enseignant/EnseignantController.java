// src/main/java/com/ipd/eduplus/enseignant/EnseignantController.java
package com.ipd.eduplus.enseignant;

import com.ipd.eduplus.enseignant.dto.EnseignantRequestDTO;
import com.ipd.eduplus.enseignant.dto.EnseignantResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    // GET /enseignants
    @GetMapping
    public ResponseEntity<List<EnseignantResponseDTO>> findAll() {
        return ResponseEntity.ok(enseignantService.findAll());
    }

    // GET /enseignants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EnseignantResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(enseignantService.findById(id));
    }

    // POST /enseignants
    @PostMapping
    public ResponseEntity<EnseignantResponseDTO> create(@Valid @RequestBody EnseignantRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enseignantService.create(dto));
    }

    // PUT /enseignants/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EnseignantResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EnseignantRequestDTO dto) {
        return ResponseEntity.ok(enseignantService.update(id, dto));
    }

    // DELETE /enseignants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enseignantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}