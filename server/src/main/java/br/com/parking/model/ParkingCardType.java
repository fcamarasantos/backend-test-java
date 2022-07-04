package br.com.parking.model;

import java.math.BigDecimal;

public class ParkingCardType extends Model{
	private String name;
	private int hierachy;
	private BigDecimal priceHour;
	private VehicleType type;
	
	public ParkingCardType(String name, VehicleType type, BigDecimal price) {
		this.name = name;
		this.type = type;
		this.priceHour = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHierachy() {
		return hierachy;
	}
	public void setHierachy(int hierachy) {
		this.hierachy = hierachy;
	}
	public BigDecimal getPriceHour() {
		return priceHour;
	}
	public void setPriceHour(BigDecimal priceHour) {
		this.priceHour = priceHour;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	
	
}
