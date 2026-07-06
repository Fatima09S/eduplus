// src/main/java/com/ipd/eduplus/enseignant/EnseignantRepository.java
package com.ipd.eduplus.enseignant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Optional<Enseignant> findByEmail(String email);
    boolean existsByEmail(String email);
}