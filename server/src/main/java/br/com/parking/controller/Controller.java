package br.com.parking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.parking.service.actions.Action;
import br.com.parking.service.actions.Company;
import br.com.parking.service.actions.TestAction;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Action action = new TestAction();
		Map<String, Action> vehicleRoutes = new HashMap<>();
		vehicleRoutes.put("/style", action);
		vehicleRoutes.put("/color", action);
		
		String request = req.getRequestURI();
		List reqPath = new ArrayList();
		
		String str = "geekss@for@geekss";
        String[] arrOfStr = str.split("@");
        
        String[] path = request.split("/");
        
        for (String string : path) {
			System.out.println(string);
		}
        
        Action action2 = (Action) new Company();
		
        System.out.println(action2.getClass().equals(Company.class));
		
		Map<String, Map> routes = new HashMap<>();
		
		
		Map<String, VehicleController> routes2 = new HashMap<String, VehicleController>();
		VehicleController vehicleController = new VehicleController();
		routes2.put("/vehicle", vehicleController);
		
		VehicleController a = (VehicleController) routes2.get("/vehicle");
	
		
		
		String respon = a.execute(req, resp);
		
		
		
//		
//		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/json");
//		rd.forward(req, resp);
		try {
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
