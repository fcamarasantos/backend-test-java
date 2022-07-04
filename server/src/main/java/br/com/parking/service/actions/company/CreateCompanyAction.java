package br.com.parking.service.actions.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import messages.Message;

public class CreateCompanyAction implements Action {

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Company comp = new Company();
		
		comp.setCnpj(request.getParameter("cnpj"));
		comp.setName(request.getParameter("name"));
		comp.setPhone(request.getParameter("phone"));
		
		CompanyDAO compDAO = new CompanyDAO();
		compDAO.insert(comp);
		
		Message msg = new Message("OK", "tudo certo");
		
		return msg;
	}

	
}
