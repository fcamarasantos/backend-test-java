package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.model.ParkingSpaceState;
import br.com.brunobrolesi.parking.model.VehicleType;

public class UpdateParkingSpaceForm {

    private int vehicleTypeId;
    private int stateId;

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public ParkingSpace converterParkingSpace() {
        ParkingSpace parkingSpace = new ParkingSpace(null, VehicleType.toEnum(vehicleTypeId), null);
        parkingSpace.setState(ParkingSpaceState.toEnum(stateId));
        return parkingSpace;
    }
}
