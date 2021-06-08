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
                "ALX8182",
                "Preto",
                "Carro");
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
        Assertions.fail();
    }

    @Test
    void vehicleTypeMustBeInLowerCase() {
        Assertions.fail();
    }

    @Test
    void vehicleTypeMustValid() {
        Assertions.fail();
    }

    @Test
    void licensePlateMustBeInUpperCase() {
        Assertions.fail();
    }

    @Test
    void licensePlateMustBeValid(){
        Assertions.fail();
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
