package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.entities.Establishment;
import com.fcamara.parking.api.domain.entities.Parking;
import com.fcamara.parking.api.domain.entities.Vehicle;
import com.fcamara.parking.api.domain.enums.VehicleType;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.repositories.ParkingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    @InjectMocks
    ParkingService parkingService;
    @Mock
    ParkingRepository parkingRepository;
    @Mock VehicleService vehicleService;
    @Mock EstablishmentService establishmentService;

    @Test
    public void shouldRegisterEntrySuccess() {
        // Arrange
        Long vehicleId = 1L;
        Long establishmentId = 1L;
        Vehicle vehicle = getVehicleMock();
        Establishment establishment = getEstablishmentMock();
        Parking expectedParking = Parking.builder()
                .vehicle(vehicle)
                .establishment(establishment)
                .entryTime(LocalDateTime.now())
                .build();

        when(vehicleService.getById(vehicleId)).thenReturn(vehicle);
        when(establishmentService.getById(establishmentId)).thenReturn(establishment);
        when(parkingRepository.save(any(Parking.class))).thenReturn(expectedParking);

        // Act
        Parking actualParking = parkingService.registerEntry(vehicleId, establishmentId);

        // Assert
        assertNotNull(actualParking);
        assertEquals(expectedParking.getVehicle(), actualParking.getVehicle());
        assertEquals(expectedParking.getEstablishment(), actualParking.getEstablishment());
        verify(parkingRepository, times(1)).save(any(Parking.class));
    }

    @Test
    public void shouldUpdatesExitTimeSuccessfully() {
        Parking parking = Parking.builder().id(1L).entryTime(LocalDateTime.now()).build();

        when(parkingRepository.findById(1L)).thenReturn(Optional.of(parking));
        when(parkingRepository.save(any(Parking.class))).thenReturn(parking);

        Parking updatedParking = parkingService.registerExit(1L);

        assertNotNull(updatedParking.getExitTime());
        verify(parkingRepository).save(parking);
    }

    @Test
    public void shouldFailWhenParkingRecordNotFound() {
        when(parkingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> parkingService.registerExit(1L));
    }

    @Test
    void shouldFindAllVehicleWithSuccess() {
        Parking parking = getMock();

        when(parkingRepository.findAll()).thenReturn(List.of(parking));

        List<Parking> foundParkingList = parkingService.getAll();

        assertFalse(foundParkingList.isEmpty());
        assertEquals(1, foundParkingList.size());
    }

    @Test
    void shouldFindByVehicleIdWithSuccess() {
        Long vehicleId = 1L;
        Parking parking = getMock();

        when(parkingRepository.findByVehicleId(1L)).thenReturn(List.of(parking));

        List<Parking> foundParkingList = parkingService.getParkingsByVehicle(vehicleId);

        assertFalse(foundParkingList.isEmpty());
        assertEquals(1, foundParkingList.size());
    }

    @Test
    void shouldFindByEstablishmentIdWithSuccess() {
        Long establishmentId = 1L;
        Parking parking = getMock();

        when(parkingRepository.findByEstablishmentId(1L)).thenReturn(List.of(parking));

        List<Parking> foundParkingList = parkingService.getParkingsByEstablishment(establishmentId);

        assertFalse(foundParkingList.isEmpty());
        assertEquals(1, foundParkingList.size());
    }

    private Parking getMock(){
        return Parking.builder()
                .vehicle(getVehicleMock())
                .establishment(getEstablishmentMock())
                .entryTime(LocalDateTime.now())
                .build();
    }

    private Establishment getEstablishmentMock(){
        return Establishment.builder()
                .id(1L)
                .name("ESTACIONAMENTO CENTRAL")
                .cnpj("17465589000104")
                .phone("(85) 1111-2222")
                .address("AV. SANTOS DUMONT, N. 1111")
                .carSpots(25)
                .motorcycleSpots(20)
                .build();
    }

    private Vehicle getVehicleMock(){
        return Vehicle.builder()
                .id(1L)
                .brand("FIAT")
                .model("UNO")
                .color("PRETO")
                .licensePlate("XXX1111")
                .type(VehicleType.CAR)
                .build();
    }
}
