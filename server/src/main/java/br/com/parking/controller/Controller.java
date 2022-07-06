package br.com.parking.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.RouteNotFoundException;

//@WebServlet("/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String valor = req.getHeader("Accept");
		System.out.println("header --- > " + req.getHeader("Accept"));
		Gson gson = new Gson();
		
		 if(valor.endsWith("json")) {
			InputStream is =  req.getInputStream();
			Scanner s = new Scanner(is);
			String body = "";
			
			while(s.hasNextLine()) {
				body += s.nextLine();
			}
			
			try {
				Company compTest = new Company();
				compTest = gson.fromJson(body, Company.class);
				System.out.println(compTest);
				
				req.setAttribute("modelObject", compTest);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		String URI = req.getRequestURI();
		Action action = null;
		
		try {
			action = Routes.getAction(URI);
			Message msg = action.execute(req, resp);
			
			String json = gson.toJson(msg);  
			
			resp.setContentType("application/json");
			resp.getWriter().print(json);
			return;
			
		} catch (RouteNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

}
