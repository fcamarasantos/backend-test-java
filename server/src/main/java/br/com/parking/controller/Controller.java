package br.com.parking.controller;

import java.io.IOException;
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
import myExceptions.RouteNotFoundException;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String URI = req.getRequestURI();
		Action action = null;
		try {
			action = Routes.getAction(URI);
			action.execute(req, resp);
		} catch (RouteNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

}
