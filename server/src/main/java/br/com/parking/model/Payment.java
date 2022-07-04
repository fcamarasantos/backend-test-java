package br.com.parking.model;

import java.math.BigDecimal;

public class Payment extends Model{
	private PaymentMethod paymentMethod;
	private PaymentStatus paymentStatus;
	private Employee employee;
	private ClientAccount client;
	private BigDecimal inicialPrice;
	private BigDecimal finalPrice;
	private BigDecimal discount;
	private BigDecimal PaidAmount;
	
	
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ClientAccount getClient() {
		return client;
	}
	public void setClient(ClientAccount client) {
		this.client = client;
	}
	public BigDecimal getInicialPrice() {
		return inicialPrice;
	}
	public void setInicialPrice(BigDecimal inicialPrice) {
		this.inicialPrice = inicialPrice;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getPaidAmount() {
		return PaidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		PaidAmount = paidAmount;
	}
	
	
}
