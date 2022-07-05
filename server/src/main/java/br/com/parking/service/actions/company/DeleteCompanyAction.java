package br.com.parking.service.actions.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.service.actions.Action;
import messages.Message;

public class DeleteCompanyAction implements Action{
	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Message msg = new Message();
		
		msg.setTitle("Ok");
		
		return msg;
	}
}
