package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.*;

public class OnlineAppointment {
		
	//A common method to connect to the DB  
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/healthcaredb?useTimezone=true&serverTimezone=UTC",
					"root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	//method to insert data
	public String insertDetails(int PatientID, String DueDate, int ScheduleId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into appointment_doctor(`appointmentId`,`patientId`,`dueDate`,`scheduleId`,`status`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, PatientID);
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
		try {
			Connection con = connect();
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
				String PatientID = Integer.toString(rs.getInt("patientId"));
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
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE appointment_doctor SET patientId=?,dueDate=?,ScheduleId=?WHERE appointmentId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PatientID));
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
	 output = "Error while updating the details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	//method to delete details
	public String deleteDetails(String AppointmentID) {
		String output = "";
		try {
			Connection con = connect();
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
	
	
	// View Doctor Schedule
	public String viewAllSchedule() {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Schedule Id</th>"
					+ "<th>Appointment Specification</th>"
					+ "<th>Doctor</th>"
					+ "<th>Hospital</th>"
					+ "<th>Date</th>" 
					+ "<th>Start Time</th>" 
					+ "<th>End Time</th></tr>";

			String query = "select s.Schedule_id,t.Appointment_Type,d.dname,d.hname,s.Date,s.Start_Time,s.End_Time from doctor d join appointment_scheduling s on s.D_id=d.did join appointment_type t on t.appointment_Id=s.App_id order by t.Appointment_Type";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String schedule_id = rs.getString("Schedule_id");
				String app_type = rs.getString("Appointment_Type");
				String d_name = rs.getString("dname");
				String h_name = rs.getString("hname");
				String date = rs.getString("Date");
				String start_time = rs.getString("Start_Time");
				String end_time = rs.getString("End_Time");
				
				
				// Add into the html table
				output += "<tr><td>" + schedule_id + "</td>";
				output += "<td>" + app_type + "</td>";
				output += "<td>" + d_name + "</td>";
				output += "<td>" + h_name + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + start_time + "</td>";
				output += "<td>" + end_time + "</td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</tr></table>";

		} catch (Exception e) {
			output = "Error while reading the Doctors Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
