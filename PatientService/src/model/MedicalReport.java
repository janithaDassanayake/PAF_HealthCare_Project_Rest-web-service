package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

import bean.MedicalReportBean;
import bean.PatientDetailsBean;
import util.DBConnection;

public class MedicalReport {
	DBConnection dbCon = new DBConnection();

	
public Boolean checkAppointmentID (String aID) throws SQLException {
		
	
		Connection con = dbCon.connect();
		
		
		String checkQuery = "SELECT p.aID FROM prescriptionpad p WHERE p.aID = ?";
		java.sql.PreparedStatement ps = con.prepareStatement(checkQuery);
		ps.setInt(1, Integer.parseInt(aID));
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next() == true) {
			return false;
		}	else {
			return true;
		}	
		
	}


public Boolean checkPatient(String patientID) throws SQLException {
	Connection con = dbCon.connect();
	
	String checkPatient = "SELECT p.patientID FROM patientdetails p WHERE p.patientID = ?";
	java.sql.PreparedStatement ps = con.prepareStatement(checkPatient);
	ps.setString(1, patientID);
	
	ResultSet rs = ps.executeQuery();
	
	if (rs.next() == true) {
		return true;
	}else {
		return false;
	}
	
}
	
	
	//Add Report Details --------------------------------------------------------
	
	public String AddDetailsToMedicatReport(String patientID, String aID, String diagnosis, String disabilities,
			String surgery, float bloodPressure, float pulseRate) {

		
		String output = "";

		try {

			Connection con = dbCon.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			
			if(checkAppointmentID(aID) == true) {
				
				if (checkPatient(patientID) == true) {
					// Get Current Date
					java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

					String insertQuery = "INSERT INTO `prescriptionpad`(`patientID`, `aID`, `date`, "
							+ "`diagnosis`, `disabilities`, `surgery`, `bloodPressure`, `pulseRate`) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

					java.sql.PreparedStatement pstmt = con.prepareStatement(insertQuery);

					pstmt.setString(1, patientID);
					pstmt.setInt(2, Integer.parseInt(aID));
					pstmt.setTimestamp(3, date);
					pstmt.setString(4, diagnosis);
					pstmt.setString(5, disabilities);
					pstmt.setString(6, surgery);
					pstmt.setFloat(7, bloodPressure);
					pstmt.setFloat(8, pulseRate);

					pstmt.execute();
					con.close();
					output = "Report inserted Sucessfully";
				}else
					output = "Patient dosen't Exists. Please Enter Registered Patient NIC number";				
				
			}
			else {
				output = "Report Already issued : ReporrtID = "+ aID + "| PatientID = "+ patientID;
			}
		

		} catch (Exception e) {
			output = "error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//----------------------------------------Delete Report 
	
	public String deleteMedicalReport(String aID) {
		String output = "";

		try {
			Connection con = dbCon.connect();
			if (con == null) {
				return "Error while connecting to the database for delete";
			}

			if (checkAppointmentID(aID) == false) {
				String deleteReportQuery = "DELETE FROM prescriptionpad WHERE aID = ?";

				// int saID = Integer.parseInt(aID);

				java.sql.PreparedStatement pstmt = con.prepareStatement(deleteReportQuery);

				pstmt.setString(1, aID);

				pstmt.execute();
				con.close();
				output = "Report Deleted : "+aID;
			}else {
				output = "Please Enter Valid Report ID";
			}
			
			

		} catch (Exception e) {
			output = "Error while deleting the report";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//-----------------------View Report---------------------------------------
	public String viewReport(String aid) {
		String output = "";
		
		PatientDetailsBean patient = new PatientDetailsBean();
		MedicalReportBean report = new MedicalReportBean();
		
		
		try {
			Connection con = dbCon.connect();

			if (con == null) {
				return "Error while connecting to the database for reading";
			}

			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Patient ID</th>"
					+ "<th>Appointment ID</th>"
					+ "<th>Report ID</th>"
					+ "<th>Date</th>"
					+ "<th>First Name</th>" 
					+ "<th>Last Name</th>" 
					+ "<th>Sex</th>"
					+ "<th>Age</th>"
					+ "<th>Contact Number</th>"
					+ "<th>Diagnosis</th>"
					+ "<th>Disabilities</th>"
					+ "<th>Surgery</th>"
					+ "<th>Blood Preasure</th>"
					+ "<th>Pulse Rate</th>" 
					+ "</tr>";

			
			
			String query = "SELECT p.patientID, a.aID, r.padID, r.date, p.firstName, p.lastName, p.sex, p.age, p.contactNumber,  "
					+ " r.diagnosis ,r.disabilities, r.surgery, r.bloodPressure, r.pulseRate FROM appointmentdetails a,"
					+ " patientdetails p, prescriptionpad r WHERE a.patientID = p.patientID AND a.aID = r.aID AND a.aID = ?";

			java.sql.PreparedStatement stat1 = con.prepareStatement(query);
		

			stat1.setInt(1, Integer.parseInt(aid));
			

			ResultSet rs1 = stat1.executeQuery();

			while (rs1.next()) {
				patient.setPatientID(rs1.getString("patientID"));
				report.setaID(rs1.getInt("aID"));
				report.setPadId(rs1.getInt("padID"));
				report.setDate(rs1.getDate("date"));
				patient.setFirstName(rs1.getString("firstName"));
				patient.setLastName(rs1.getString("lastName"));
				patient.setSex(rs1.getString("sex"));
				patient.setAge(rs1.getInt("age"));
				patient.setContactNumber(rs1.getInt("contactNumber"));
				report.setDiagnosis(rs1.getString("diagnosis"));
				report.setDisabilities(rs1.getString("disabilities"));
				report.setSurgery(rs1.getString("surgery"));
				report.setBloodPressure(rs1.getFloat("bloodPressure"));
				report.setPulseRate(rs1.getFloat("pulseRate"));
							
				
				output += "<tr><td>" + patient.getPatientID() + "</td>";
				output += "<td>" + report.getaID() + "</td>";
				output += "<td>" + report.getPadId() + "</td>";
				output += "<td>" + report.getDate() + "</td>";
				output += "<td>" + patient.getFirstName() + "</td>";
				output += "<td>" + patient.getLastName() + "</td>";
				output += "<td>" + patient.getSex() + "</td>";
				output += "<td>" + patient.getAge() + "</td>";
				output += "<td>" + patient.getContactNumber() + "</td>";				
				output += "<td>" + report.getDiagnosis() + "</td>";
				output += "<td>" + report.getDisabilities() + "</td>";
				output += "<td>" + report.getSurgery() + "</td>";
				output += "<td>" + report.getBloodPressure() + "</td>";
				output += "<td>" + report.getPulseRate() + "</td></tr>";
			}

		

			con.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Report Details";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	

}
