package br.com.parking.service;

import br.com.parking.DAO.AuthenticationValidationDAO;

public class AuthenticationValidation {
	public Boolean validateToken(String token) {
		
		AuthenticationValidationDAO authenticationDAO = new AuthenticationValidationDAO();
		boolean tokenExists = authenticationDAO.validateToken(token);
		
		return tokenExists;
		
	}
}
