package com.ipd.eduplus.etudiant;

import com.ipd.eduplus.etudiant.dto.EtudiantRequestDTO;
import com.ipd.eduplus.etudiant.dto.EtudiantResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private EtudiantMapper etudiantMapper;

    @InjectMocks
    private EtudiantService etudiantService;

    private Etudiant etudiant;
    private EtudiantRequestDTO requestDTO;
    private EtudiantResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        // Données de test
        etudiant = new Etudiant();
        etudiant.setId(1L);
        etudiant.setNom("Diallo");
        etudiant.setPrenom("Amadou");
        etudiant.setEmail("amadou.diallo@ipd.sn");
        etudiant.setTelephone("771111111");
        etudiant.setNumeroEtudiant("IPD-2025-001");

        requestDTO = new EtudiantRequestDTO();
        requestDTO.setNom("Diallo");
        requestDTO.setPrenom("Amadou");
        requestDTO.setEmail("amadou.diallo@ipd.sn");
        requestDTO.setTelephone("771111111");
        requestDTO.setNumeroEtudiant("IPD-2025-001");

        responseDTO = new EtudiantResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNom("Diallo");
        responseDTO.setPrenom("Amadou");
        responseDTO.setEmail("amadou.diallo@ipd.sn");
    }

    // ── Test 1 : créer un étudiant avec succès ──
    @Test
    void create_shouldReturnResponseDTO_whenEmailNotExists() {
        when(etudiantRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(etudiantMapper.toEntity(requestDTO)).thenReturn(etudiant);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        when(etudiantMapper.toDTO(etudiant)).thenReturn(responseDTO);

        EtudiantResponseDTO result = etudiantService.create(requestDTO);

        assertNotNull(result);
        assertEquals("Diallo", result.getNom());
        assertEquals("amadou.diallo@ipd.sn", result.getEmail());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    // ── Test 2 : créer un étudiant avec email déjà existant ──
    @Test
    void create_shouldThrowException_whenEmailAlreadyExists() {
        when(etudiantRepository.existsByEmail(requestDTO.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> etudiantService.create(requestDTO));

        assertTrue(exception.getMessage().contains("existe deja"));
        verify(etudiantRepository, never()).save(any());
    }

    // ── Test 3 : trouver un étudiant par id ──
    @Test
    void findById_shouldReturnEtudiant_whenExists() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Diallo", result.getNom());
    }

    // ── Test 4 : étudiant introuvable par id ──
    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(etudiantRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> etudiantService.findById(99L));

        assertTrue(exception.getMessage().contains("introuvable"));
    }

    // ── Test 5 : supprimer un étudiant ──
    @Test
    void delete_shouldDeleteEtudiant_whenExists() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));
        doNothing().when(etudiantRepository).delete(etudiant);

        etudiantService.delete(1L);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }
}