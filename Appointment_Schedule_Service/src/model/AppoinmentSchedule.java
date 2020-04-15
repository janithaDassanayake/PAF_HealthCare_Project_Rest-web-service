package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

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
		
	
		
		
		//====================== Add In To Appointment Scheduling ========================
		
		public String makeAppoinment(Date date, Time start_time, Time end_time, int d_id, int h_id, int app_id) {

			String output = "";
			try {

				Connection con = dbObj.connect();

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " INSERT INTO appointment_scheduling (Date, Start_Time, End_Time, D_id, H_id, App_id) VALUES (?, ?, ?, ?, ?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setDate(1, date);
				preparedStmt.setTime(2, start_time);
				preparedStmt.setTime(3, end_time);
				preparedStmt.setInt(4, d_id);
				preparedStmt.setInt(5, h_id);
				preparedStmt.setInt(6, app_id);

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
		
		
		
		
		
		//============================= Update Appointment Scheduling ==============================
		
		public String updateAppointmentType(int schedule_id , Date date, Time startTime, Time endTime, int d_id, int h_id, int app_id) {

			String output = "";

			try {
				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE appointment_scheduling SET Date =?,Start_Time =?,End_Time =?,D_id =?,H_id =?,App_id =? WHERE Schedule_id =?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values

				preparedStmt.setDate(1, date);
				preparedStmt.setTime(2, startTime);
				preparedStmt.setTime(3, endTime);
				preparedStmt.setInt(4, d_id);
				preparedStmt.setInt(5, h_id);
				preparedStmt.setInt(6, app_id);
				preparedStmt.setInt(7, schedule_id);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully [ ID : "+schedule_id+" ]";
			} catch (Exception e) {
				output = "Error while updating the Doctor " + schedule_id;
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		//============================= Delete Appointment Schedule ==============================	
		
		public String deleteAppointmentSchedule(String s_id) {
			String output = "";
			try {

				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "DELETE FROM appointment_scheduling WHERE Schedule_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, s_id);

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully [ Appointment Id : "+s_id +" ]";

			} catch (Exception e) {

				output = "Error while deleting the Doctor" + s_id;
				System.err.println(e.getMessage());
			}

			return output;
		}

}