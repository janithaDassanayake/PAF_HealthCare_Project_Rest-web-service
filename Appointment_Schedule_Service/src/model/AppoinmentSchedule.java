package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBconnection;

public class AppoinmentSchedule {

	DBconnection dbObj = new DBconnection();
	
	
	//========================= View All Schedule ==========================
	
		public String viewAllSchedule() {

			String output = "";
			try {
				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Schedule Id</th>"
						+ "<th>Date</th>" 
						+ "<th>Start Time</th>" 
						+ "<th>End Time</th>"
						+ "<th>Doctor Id</th>"
						+ "<th>Hostpital Id</th>"
						+ "<th>Appointment Id</th></tr>";

				String query = "select * from appointment_scheduling";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String schedule_id = rs.getString("Schedule_id");
					String date = rs.getString("Date");
					String start_time = rs.getString("Start_Time");
					String end_time = rs.getString("End_Time");
					String d_id = rs.getString("D_id");
					String h_id = rs.getString("H_id");
					String app_id = rs.getString("App_id");

					// Add into the html table
					output += "<tr><td>" + schedule_id + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + start_time + "</td>";
					output += "<td>" + end_time + "</td>";
					output += "<td>" + d_id + "</td>";
					output += "<td>" + h_id + "</td>";
					output += "<td>" + app_id + "</td>";

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
