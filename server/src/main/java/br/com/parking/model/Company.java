package br.com.parking.model;

import java.util.Date;

public class Company extends Model{
	private String name;
	private String cnpj;
	private String phone;
	
	@Override
	public int getId() {
		return super.getId();
	}
	@Override
	public void setId(int id) {
		super.setId(id);
	}
	@Override
	public Date getUpdate() {
		// TODO Auto-generated method stub
		return super.getUpdate();
	}
	@Override
	public void setUpdate(Date update) {
		super.setUpdate(update);
	}
	@Override
	public Date getCreate() {
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return (
			"{name: " + this.name + ", phone: " + this.phone + ", cnpj: " + this.cnpj + ", id: " + this.id + "}"	
		);
	}
}
