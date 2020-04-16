package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class Schedule{
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
