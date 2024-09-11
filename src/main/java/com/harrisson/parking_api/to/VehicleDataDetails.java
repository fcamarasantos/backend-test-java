package com.harrisson.parking_api.to;

import com.harrisson.parking_api.model.Vehicle;

public record VehicleDataDetails(
        Long id,
        String brand,
        String model,
        String color,
        String plate,
        String type
) {
    public VehicleDataDetails(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getColor(), vehicle.getPlate(), vehicle.getType().name());
    }
}
