package com.harrisson.parking_api.to;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.Vehicle;

public record VehicleData(
        String brand,
        String model,
        String color,
        String plate,
        Type type
) {
    public VehicleData(Vehicle vehicle) {
        this(vehicle.getBrand(), vehicle.getModel(), vehicle.getColor(), vehicle.getPlate(), vehicle.getType());
    }
}
