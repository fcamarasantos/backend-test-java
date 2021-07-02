package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.Parking;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.model.VehicleType;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.repositories.ParkingSpaceRepository;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class UpdateParkingForm {

    @NotEmpty
    private String name;
    private Integer carSpaces;
    private Integer motorcycleSpaces;

    public Integer getCarSpaces() {
        return carSpaces;
    }

    public void setCarSpaces(Integer carSpaces) {
        this.carSpaces = carSpaces;
    }

    public Integer getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public void setMotorcycleSpaces(Integer motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parking update(Integer id, ParkingRepository parkingRepository, ParkingSpaceRepository parkingSpaceRepository) {
        Parking parking = parkingRepository.getById(id);

        parking.setName(this.name);

        return parking;
    }

    private List<ParkingSpace> generateParkingSpaces(VehicleType type, int number, Parking parking) {
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            parkingSpaces.add(new ParkingSpace(null, type, parking));
        }
        return parkingSpaces;
    }
}
