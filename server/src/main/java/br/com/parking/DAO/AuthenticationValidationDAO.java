package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationValidationDAO extends DAO{
	
	public boolean validateToken(String token) {
		String tableName = "EmployeeAccessToken";
		boolean tokenExists = false;
		try {
			String query = "SELECT * FROM "+tableName+" WHERE at_code=? AND at_status=1 AND at_expires_at <= (NOW() - INTERVAL 1 DAY)";
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, token);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.isBeforeFirst())
				tokenExists = true;
			
			super.closeConnection(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return tokenExists;

	}
}
