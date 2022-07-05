package br.com.parking.tests;

import org.junit.Assert;
import org.junit.Test;

import br.com.parking.controller.Routes;
import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import myExceptions.RouteNotFoundException;

public class ControllerTest {
	
	@Test
	public void shouldReturnCompanyActionInstance() throws RouteNotFoundException {
		String URI = "ParkingLot/company/";
		
		Routes routes = new Routes();
		Action action = routes.getAction(URI);
		
		Assert.assertEquals(action.getClass(), Company.class);
	}
	
	@Test
	public void shouldReturnVehicleInstance() throws RouteNotFoundException {
		String URI = "ParkingLot/vehicle/";
		
		Routes routes = new Routes();
		Action action = routes.getAction(URI);
		
		Assert.assertEquals(action.getClass(), Company.class);
	}
	
	@Test
//	public void shouldReturnVehicleStyle() {
//		String URI = "ParkingLot/vehicle/style";
//		
//		Routes routes = new Routes();
//		Action action = routes.getAction(URI);
//		
//		Assert.assertEquals(action.getClass(), Style.class);
//	}
	
	public void shouldReturnVehicleType() throws RouteNotFoundException {
		String URI = "ParkingLot/vehicle/type";
		
		Routes routes = new Routes();
		Action action = routes.getAction(URI);
		
		Assert.assertEquals(action.getClass(), Company.class);
	}
}
