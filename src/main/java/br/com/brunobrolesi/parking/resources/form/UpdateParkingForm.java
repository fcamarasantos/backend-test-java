package br.com.brunobrolesi.parking.resources.form;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.Parking;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UpdateParkingForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parking update(Integer id, ParkingRepository parkingRepository) {
        Parking parking = parkingRepository.getById(id);

        parking.setName(this.name);

        return parking;
    }
}
