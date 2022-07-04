package br.com.parking.service.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messages.Message;

public interface Action {
	public Message execute(HttpServletRequest request, HttpServletResponse response);
}
