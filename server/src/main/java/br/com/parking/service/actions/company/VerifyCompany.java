package br.com.parking.service.actions.company;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.model.Company;
import myExceptions.MissingDataException;
import myExceptions.UniqueDataAlreadyExists;

public class VerifyCompany {
	public void verifyCreate (Company comp) throws MissingDataException, UniqueDataAlreadyExists {
		CompanyDAO compDAO = new CompanyDAO();
		
		if(comp.getName() == null || comp.getCnpj() == null || comp.getPhone() == null) {
			throw new MissingDataException("Some company Values are null");
		}
		
		if(comp.getName().trim().length() < 3 || comp.getPhone().trim().length() < 10 || comp.getCnpj().length() < 14) {
			throw new MissingDataException("Must have phone >= 10, name >= 3 and cnpj >= 15");
		} 
		
		if(compDAO.CNPJAlreadyRegistered(comp)) {
			throw new UniqueDataAlreadyExists("CNPJ alreday stored");
		}
		return;
		
	}
}
