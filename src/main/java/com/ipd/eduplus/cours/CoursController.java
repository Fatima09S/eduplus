package com.ipd.eduplus.cours;

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
    public ResponseEntity<List<Cours>> findAll() {
        return ResponseEntity.ok(coursService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> findById(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cours> save(@Valid @RequestBody Cours cours) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coursService.save(cours));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> update(@PathVariable Long id, @Valid @RequestBody Cours cours) {
        return ResponseEntity.ok(coursService.update(id, cours));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coursService.delete(id);
        return ResponseEntity.noContent().build();
    }
}