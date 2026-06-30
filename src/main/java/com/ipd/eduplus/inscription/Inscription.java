package com.ipd.eduplus.inscription;

import com.ipd.eduplus.cours.Cours;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscription", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"etudiant_id", "cours_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'étudiant est obligatoire")
    @Column(name = "etudiant_id", nullable = false)
    private Long etudiantId;

    @NotNull(message = "Le cours est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    @Builder.Default
    @Column(name = "date_inscription", nullable = false)
    private LocalDateTime dateInscription = LocalDateTime.now();
}