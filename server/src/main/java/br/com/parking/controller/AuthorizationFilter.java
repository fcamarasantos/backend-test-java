package br.com.parking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.service.AuthenticationValidation;
import messages.Message;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		List<String> freeActions = new ArrayList<String>();
		freeActions.add("/company/create");
		
		String projectName = "/ParkingLot";
		String URI = request.getRequestURI();
		String path = URI.replace(projectName, "");
		
		if(freeActions.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = request.getParameter("token");
		
		AuthenticationValidation authent = new AuthenticationValidation();
		
		if(!authent.validateToken(token)) {
			Message msg = new Message("Not a valid Token", "Your token may have expired.");
			
			Gson gson = new Gson();
			String json = gson.toJson(msg);  
			
			response.setContentType("application/json");
			response.getWriter().print(json);
			return;
		}
		
		chain.doFilter(request, response);

	}

}
