package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.AppointmentBean;
import util.DBConnection;

public class Appointment {
	
	Schedule sch = new Schedule();
	DBConnection dbObj = new DBConnection();
		
	//method to insert data
	public String insertDetails(String PatientID, String DueDate, int ScheduleId) {
		String output = "";
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into appointment_doctor(`appointmentId`,`patientId`,`dueDate`,`scheduleId`,`status`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PatientID);
			preparedStmt.setString(3, DueDate);
			preparedStmt.setInt(4, ScheduleId);
			preparedStmt.setBoolean(5, false);
									
			// execute the statement 			
			preparedStmt.execute();
			output = "Inserted successfully";
			
		} catch (Exception e) {
			output = "Error while inserting.Can't add a child row";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//method to read database
	public String readDetails() {
		String output = "";
		AppointmentBean docbean = new AppointmentBean();
		
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Appointment ID</th>" + "<th>Patient ID</th>" 
					 + "<th>Date</th>" + "<th>Schedule Id</th>" + "<th>Status</th></tr>";
			String query = "select * from appointment_doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String AppointmentID = Integer.toString(rs.getInt("appointmentId"));
				String PatientID = rs.getString("patientId");
				String DueDate =rs.getString("dueDate");
				String ScheduleId = Integer.toString(rs.getInt("scheduleId"));
				String Status = rs.getString("status");
				
				// Add into the html table
				output += "<tr><td>" + AppointmentID + "</td>";
				output += "<td>" + PatientID + "</td>";
				output += "<td>" + DueDate + "</td>";
				output += "<td>" + ScheduleId + "</td>";
				output += "<td>" + Status + "</td>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//method to update details
	public String updateDetails(String AppointmentID, String PatientID,  String DueDate, String ScheduleId)
	 {
	 String output = "";
	 try
	 {
	 Connection con = dbObj.connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE appointment_doctor SET patientId=?,dueDate=?,ScheduleId=?WHERE appointmentId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, PatientID);
	 preparedStmt.setString(2, DueDate);
	 preparedStmt.setString(3, ScheduleId); 
	 preparedStmt.setInt(4, Integer.parseInt(AppointmentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the details.Can't update a child row";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	//method to delete details
	public String deleteDetails(String AppointmentID) {
		String output = "";
		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment_doctor where AppointmentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(AppointmentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//search appointments by ID	
	
	// view list of appointments
	public List<AppointmentBean> viewAppointments() {		
		return	viewAppointments(null);
	}
	
	// show appointments by ID
	public AppointmentBean ShowAppointmentById(String id) {
	List<AppointmentBean> list =viewAppointments(id);
		if(!list.isEmpty()) {
			return	list.get(0);
		}
		return null;
	}
			
	//view method
	public List<AppointmentBean> viewAppointments(String id) {
		List <AppointmentBean> AppList = new ArrayList<>();
		try 
		{
			Connection con = dbObj.connect();
			if (con == null) {
				System.out.println("Error While reading from database");
				return AppList;
			}

			String query;
			
			if(id==null) {
			query = "select * from appointment_doctor";
			}
			else {
				query = "select * from appointment_doctor where patientId="+id;	
			}
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);

			while (results.next()) {
				AppointmentBean type = new AppointmentBean(
					results.getInt("appointmentid"),
					results.getString("patientid"),
					results.getString("dueDate"),
					results.getInt("scheduleid"),
					results.getBoolean("status")
				);
				AppList.add(type);
			}
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error While Reading");
			System.err.println(e.getMessage());
		}		
		return AppList;
	}
	
	public List<AppointmentBean> View_Appointments_By_given_ID(String pid) {
		List<AppointmentBean> ScheduleBeanlist = new ArrayList<>();
		for (AppointmentBean sch : viewAppointments()) {
			if (pid.equals(sch.getPatientid())) {				
				ScheduleBeanlist.add(sch);
			}
		}
		return ScheduleBeanlist;
	}
}
