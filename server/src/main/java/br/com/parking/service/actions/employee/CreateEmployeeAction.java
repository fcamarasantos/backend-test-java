package br.com.parking.service.actions.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import messages.Message;

public class CreateEmployeeAction implements Action {

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Company comp = new Company();
		
		comp.setCnpj(request.getParameter("cnpj"));
		comp.setName(request.getParameter("name"));
		comp.setPhone(request.getParameter("phone"));
		
		CompanyDAO compDAO = new CompanyDAO();
		
		Message msg = new Message();
		
		try {
			VerifyEmployee verif = new VerifyEmployee();
			verif.verifyCreate(comp);
		} catch (Exception e) {
			msg.setTitle("An error Ocurred");
			msg.setMessage(e.getMessage());
			
			return msg;
		}
		
		compDAO.insert(comp);
		
		msg.setTitle("Ok");
		
		return msg;
	}

	
}
