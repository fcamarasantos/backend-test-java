package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.dto.EstablishmentPostDTO;
import com.fcamara.parking.api.domain.entities.Establishment;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.repositories.EstablishmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstablishmentServiceTest {

    @InjectMocks EstablishmentService establishmentService;
    @Mock
    EstablishmentRepository establishmentRepository;

    @Test
    void shouldCreateEstablishmentWithSuccess() {
        Establishment establishment = getMock();

        var establishmentPostDTO = new EstablishmentPostDTO();
        BeanUtils.copyProperties(establishment, establishmentPostDTO);

        when(establishmentRepository.save(any(Establishment.class))).thenReturn(establishment);

        Establishment savedEstablishment = establishmentService.create(establishmentPostDTO);

        ArgumentCaptor<Establishment> establishmentCaptor = forClass(Establishment.class);
        verify(establishmentRepository).save(establishmentCaptor.capture());

        Establishment capturedEstablishment = establishmentCaptor.getValue();

        assertAll(
                () -> assertEquals("ESTACIONAMENTO CENTRAL", capturedEstablishment.getName()),
                () -> assertEquals("17465589000104", capturedEstablishment.getCnpj()),
                () -> assertEquals("(85) 1111-2222", capturedEstablishment.getPhone()),
                () -> assertEquals(25, capturedEstablishment.getCarSpots()),
                () -> assertEquals(20, capturedEstablishment.getMotorcycleSpots()),
                () -> assertNotNull(capturedEstablishment.getAddress()),
                () -> assertNotNull(savedEstablishment)
        );
    }

    @Test
    void shouldFindAllEstablishmentWithSuccess() {
        Establishment establishment = getMock();

        when(establishmentRepository.findAll()).thenReturn(List.of(establishment));

        List<Establishment> foundEstablishmentList = establishmentService.getAll();

        assertFalse(foundEstablishmentList.isEmpty());
        assertEquals(1, foundEstablishmentList.size());
    }


    @Test
    void shouldFindEstablishmentByIdWithSuccess() {
        Long establishmentId = 1L;
        Establishment establishment = getMock();
        establishment.setId(establishmentId);

        when(establishmentRepository.findById(1L)).thenReturn(Optional.of(establishment));

        Establishment foundEstablishment = establishmentService.getById(establishmentId);

        assertNotNull(foundEstablishment);
        assertEquals(establishment, foundEstablishment);
    }

    @Test
    public void shouldThrowsExceptionWhenEstablishmentIdNotExist() {
        Long establishmentId = 1L;

        when(establishmentRepository.findById(establishmentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            establishmentService.getById(establishmentId);
        });
    }

    @Test
    public void shouldDeleteEstablishmentWithSuccess() {
        Long establishmentId = 1L;

        establishmentService.delete(establishmentId);

        verify(establishmentRepository, times(1)).deleteById(establishmentId);
    }

    @Test
    public void shouldUpdateSuccessful() {
        Long establishmentId = 1L;
        String newName = "ESTACIONAMENTO CENTRO";
        Establishment establishmentDatabase = getMock();
        establishmentDatabase.setId(establishmentId);

        Establishment establishmentUpdated = getMock();
        establishmentUpdated.setId(establishmentId);
        establishmentUpdated.setName(newName);

        var establishmentPostDTO = new EstablishmentPostDTO();
        BeanUtils.copyProperties(establishmentDatabase, establishmentPostDTO);

        when(establishmentRepository.findById(1L)).thenReturn(Optional.of(establishmentDatabase));
        when(establishmentRepository.save(any(Establishment.class))).thenReturn(establishmentUpdated);

        Establishment updatedEstablishment = establishmentService.update(1L, establishmentPostDTO);

        assertNotNull(updatedEstablishment);
        assertEquals(newName, updatedEstablishment.getName());
    }

    private Establishment getMock(){
        return Establishment.builder()
                .name("ESTACIONAMENTO CENTRAL")
                .cnpj("17465589000104")
                .phone("(85) 1111-2222")
                .address("AV. SANTOS DUMONT, N. 1111")
                .carSpots(25)
                .motorcycleSpots(20)
                .build();
    }
}
