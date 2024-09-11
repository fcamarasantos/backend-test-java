package com.harrisson.parking_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String establishmentName;
    private int totalVehicles;
    private int totalMotorcycles;
    private int totalCars;
    private LocalDateTime reportDate;
}