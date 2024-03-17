package org.restapiparkinglot.restapiparkinglot.setup;

import org.restapiparkinglot.restapiparkinglot.dtos.ParkingLotDTO;

public class ParkingLotDTOSetup {
    public static ParkingLotDTO createValidParkingLot(){
        return defaultBuilder().build();
    }
    public static  ParkingLotDTO.ParkingLotDTOBuilder defaultBuilder(){
        return ParkingLotDTO.builder()
                .name("Parking lot test")
                .cnpj("12233344445555")
                .address("Avenue Test")
                .phoneNumber("11122233344")
                .carSpaces(10)
                .motorcycleSpaces(10);
    }
}
