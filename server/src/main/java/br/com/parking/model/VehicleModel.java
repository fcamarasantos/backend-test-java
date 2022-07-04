package br.com.parking.model;

import java.util.Date;

public class VehicleModel extends Model{
	private String name;
	private VehicleBrand vehicleBrand;
	private Date year;
	private VehicleType type;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleBrand getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(VehicleBrand vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	
	
}
