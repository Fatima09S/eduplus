package com.ipd.eduplus.cours;

import com.ipd.eduplus.cours.dto.CoursRequestDTO;
import com.ipd.eduplus.cours.dto.CoursResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cours")
@RequiredArgsConstructor
public class CoursController {

    private final CoursService coursService;

    @GetMapping
    public ResponseEntity<List<CoursResponseDTO>> findAll() {
        return ResponseEntity.ok(coursService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CoursResponseDTO> save(@Valid @RequestBody CoursRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coursService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursResponseDTO> update(@PathVariable Long id,
                                                   @Valid @RequestBody CoursRequestDTO dto) {
        return ResponseEntity.ok(coursService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coursService.delete(id);
        return ResponseEntity.noContent().build();
    }
}