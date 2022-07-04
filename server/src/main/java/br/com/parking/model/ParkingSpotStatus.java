package br.com.parking.model;

public class ParkingSpotStatus extends Model{
	private String name;
	
	public ParkingSpotStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
