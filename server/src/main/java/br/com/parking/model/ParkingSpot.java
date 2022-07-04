package br.com.parking.model;

public class ParkingSpot extends Model{
	private String code;
	private VehicleType type;
	private ParkingLot parkingLot;
	private ParkingSpotStatus spotStatus;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	public ParkingSpotStatus getSpotStatus() {
		return spotStatus;
	}
	public void setSpotStatus(ParkingSpotStatus spotStatus) {
		this.spotStatus = spotStatus;
	}
	
}
