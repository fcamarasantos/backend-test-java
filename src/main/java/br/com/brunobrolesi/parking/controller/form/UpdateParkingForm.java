package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.Parking;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.model.VehicleType;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.repositories.ParkingSpaceRepository;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateParkingForm {

    @NotEmpty
    private String name;

    private String phone1;
    private String phone2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    private List<ParkingSpace> generateParkingSpaces(VehicleType type, int number, Parking parking) {
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            parkingSpaces.add(new ParkingSpace(null, type, parking));
        }
        return parkingSpaces;
    }

    public Parking converterParking() {
        Parking parking = new Parking(
                null,
                null,
                this.name
        );
        parking.getPhones().addAll(Arrays.asList(phone1,phone2));
        return  parking;
    }
}
