package br.com.parking.tests;

import org.junit.Assert;
import org.junit.Test;

import br.com.parking.controller.Routes;
import br.com.parking.service.actions.Action;
import br.com.parking.service.actions.company.CreateCompanyAction;
import br.com.parking.service.actions.employee.UpdateEmployeeAction;
import myExceptions.MissingDataException;
import myExceptions.RouteNotFoundException;

public class RoutesTests {

	@Test
	public void shoudReturnCreateCompanyInstance() throws RouteNotFoundException {
		String Path = "/ParkingLot/company/create";

		Routes routes = new Routes();
		Action action = routes.getAction(Path);
		
		Assert.assertEquals(action.getClass(), CreateCompanyAction.class);
	}
	
	@Test
	public void shoudReturnUpdateEmployeeInstance() throws RouteNotFoundException {
		String Path = "/ParkingLot/employee/update";

		Routes routes = new Routes();
		Action action = routes.getAction(Path);
		
		Assert.assertEquals(action.getClass(), UpdateEmployeeAction.class);
	}
	
	@Test(expected = RouteNotFoundException.class)
	public void shoudReturnRouteNotFoudException() throws RouteNotFoundException {
		String Path = "/ParkingLot/car/update";

		Routes routes = new Routes();
		Action action = routes.getAction(Path);
		
	}
}
