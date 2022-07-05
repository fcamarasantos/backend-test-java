package br.com.parking.tests;

import org.junit.Test;

import br.com.parking.model.Company;
import br.com.parking.service.actions.company.VerifyCompany;
import org.junit.Assert;
import myExceptions.MissingDataException;

public class VerifyTest {
	
	@Test(expected = MissingDataException.class)
	public void shouldReturnMissingData() throws MissingDataException {
		Company comp = new Company();
		
		comp.setName("nome");
		comp.setCnpj("12345678");
		VerifyCompany ver = new VerifyCompany();
	
		ver.verifyCreate(comp);
	}
	
	@Test(expected = MissingDataException.class)
	public void shouldReturnMissingData2() throws MissingDataException {
		Company comp = new Company();
		
		VerifyCompany ver = new VerifyCompany();
	
		ver.verifyCreate(comp);
	}
	
	@Test(expected = MissingDataException.class)
	public void shouldReturnExeption() throws MissingDataException {
		Company comp = new Company();
		
		comp.setName("");
		comp.setCnpj("12345678");
		comp.setPhone("(11) 90234-9045");
		VerifyCompany ver = new VerifyCompany();
	
		ver.verifyCreate(comp);
	}
	
	@Test(expected = MissingDataException.class)
	public void shouldReturnExeptionMissing() throws MissingDataException {
		Company comp = new Company();
		
		comp.setName("");
		comp.setCnpj(null);
		comp.setPhone("(11) 90234-9045");
		VerifyCompany ver = new VerifyCompany();
	
		ver.verifyCreate(comp);
	}
	
	@Test
	public void shouldNotThrowException() throws MissingDataException {
		Company comp = new Company();
		
		comp.setName("nome da empresa");
		comp.setCnpj("1234567800000000000");
		comp.setPhone("(11) 90234-9045");
		VerifyCompany ver = new VerifyCompany();
	
		try {
			ver.verifyCreate(comp);
		} catch (Exception e) {
			throw e;
		}
		
		Assert.assertEquals(true, true);
	}
}
