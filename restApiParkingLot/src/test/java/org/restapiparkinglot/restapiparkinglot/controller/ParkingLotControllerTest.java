package org.restapiparkinglot.restapiparkinglot.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.restapiparkinglot.restapiparkinglot.dtos.ParkingLotDTO;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.restapiparkinglot.restapiparkinglot.services.ParkingLotService;
import org.restapiparkinglot.restapiparkinglot.setup.ParkingLotDTOSetup;
import org.restapiparkinglot.restapiparkinglot.setup.ParkingLotSetup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ParkingLotControllerTest {
    @InjectMocks
    private ParkingLotController parkingLotController;

    @Mock
    private ParkingLotService parkingLotService;

    private static final ParkingLot VALID_PARKINGLOT = ParkingLotSetup.createValidParkingLot();

    @BeforeEach
    void setUp() throws NotFoundException{
        when(parkingLotService.create(ArgumentMatchers.any(ParkingLotDTO.class))).thenReturn(VALID_PARKINGLOT);
        when(parkingLotService.findAll()).thenReturn(List.of(VALID_PARKINGLOT));
        when(parkingLotService.retrieveById(ArgumentMatchers.anyInt())).thenReturn(VALID_PARKINGLOT);
        when(parkingLotService.retrieveByCnpj(ArgumentMatchers.anyString())).thenReturn(VALID_PARKINGLOT);
        when(parkingLotService.delete(ArgumentMatchers.anyInt())).thenReturn("Parking Lot deleted");
    }

    @Test
    void createSaveParkingLotWhenSuccessful(){
        ResponseEntity<Object> parkingLotResponseEntity = parkingLotController.createParkingLot(ParkingLotDTOSetup.createValidParkingLot());
        ParkingLot body = (ParkingLot) parkingLotResponseEntity.getBody();

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.CREATED);

        assertParkingLotFields(body);
    }
    @Test
    void deleteReturnDeletedParkingLotWhenSuccessful(){
        ResponseEntity <String> parkingLotResponseEntity = parkingLotController.deleteParkingLot(VALID_PARKINGLOT.getId());

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void listAllReturnListOfParkingLotWhenSuccessful() {
        ResponseEntity<List<ParkingLot>> parkingLotResponseEntity = parkingLotController.findAllParkingLot();
        List<ParkingLot> body = parkingLotResponseEntity.getBody();

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull().isNotEmpty().hasSize(1).contains(VALID_PARKINGLOT);
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void listAllReturnEmptyListOfParkingLotWhenSuccessful() {
        when(parkingLotService.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<ParkingLot>> parkingLotResponseEntity = parkingLotController.findAllParkingLot();
        List<ParkingLot> body = parkingLotResponseEntity.getBody();

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat(body).isNotNull().isEmpty();
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void findByIdReturnParkingLotWhenSuccessful() throws NotFoundException {
        ResponseEntity<Object> parkingLotResponseEntity = parkingLotController.findParkingLotById(VALID_PARKINGLOT.getId());
        ParkingLot body = (ParkingLot) parkingLotResponseEntity.getBody();

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(1);
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
    }

    @Test
    void findByCnpjReturnParkingLotWhenSuccessful() throws NotFoundException {
        ResponseEntity<Object> parkingLotResponseEntity = parkingLotController.findParkingLotByCnpj(VALID_PARKINGLOT.getCnpj());
        ParkingLot body = (ParkingLot) parkingLotResponseEntity.getBody();

        Assertions.assertThat(parkingLotResponseEntity).isNotNull();
        Assertions.assertThat((body.getCnpj())).isNotNull().isEqualTo("12233344445555");
        Assertions.assertThat(parkingLotResponseEntity.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);

    }
    @Test
    void update_UpdateEstabelecimento_whenSucessful (){


    }
    private void assertParkingLotFields(ParkingLot parkingLot) {
        Assertions.assertThat(parkingLot.getName()).isNotNull().isNotEmpty();
        Assertions.assertThat(parkingLot.getCnpj()).isNotNull().isNotEmpty();
        Assertions.assertThat(parkingLot.getAddress()).isNotNull().isNotEmpty();
        Assertions.assertThat(parkingLot.getPhoneNumber()).isNotNull().isNotEmpty();
        Assertions.assertThat(parkingLot.getCarSpaces()).isNotNull();
        Assertions.assertThat(parkingLot.getMotorcycleSpaces()).isNotNull();
    }

}
