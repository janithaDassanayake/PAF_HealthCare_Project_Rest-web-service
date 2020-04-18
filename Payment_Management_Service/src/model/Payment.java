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
	
	
	
}
