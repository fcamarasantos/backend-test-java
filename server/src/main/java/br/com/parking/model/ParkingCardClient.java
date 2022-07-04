package br.com.parking.model;

import java.math.BigDecimal;
import java.util.Date;

public class ParkingCardClient extends Model {
	private Date takeoff;
	private Date returnMoment;
	private Date checkout;
	private ClientAccount client;
	private ParkingCard parkingCard;
	private Vehicle vehicle;
	private ParkingCardClientStatus cardStatus;
	private Employee employee;
	private BigDecimal totalPrice;
	private BigDecimal totalPaid;
	
	
	
	public Date getTakeoff() {
		return takeoff;
	}
	public void setTakeoff(Date takeoff) {
		this.takeoff = takeoff;
	}
	public Date getReturnMoment() {
		return returnMoment;
	}
	public void setReturnMoment(Date returnMoment) {
		this.returnMoment = returnMoment;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	public ClientAccount getClient() {
		return client;
	}
	public void setClient(ClientAccount client) {
		this.client = client;
	}
	public ParkingCard getParkingCard() {
		return parkingCard;
	}
	public void setParkingCard(ParkingCard parkingCard) {
		this.parkingCard = parkingCard;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public ParkingCardClientStatus getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(ParkingCardClientStatus cardStatus) {
		this.cardStatus = cardStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}
	
	
	
	
}
