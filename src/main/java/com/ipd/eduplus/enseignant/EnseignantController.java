// src/main/java/com/ipd/eduplus/enseignant/EnseignantController.java
package com.ipd.eduplus.enseignant;

import com.ipd.eduplus.enseignant.dto.EnseignantRequestDTO;
import com.ipd.eduplus.enseignant.dto.EnseignantResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    // GET /enseignants?page=0&size=10&sort=nom&direction=asc
    @GetMapping
    public ResponseEntity<Page<EnseignantResponseDTO>> findAll(
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "nom")  String sort,
            @RequestParam(defaultValue = "asc")  String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        return ResponseEntity.ok(enseignantService.findAll(pageable));
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