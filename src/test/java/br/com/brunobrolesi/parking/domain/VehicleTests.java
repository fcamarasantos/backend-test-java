package br.com.brunobrolesi.parking.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void yearMustHaveFourDigits() {
        Assertions.fail();
    }

    @Test
    void yearMustBeValid() {
        Assertions.fail();
    }
}
