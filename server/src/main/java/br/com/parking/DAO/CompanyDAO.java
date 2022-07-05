package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.parking.model.Company;

public class CompanyDAO extends DAO{
	private static String tableName = "company";
	
	public void insert(Company comp) {
		try {
			String query = "INSERT INTO "+tableName+" (comp_name, comp_cnpj, comp_phone) VALUES (?,?,?)";
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, comp.getName());
			pst.setString(2, comp.getCnpj());
			pst.setString(3, comp.getPhone());
			
			pst.executeUpdate();
			
			super.closeConnection(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
