package br.com.parking.model;

import java.math.BigDecimal;

public class PaymentMethod extends Model{
	private String name;
	private BigDecimal charge;
	
	public PaymentMethod(String name, BigDecimal charge) {
		super();
		this.name = name;
		this.charge = charge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
}
