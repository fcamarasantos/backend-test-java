package br.com.parking.model;

import java.util.Date;

public class VehicleStyle extends Model {
	private String name;
	private VehicleType type;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}
	@Override
	public Date getUpdate() {
		// TODO Auto-generated method stub
		return super.getUpdate();
	}
	@Override
	public void setUpdate(Date update) {
		// TODO Auto-generated method stub
		super.setUpdate(update);
	}
	@Override
	public Date getCreate() {
		// TODO Auto-generated method stub
		return super.getCreate();
	}
	@Override
	public void setCreate(Date create) {
		// TODO Auto-generated method stub
		super.setCreate(create);
	}
	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return super.getStatus();
	}
	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		super.setStatus(status);
	}
}
