package br.com.parking.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.model.Company;

public class VehicleController {

	public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		req.setAttribute("a", "vehicleController reponding");
		
		Company comp = new Company();
		comp.setName("nome");
		comp.setPhone("(11) 9090954324");
		
		Gson gson = new Gson();
		String json = gson.toJson(comp);  
		
		req.setAttribute("json", json);
		

		resp.setContentType("application/json");
		resp.getWriter().print(json);
		
		
		return "vehicleController reponding";
	}

}
