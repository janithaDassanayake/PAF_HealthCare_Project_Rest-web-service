package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.PaymentBean;
import util.DBConnectionUtil;

/**
 * 
 * @author Ishanka
 * all the operations are implemented here
 */
public class Payment {
	
	DBConnectionUtil dbCon = new DBConnectionUtil();
	
	public ArrayList<Payment> viewPaymentsOfPatient(int pid){
		
		ArrayList <Payment> paymentList = new ArrayList<>();
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {System.out.println("Error While reading from database");}
			
			String query = "SELECT * FROM payments_tbl WHERE pa";
			
		}catch(Exception e) {
			
		}
		
		
		
		return paymentList; 
		
	}  
	
	public void totalCal(int schedule_id,PaymentBean paymentBean ) {
	
		try {
			Connection con = dbCon.connect();
			if (con == null) {
				System.out.println("Error While reading from database");}
				String query = "select p.id,((p.doc_charge+p.hosp_charge)*(tax+100)/100) as total FROM schedule s left outer join payment_schemes p on s.d_id=p.doc_id and s.h_id=p.hospital_id where schedule_id='1'";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			// iterate through the rows in the result set
		
	
			paymentBean.setTotal_charge(rs.getDouble("total"));
			paymentBean.setPaymentScheme_id(rs.getInt("id"));				
			
			con.close();
	
	
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
	
	}
		
	
	
	
		
	
}
