package br.com.parking.model;

public class Vehicle extends Model{
	private String plate;
	private VehicleModel model;
	private VehicleColor color;
	private VehicleStyle style;
	private ClientAccount client;
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public VehicleModel getModel() {
		return model;
	}
	public void setModel(VehicleModel model) {
		this.model = model;
	}
	public VehicleColor getColor() {
		return color;
	}
	public void setColor(VehicleColor color) {
		this.color = color;
	}
	public VehicleStyle getStyle() {
		return style;
	}
	public void setStyle(VehicleStyle style) {
		this.style = style;
	}
	public ClientAccount getClient() {
		return client;
	}
	public void setClient(ClientAccount client) {
		this.client = client;
	}
	
	
}
