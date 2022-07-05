package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/parkinglot";
	private String user = "root";
	private String password = System.getenv("bd_password");
	
	protected Connection connect () {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally{
			return con;
		}
	}
	
	protected void closeConnection(Connection con) {
		try {
			con.close();	
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
