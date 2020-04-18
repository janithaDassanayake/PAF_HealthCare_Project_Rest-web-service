package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.PaymentSchemeBean;
import util.DBConnectionUtil;

public class PaymentScheme {
	
	DBConnectionUtil dbCon = new DBConnectionUtil(); // DB connection object for all methods
	
	/*
	 * view all the payment schemes
	 */
	public String viewAllPaymentSchemes() {
		
		String output = "";
		
		PaymentSchemeBean psBean = new PaymentSchemeBean();
		
		try {
			
			System.out.println("test");
			
			Connection con = dbCon.connect();
			
			if (con == null) {
				return "Error While connection to database";
			}
			
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Doctor ID</th>"
					+ "<th>Hospital ID</th>"
					+ "<th>Doctor's Charge</th>"
					+ "<th>Hospital's Charge</th>"
					+ "<th>Tax</th>"
					+ "</tr>"; // changed
			
			
			String query = "SELECT * FROM payment_schemes";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {

					psBean.setId(rs.getInt("id"));
					psBean.setDoc_id(rs.getInt("doc_id"));
					psBean.setHospital_id(rs.getInt("hospital_id"));
					psBean.setDoc_charge(rs.getDouble("doc_charge"));
					psBean.setHosp_charge(rs.getDouble("hosp_charge"));
					psBean.setTax(rs.getDouble("tax"));

					output += "<tr><td>"+psBean.getId()+" </td>";
					output += "<td>"+psBean.getDoc_id()+"</td>";
					output += "<td>"+psBean.getHospital_id()+"</td>";
					output += "<td>"+psBean.getDoc_charge()+"</td>";
					output += "<td>"+psBean.getHosp_charge()+"</td>";
					output += "<td>"+psBean.getTax()+"</td>";
			
			}
			
		} catch (Exception e) {
			
			 output = "Error while reading the payment schemes.";
			 System.err.println(e.getMessage()); 
			
		}
		
		return output;
		
		
	}
	

}
