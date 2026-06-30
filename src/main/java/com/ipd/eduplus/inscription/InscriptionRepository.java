package com.ipd.eduplus.inscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    List<Inscription> findByEtudiantId(Long etudiantId);

    List<Inscription> findByCoursId(Long coursId);

    boolean existsByEtudiantIdAndCoursId(Long etudiantId, Long coursId);

    long countByCoursId(Long coursId);
}