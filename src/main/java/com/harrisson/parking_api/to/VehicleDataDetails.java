package com.harrisson.parking_api.to;

import com.harrisson.parking_api.enums.Type;
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

    public Vehicle toEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(this.id);
        vehicle.setBrand(this.brand);
        vehicle.setModel(this.model);
        vehicle.setColor(this.color);
        vehicle.setPlate(this.plate);
        vehicle.setType(this.type != null ? Type.valueOf(this.type) : null);
        return vehicle;
    }
}
