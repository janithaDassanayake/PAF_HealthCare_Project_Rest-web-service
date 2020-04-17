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
	


	public String addPayment(int appointment_id,int schedule_id ) {
		PaymentBean paymenntBean=new PaymentBean(); 
		paymenntBean.setAppointment_id(appointment_id);
		totalCal(schedule_id,paymenntBean);
		String output = "";
		try {

			Connection con = dbCon.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "insert into payment_tbl (appointment_id,paymentScheme_id,total_charge) values (?,?,?)";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);

	// binding values
	preparedStmt.setInt(1, paymenntBean.getAppointment_id());
			preparedStmt.setInt(2, paymenntBean.getPaymentScheme_id());
			preparedStmt.setDouble(3, paymenntBean.getTotal_charge());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String viewAllPayments() {
		String output = "";
		
		PaymentBean pBean = new PaymentBean();
		
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error While connection to database";}
			
			
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>ID</th>"
					+ "<th>Appoinment ID</th>"
					+ "<th>Payment Scheme ID</th>"
					+ "<th>Total Charge</th>"
					+ "</tr>";
			
			String query = "SELECT * FROM payment_tbl";

			java.sql.Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				pBean.setPayment_id(rs.getInt("payment_id"));
				pBean.setAppointment_id(rs.getInt("appointment_id"));
				pBean.setPaymentScheme_id(rs.getInt("paymentScheme_id"));
				pBean.setTotal_charge(rs.getDouble("total_charge"));
				
				output += "<tr><td>"+pBean.getPayment_id()+" </td>";
				output += "<td>"+pBean.getPayment_id()+"</td>";
				output += "<td>"+pBean.getPaymentScheme_id()+"</td>";
				output += "<td>"+pBean.getTotal_charge()+"</td>";
			}
			
		} catch (Exception e) {
			 output = "Error while reading the payments.";
			 System.err.println(e.getMessage()); 
		}
		
		
		return output;
	}
	
	
}
