package org.restapiparkinglot.restapiparkinglot.setup;

import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;

public class ParkingLotSetup {
    public static ParkingLot createValidParkingLot(){
        return defaultBuilder().build();
    }
    public static ParkingLot updateValidParkingLot(){
        return defaultBuilder().id(1)
                .name("New parking lot test")
                .cnpj("12233344445555")
                .build();
    }
    public static  ParkingLot.ParkingLotBuilder defaultBuilder(){
        return ParkingLot.builder().id(1)
                .name("Parking lot test")
                .cnpj("12233344445555")
                .address("Avenue Test")
                .phoneNumber("11122233344")
                .carSpaces(10)
                .motorcycleSpaces(10);
    }
}
