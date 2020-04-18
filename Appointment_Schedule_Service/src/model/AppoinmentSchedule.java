package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import beans.ScheduleBean;
import beans.AppoinmentTypeBean;
import util.DBconnection;

public class AppoinmentSchedule {

	DBconnection dbObj = new DBconnection();

	// ========================= View All Schedule ==========================

	public String viewAllSchedule() {

		String output = "";

		ScheduleBean appScheduling = new ScheduleBean();
		AppoinmentTypeBean TypeRead = new AppoinmentTypeBean();

		try {
			Connection con = dbObj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Schedule Id</th>" + "<th>appoinment Type</th>"
					+ "<th>Doctor Name</th>" + "<th>Hospital Name</th>" + "<th>Day</th>" + "<th>Start Time</th>"
					+ "<th>End Time</th>" + "<th>Doctor Id</th>" + "<th>Hostpital Id</th>"
					+ "<th>Appointment Id</th></tr>";

			// String query = "select
			// s.Schedule_id,a.Appointment_Name,s.Date,s.Start_Time,s.End_Time,s.D_id,s.H_id,s.App_id
			// from appointment_scheduling s inner join appointment_type a on
			// s.App_id=a.appointment_Id ";

			String query = "select s.Schedule_id,a.Appointment_Type,d.DoctorName,h.Hospital_Name,s.Date,s.Start_Time,s.End_Time,s.D_id,s.H_id,s.App_id "
					+ "from appointment_scheduling s ,appointment_type a , doctor d, hostpital h "
					+ "WHERE s.App_id=a.appointment_Id AND s.D_id=d.Doctor_Id AND s.H_id=h.hospital_Id";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String docname = rs.getString("DoctorName");
				String Hosname = rs.getString("Hospital_Name");
				appScheduling.setSchedule_id(rs.getInt("Schedule_id"));

				TypeRead.setAppointment_Type(rs.getString("Appointment_Type"));

				appScheduling.setDate(rs.getString("Date"));
				appScheduling.setStart_Time(rs.getTime("Start_Time"));
				appScheduling.setEnd_Time(rs.getTime("End_Time"));
				appScheduling.setD_id(rs.getInt("D_id"));
				appScheduling.setH_id(rs.getInt("H_id"));
				appScheduling.setApp_id(rs.getInt("App_id"));

				// Add into the html table
				output += "<tr><td>" + appScheduling.getSchedule_id() + "</td>";
				output += "<td>" + TypeRead.getAppointment_Type() + "</td>";
				output += "<td>" + docname + "</td>";
				output += "<td>" + Hosname + "</td>";
				output += "<td>" + appScheduling.getDate() + "</td>";
				output += "<td>" + appScheduling.getStart_Time() + "</td>";
				output += "<td>" + appScheduling.getEnd_Time() + "</td>";
				output += "<td>" + appScheduling.getD_id() + "</td>";
				output += "<td>" + appScheduling.getH_id() + "</td>";
				output += "<td>" + appScheduling.getApp_id() + "</td>";

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

	// ====================== Add In To Appointment Scheduling
	

	public String add_Appoinment_Schedule(String date, Time start_time, Time end_time, int d_id, int h_id, int app_id) {

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
			preparedStmt.setString(1, date);
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

	// ============================= Update Appointment Scheduling
	
	public String updateAppointmentSchedule(int schedule_id, String date, Time startTime, Time endTime, int d_id,
			int h_id, int app_id) {

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

			preparedStmt.setString(1, date);
			preparedStmt.setTime(2, startTime);
			preparedStmt.setTime(3, endTime);
			preparedStmt.setInt(4, d_id);
			preparedStmt.setInt(5, h_id);
			preparedStmt.setInt(6, app_id);
			preparedStmt.setInt(7, schedule_id);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully Schedule [ ID : " + schedule_id + " ]";
		} catch (Exception e) {
			output = "Error while updating the Schedule " + schedule_id;
			System.err.println(e.getMessage());
		}
		return output;
	}

	// ============================= Delete Appointment Schedule
	

	public String deleteAppointmentSchedule(ScheduleBean appSched) {
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
			preparedStmt.setInt(1, appSched.getSchedule_id());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully [ Schedule ID : " + appSched.getSchedule_id() + " ]";

		} catch (Exception e) {

			output = "Error while deleting the Schedule ID" + appSched.getSchedule_id();
			System.err.println(e.getMessage());
		}

		return output;
	}

}
