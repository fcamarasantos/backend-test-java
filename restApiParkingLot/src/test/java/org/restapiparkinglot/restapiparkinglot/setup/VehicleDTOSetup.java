package org.restapiparkinglot.restapiparkinglot.setup;

import org.restapiparkinglot.restapiparkinglot.dtos.VehicleDTO;
import org.restapiparkinglot.restapiparkinglot.model.VehicleType;

public class VehicleDTOSetup {
    public static VehicleDTO createValidVehicle(){
        return defaultBuilder().build();
    }

    public static  VehicleDTO.VehicleDTOBuilder defaultBuilder(){
        return VehicleDTO.builder()
                .model("Model test")
                .brand("Brand test")
                .licensePlate("JVA1A70")
                .color("blue")
                .vehicleType(VehicleType.valueOf("CAR"));
    }
}


