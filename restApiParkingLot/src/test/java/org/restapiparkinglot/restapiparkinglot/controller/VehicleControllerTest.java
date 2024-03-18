package org.restapiparkinglot.restapiparkinglot.controller;


import org.restapiparkinglot.restapiparkinglot.dtos.VehicleDTO;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.restapiparkinglot.restapiparkinglot.services.VehicleService;
import org.restapiparkinglot.restapiparkinglot.setup.VehicleDTOSetup;
import org.restapiparkinglot.restapiparkinglot.setup.VehicleSetup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    private static final Vehicle VALID_VEHICLE = VehicleSetup.createValidVehicle();

    @BeforeEach
    void setUp() throws NotFoundException {
        when(vehicleService.create(ArgumentMatchers.any(VehicleDTO.class))).thenReturn(VALID_VEHICLE);
        when(vehicleService.findAll()).thenReturn(List.of(VALID_VEHICLE));
        when(vehicleService.retrieveById(ArgumentMatchers.anyInt())).thenReturn(VALID_VEHICLE);
        when(vehicleService.retrieveByLicensePlate(ArgumentMatchers.anyString())).thenReturn(VALID_VEHICLE);
        when(vehicleService.delete(ArgumentMatchers.anyInt())).thenReturn("Vehicle deleted");
    }

    @Test
    void createSaveVehicleWhenSuccessful() {

        ResponseEntity<Object> vehicleResponseEntity = vehicleController.createVehicle(VehicleDTOSetup.createValidVehicle());
        Vehicle body = (Vehicle) vehicleResponseEntity.getBody();

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.CREATED);

        assertVehicleFields(body);
    }


    @Test
    void deleteReturnDeletedVehicleWhenSuccessful() {
        ResponseEntity<String> vehicleResponseEntity = vehicleController.deleteVehicle(VALID_VEHICLE.getId());

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void listAllReturnListOfVehicleWhenSuccessful() {
        ResponseEntity<List<Vehicle>> vehicleResponseEntity = vehicleController.findAllVehicles();
        List<Vehicle> body = vehicleResponseEntity.getBody();

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull().isNotEmpty().hasSize(1).contains(VALID_VEHICLE);
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void listAllReturnEmptyListOfVehicleWhenSuccessful() {
        when(vehicleService.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Vehicle>> vehicleResponseEntity = vehicleController.findAllVehicles();
        List<Vehicle> body = vehicleResponseEntity.getBody();

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull().isEmpty();
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void findByIdReturnVehicleWhenSuccessful() throws NotFoundException {
        ResponseEntity<Object> vehicleResponseEntity = vehicleController.findVehicleById(VALID_VEHICLE.getId());
        Vehicle body = (Vehicle) vehicleResponseEntity.getBody();

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(1);
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void findByLicensePlateReturnVehicleWhenSuccessful() throws NotFoundException {
        ResponseEntity<Object> vehicleResponseEntity = vehicleController.findVehicleByLicensePlate(VALID_VEHICLE.getLicensePlate());
        Vehicle body = (Vehicle) vehicleResponseEntity.getBody();

        Assertions.assertThat(vehicleResponseEntity).isNotNull();
        Assertions.assertThat((body.getLicensePlate())).isNotNull().isEqualTo("JVA1A70"); //testando p da errado
        Assertions.assertThat(vehicleResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);

    }

    private void assertVehicleFields(Vehicle vehicle) {
        Assertions.assertThat(vehicle.getBrand()).isNotNull().isNotEmpty();
        Assertions.assertThat(vehicle.getModel()).isNotNull().isNotEmpty();
        Assertions.assertThat(vehicle.getColor()).isNotNull().isNotEmpty();
        Assertions.assertThat(vehicle.getLicensePlate()).isNotNull().isNotEmpty();
        Assertions.assertThat(vehicle.getVehicleType()).isNotNull();
    }
}
