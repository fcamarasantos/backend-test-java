package org.restapiparkinglot.restapiparkinglot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.restapiparkinglot.restapiparkinglot.model.VehicleType;

@Data
@Builder
public class VehicleDTO {
    @NotBlank(message = "Must inform brand")
    private String brand;
    @NotBlank(message = "Must inform model")
    private String model;
    @NotBlank(message = "Must inform color")
    @Pattern(regexp = "^[a-zA-Z]*")
    private String color;
    @NotBlank(message = "Must inform license plate")
    private String licensePlate;
    @NotBlank(message = "Vehicle type must be car or motorcycle")
    private VehicleType vehicleType;
}
