package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.dto.VehiclePostDTO;
import com.fcamara.parking.api.domain.entities.Vehicle;
import com.fcamara.parking.api.domain.enums.VehicleType;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.repositories.VehicleRepository;
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
public class VehicleServiceTest {

    @InjectMocks VehicleService vehicleService;
    @Mock
    VehicleRepository vehicleRepository;

    @Test
    void shouldCreateVehicleWithSuccess() {
        Vehicle vehicle = getMock();

        var vehiclePostDTO = new VehiclePostDTO();
        BeanUtils.copyProperties(vehicle, vehiclePostDTO);

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle savedVehicle = vehicleService.create(vehiclePostDTO);

        ArgumentCaptor<Vehicle> vehicleCaptor = forClass(Vehicle.class);
        verify(vehicleRepository).save(vehicleCaptor.capture());

        Vehicle capturedVehicle = vehicleCaptor.getValue();

        assertAll(
                () -> assertEquals("FIAT", capturedVehicle.getBrand()),
                () -> assertEquals("UNO", capturedVehicle.getModel()),
                () -> assertEquals("PRETO", capturedVehicle.getColor()),
                () -> assertEquals("XXX1111", capturedVehicle.getLicensePlate()),
                () -> assertEquals(VehicleType.CAR, capturedVehicle.getType()),
                () -> assertNotNull(savedVehicle)
        );
    }

    @Test
    void shouldFindAllVehicleWithSuccess() {
        Vehicle vehicle = getMock();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        List<Vehicle> foundVehicleList = vehicleService.getAll();

        assertFalse(foundVehicleList.isEmpty());
        assertEquals(1, foundVehicleList.size());
    }

    @Test
    void shouldFindVehicleByIdWithSuccess() {
        Long vehicleId = 1L;
        Vehicle vehicle = getMock();
        vehicle.setId(vehicleId);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Vehicle foundVehicle = vehicleService.getById(vehicleId);

        assertNotNull(foundVehicle);
        assertEquals(vehicle, foundVehicle);
    }

    @Test
    public void shouldThrowsExceptionWhenVehicleIdNotExist() {
        Long vehicleId = 1L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            vehicleService.getById(vehicleId);
        });
    }

    @Test
    public void shouldDeleteVehicleWithSuccess() {
        Long vehicleId = 1L;

        vehicleService.delete(vehicleId);

        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }

    @Test
    public void shouldUpdateSuccessful() {
        Long vehicleId = 1L;
        String newModelName = "UNO MILE";
        Vehicle vehicleDatabase = getMock();
        vehicleDatabase.setId(vehicleId);

        Vehicle vehicleUpdated = getMock();
        vehicleUpdated.setId(vehicleId);
        vehicleUpdated.setModel(newModelName);

        var vehiclePostDTO = new VehiclePostDTO();
        BeanUtils.copyProperties(vehicleDatabase, vehiclePostDTO);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicleDatabase));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicleUpdated);

        Vehicle updatedVehicle = vehicleService.update(1L, vehiclePostDTO);

        assertNotNull(updatedVehicle);
        assertEquals(newModelName, updatedVehicle.getModel());
    }

    private Vehicle getMock(){
        return Vehicle.builder()
                .brand("FIAT")
                .model("UNO")
                .color("PRETO")
                .licensePlate("XXX1111")
                .type(VehicleType.CAR)
                .build();
    }
}

