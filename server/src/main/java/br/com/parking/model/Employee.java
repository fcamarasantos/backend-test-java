package br.com.parking.model;

import java.util.Date;

public class Employee extends Model{
	private String name;
	private String login;
	private String password;
	private Company comp;
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Company getComp() {
		return comp;
	}
	public void setComp(Company comp) {
		this.comp = comp;
	}
	
}
