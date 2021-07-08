package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.model.ParkingSpaceState;
import br.com.brunobrolesi.parking.model.VehicleType;

public class ParkingSpaceForm {

    private Integer vehicleTypeId;

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public ParkingSpace converterParkingSpace() {
        ParkingSpace parkingSpace = new ParkingSpace(null, VehicleType.toEnum(vehicleTypeId), null);
        return parkingSpace;
    }
}
