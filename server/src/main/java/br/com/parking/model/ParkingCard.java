package br.com.parking.model;

public class ParkingCard extends Model{
	private String code;
	private ParkingLot parkingLot;
	private ParkingCardType parkingType;
	
	public ParkingCard(String code, ParkingLot parkingLot, ParkingCardType parkingType) {
		this.code = code;
		this.parkingLot = parkingLot;
		this.parkingType = parkingType;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	public ParkingCardType getParkingType() {
		return parkingType;
	}
	public void setParkingType(ParkingCardType parkingType) {
		this.parkingType = parkingType;
	}
	
	
}
