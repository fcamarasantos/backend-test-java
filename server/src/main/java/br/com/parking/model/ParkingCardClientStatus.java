package br.com.parking.model;

public class ParkingCardClientStatus extends Model{
	private String name;
	
	public ParkingCardClientStatus() {};

	public ParkingCardClientStatus(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
