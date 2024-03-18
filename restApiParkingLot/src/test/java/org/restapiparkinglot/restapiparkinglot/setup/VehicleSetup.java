package org.restapiparkinglot.restapiparkinglot.setup;

import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.restapiparkinglot.restapiparkinglot.model.VehicleType;

public class VehicleSetup {
    public static Vehicle createValidVehicle(){
        return defaultBuilder().build();
    }

    public static  Vehicle.VehicleBuilder defaultBuilder(){
        return Vehicle.builder().id(1)
                .model("Model test")
                .brand("Brand test")
                .licensePlate("JVA1A70")
                .color("blue")
                .vehicleType(VehicleType.valueOf("CAR"));
    }
}
