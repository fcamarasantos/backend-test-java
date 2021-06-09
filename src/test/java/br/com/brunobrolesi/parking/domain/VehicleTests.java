package br.com.brunobrolesi.parking.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VehicleTests {
    private Vehicle vehicle;

    @BeforeEach
    void createVeichle() {
        this.vehicle = new Vehicle(
                "FORD",
                "Fusion",
                "2018",
                "Preto",
                "ALX8182",
                VehicleType.CARRO);
    }

    @Test
    void manufacturerMustBeInLowerCase() {
        Assertions.assertTrue(vehicle.getManufacturer().matches("^[a-z0-9]*$"));

        this.vehicle.setManufacturer("REnaulT");
        Assertions.assertTrue(vehicle.getManufacturer().matches("^[a-z0-9]*$"));
    }

    @Test
    void modelMustBeInLowerCase() {
        Assertions.assertTrue(vehicle.getModel().matches("^[a-z0-9]*$"));

        this.vehicle.setModel("KA");
        Assertions.assertTrue(vehicle.getModel().matches("^[a-z0-9]*$"));
    }

    @Test
    void colorMustBeInLowerCase() {
        Assertions.assertTrue(vehicle.getColor().matches("^[a-z]*$"));

        this.vehicle.setColor("BrancO");
        Assertions.assertTrue(vehicle.getColor().matches("^[a-z]*$"));
    }

    @Test
    void licensePlateMustBeInUpperCase() {
        Assertions.assertTrue(vehicle.getLicensePlate().matches("^[A-Z0-9]*$"));

        this.vehicle.setLicensePlate("aLx8182");
        Assertions.assertTrue(vehicle.getLicensePlate().matches("^[A-Z0-9]*$"));
    }

    @Test
    void licensePlateMustBeValid(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Vehicle(
                        "FORD",
                        "Fusion",
                        "2018",
                        "Preto",
                        "A8182",
                        VehicleType.CARRO)
                );

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> vehicle.setLicensePlate("A8182"));
    }

    @Test
    void yearMustHaveFourDigits() {
        Assertions.assertTrue(vehicle.getYear().matches("[0-9]{4}"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Vehicle(
                        "FORD",
                        "Fusion",
                        "218",
                        "Preto",
                        "ALX8182",
                        VehicleType.CARRO)
        );

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> vehicle.setYear("12332")
        );
    }

    @Test
    void yearMustBeValid() {
       Assertions.assertThrows(IllegalArgumentException.class,
               () -> new Vehicle(
                       "FORD",
                       "Fusion",
                       "1800",
                       "Preto",
                       "ALX8182",
                       VehicleType.CARRO)
       );

       Assertions.assertThrows(IllegalArgumentException.class,
               () -> vehicle.setYear(String.valueOf(LocalDate.now().getYear() + 2)));
    }
}
