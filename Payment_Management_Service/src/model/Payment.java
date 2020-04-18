package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBConnectionUtil;

import beans.PaymentBean;


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
	System.out.println("test"+schedule_id);
		try {
			Connection con = dbCon.connect();
			if (con == null) {
				System.out.println("Error While reading from database");}
				String query = "select p.id,((p.doc_charge+p.hosp_charge)*(tax+100)/100) as total FROM schedule s left outer join payment_schemes p on s.d_id=p.doc_id and s.h_id=p.hospital_id where schedule_id="+schedule_id;
				java.sql.Statement statement = con.createStatement();
				
				ResultSet rs = statement.executeQuery(query);
				
				while(rs.next()) {

					paymentBean.setTotal_charge(rs.getDouble("total"));
					paymentBean.setPaymentScheme_id(rs.getInt("id"));	
				}
				
				con.close();
				System.out.println("id"+paymentBean.getPaymentScheme_id()+" total "+paymentBean.getTotal_charge());
			
		
	
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
	
	}

	
public String insertPayment(PaymentBean pBean , int schedule_id ) {
		
		String output = "";
		totalCal(schedule_id, pBean);
	
		try {
			
			Connection con = dbCon.connect();
			
			if(con == null) {return "Error while connecting to the database for inserting.";}
			
			System.out.println("test query" + pBean.getAppointment_id());
			
			String query = "insert into payment_tbl (appointment_id,paymentScheme_id,total_charge) values (?,?,?)"; // changed
			
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			
			
			//value binding
			preparedStatement.setInt(1, pBean.getAppointment_id());
			preparedStatement.setInt(2, pBean.getPaymentScheme_id());
			preparedStatement.setDouble(3, pBean.getTotal_charge());
			
			System.out.println(pBean.getAppointment_id()+pBean.getPayment_id()+pBean.getTotal_charge());
			
			preparedStatement.execute();
			con.close();
			
			output = "Inserted successfully"; 
		} catch (Exception e) {
		
			 output = "Error while inserting the Scheme.";
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
			con.close();
		} catch (Exception e) {
			 output = "Error while reading the payments.";
			 System.err.println(e.getMessage()); 
		}
		
		
		return output;
	}
	
			
	
}
