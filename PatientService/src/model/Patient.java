package model;

import java.util.Calendar;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

import bean.PatientDetailsBean;
import util.DBConnection;

public class Patient {

	MedicalReport rPatient = new MedicalReport();
	DBConnection dbCon = new DBConnection();

	// -------------------- View Patient Details -------------------------------

	public String viewPatientDetails() {
		String output = "";

		PatientDetailsBean patientDetails = new PatientDetailsBean();
		try {
			Connection con = dbCon.connect();

			if (con == null) {
				return "Error while connecting to the database for reading";
			}

			output = "<table border=\"1\"><tr><th>No.</th><th>Patient ID</th>"
					+ "<th>First Name</th><th>Middle Name</th>" + "<th>Last Name</th><th>Email</th>"
					+ "<th>Sex</th><th>DOB</th><th>Age</th>" + "<th>Height</th><th>Weight</th>"
					+ "<th>Contact Number</th><th>Status</th>" + "<th>Address1</th><th>Address2</th>"
					+ "<th>City</th><th>State</th>" + "<th>Postal Code</th>" + "</tr>";

			String retriveQuery = "SELECT * FROM patientdetails";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(retriveQuery);

			while (resultSet.next()) {
				patientDetails.setId(resultSet.getInt("id"));
				patientDetails.setPatientID(resultSet.getString("patientID"));
				patientDetails.setFirstName(resultSet.getString("firstName"));
				patientDetails.setMiddleName(resultSet.getString("middleName"));
				patientDetails.setLastName(resultSet.getString("lastName"));
				patientDetails.setEmail(resultSet.getString("email"));
				patientDetails.setSex(resultSet.getString("sex"));
				patientDetails.setDob(resultSet.getDate("dob"));
				patientDetails.setAge(resultSet.getInt("age"));
				patientDetails.setWeight(resultSet.getFloat("weight"));
				patientDetails.setHeight(resultSet.getFloat("height"));
				patientDetails.setContactNumber(resultSet.getInt("contactNumber"));
				patientDetails.setMaritalStatus(resultSet.getString("maritalStatus"));
				patientDetails.setStreetAddL1(resultSet.getString("streetAddL1"));

				patientDetails.setStreetAddL2(resultSet.getString("streetAddL2"));
				patientDetails.setCity(resultSet.getString("city"));
				patientDetails.setState(resultSet.getString("state"));
				patientDetails.setPostalCode(resultSet.getInt("postalCode"));

				// ---------------------------------------Add
				// details-------------------------------------

				output += "<tr><td>" + patientDetails.getId() + "</td>";
				output += "<td>" + patientDetails.getPatientID() + "</td>";
				output += "<td>" + patientDetails.getFirstName() + "</td>";
				output += "<td>" + patientDetails.getMiddleName() + "</td>";
				output += "<td>" + patientDetails.getLastName() + "</td>";
				output += "<td>" + patientDetails.getEmail() + "</td>";
				output += "<td>" + patientDetails.getSex() + "</td>";
				output += "<td>" + patientDetails.getDob() + "</td>";
				output += "<td>" + patientDetails.getAge() + "</td>";
				output += "<td>" + patientDetails.getWeight() + "</td>";
				output += "<td>" + patientDetails.getHeight() + "</td>";
				output += "<td>" + patientDetails.getContactNumber() + "</td>";
				output += "<td>" + patientDetails.getMaritalStatus() + "</td>";
				output += "<td>" + patientDetails.getStreetAddL1() + "</td>";
				output += "<td>" + patientDetails.getStreetAddL2() + "</td>";
				output += "<td>" + patientDetails.getCity() + "</td>";
				output += "<td>" + patientDetails.getState() + "</td>";
				output += "<td>" + patientDetails.getPostalCode() + "</td></tr>";

			}

			con.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Patient Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// -------------------------------------Add Patient
	// --------------------------------------------------

	public String addPatient(String patientID, String firstName, String middleName, String lastName, String email,
			String sex, Date dob, float weight, float height, int contactNumber, String maritalStatus,
			String streetAddL1, String streetAddL2, String city, String state, int postalCode, String password,
			String emergencyCNanme, String emergencyCrelationship, int emergencynumber, boolean takeMedication,
			String currentMedication) {

		String output = "";

		try {

			Connection con = dbCon.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			if (rPatient.checkPatient(patientID) == false) {
				// Calculate Age
				Calendar today = Calendar.getInstance();
				Calendar birthDate = Calendar.getInstance();

				int age = 0;

				birthDate.setTime(dob);
				if (birthDate.after(today)) {
					throw new IllegalArgumentException("Can't be born in the future");
				}

				age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

				// If birth date is greater than todays date (after 2 days adjustment of leap
				// year) then decrement age one year
				if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
						|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
					age--;

					// If birth date and todays date are of same month and birth day of month is
					// greater than todays day of month then decrement age
				} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
						&& (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
					age--;
				}

				String insertQuery = "INSERT INTO `patientdetails` (`patientID`, `firstName`, `middleName`, `lastName`, "
						+ "`email`, `sex`, `dob`, `height`, `weight`, `contactNumber`, `maritalStatus`, `streetAddL1`, "
						+ "`streetAddL2`, `city`, `state`, `postalCode`, `password`, `emergencyCNanme`, `emergencyCrelationship`, "
						+ "`emergencynumber`, `takeMedication`, `currentMedication`, `age`) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				java.sql.PreparedStatement prestmt = con.prepareStatement(insertQuery);

				// insert Values
				prestmt.setString(1, patientID);
				prestmt.setString(2, firstName);
				prestmt.setString(3, middleName);
				prestmt.setString(4, lastName);
				prestmt.setString(5, email);
				prestmt.setString(6, sex);
				prestmt.setDate(7, dob);
				prestmt.setFloat(8, weight);
				prestmt.setFloat(9, height);
				prestmt.setInt(10, contactNumber);
				prestmt.setString(11, maritalStatus);
				prestmt.setString(12, streetAddL1);
				prestmt.setString(13, streetAddL2);
				prestmt.setString(14, city);
				prestmt.setString(15, state);
				prestmt.setInt(16, postalCode);
				prestmt.setString(17, password);
				prestmt.setString(18, emergencyCNanme);
				prestmt.setString(19, emergencyCrelationship);
				prestmt.setInt(20, emergencynumber);
				prestmt.setBoolean(21, takeMedication);
				prestmt.setString(22, currentMedication);
				prestmt.setInt(23, age);

				prestmt.execute();
				con.close();
				output = "insert Sucessfully";
			}else {
				output = "Patient Already Registered ! Please Use NIC number for Make Appointment !";
			}

			

		} catch (Exception e) {
			output = "error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// --------------------------------- Update Profile
	// -------------------------------------
	

	public String updatePatient(String patientID, String firstName, String middleName, String lastName, String email,
			String sex, Date dob, float weight, float height, int contactNumber, String maritalStatus,
			String streetAddL1, String streetAddL2, String city, String state, int postalCode) {

		String output = "";

		try {
			Connection con = dbCon.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			if (rPatient.checkPatient(patientID) == true) {
				// Calculate Age
				Calendar today = Calendar.getInstance();
				Calendar birthDate = Calendar.getInstance();

				int age = 0;

				birthDate.setTime(dob);
				if (birthDate.after(today)) {
					throw new IllegalArgumentException("Can't be born in the future");
				}

				age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

				// If birth date is greater than todays date (after 2 days adjustment of leap
				// year) then decrement age one year
				if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
						|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
					age--;

					// If birth date and todays date are of same month and birth day of month is
					// greater than todays day of month then decrement age
				} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
						&& (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
					age--;
				}

				String updateQuery = "UPDATE `patientdetails` SET " + "`firstName`=?,`middleName`=?,`lastName`=?,"
						+ "`email`=?,`sex`=?,`dob`=?,`height`=?," + "`weight`=?,`contactNumber`=?,`maritalStatus`=?,"
						+ "`streetAddL1`=?,`streetAddL2`=?,`city`=?,`state`=?,"
						+ "`postalCode`=?, `age`=? WHERE `patientID`=?";

				java.sql.PreparedStatement prestmt = con.prepareStatement(updateQuery);

				prestmt.setString(1, firstName);
				prestmt.setString(2, middleName);
				prestmt.setString(3, lastName);
				prestmt.setString(4, email);
				prestmt.setString(5, sex);
				prestmt.setDate(6, dob);
				prestmt.setFloat(7, height);
				prestmt.setFloat(8, weight);
				prestmt.setInt(9, contactNumber);
				prestmt.setString(10, maritalStatus);
				prestmt.setString(11, streetAddL1);
				prestmt.setString(12, streetAddL2);
				prestmt.setString(13, city);
				prestmt.setString(14, state);
				prestmt.setInt(15, postalCode);
				prestmt.setInt(16, age);
				prestmt.setString(17, patientID);

				prestmt.execute();
				con.close();
				output = "Updated successfully [ PatientID : " + patientID + " ]";
			}else {
				output = "Update Failed : Please Enter Registered Patient ID";
			}
			
			

		} catch (Exception e) {
			output = "Error while updating the patient details " + patientID;
			System.err.println(e.getMessage());
		}

		return output;
	}

	

	// ---------------------------------Delete Patient Account ------------------

	public String deletePatient(String patientID) {
		String output = "";

		try {
			Connection con = dbCon.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			if (rPatient.checkPatient(patientID) == true) {
				String deleteQurey = "DELETE FROM `patientdetails` WHERE patientID = ?";
				java.sql.PreparedStatement prestmt = con.prepareStatement(deleteQurey);

				prestmt.setString(1, patientID);

				prestmt.execute();
				con.close();
				output = "Deleted successfully : PatientID ["+patientID+"]";
			}else {
				output = "WARNING! - Invalid ID - Please Enter Valid Patient ID";
			}
			
			

		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//View Temporary Patients
	// SELECT DISTINCT(p.patientID) FROM patientdetails p, appointmentdetails a WHERE p.patientID != A.patientID
	public String showTempPatients() {
		String output = "";
		
		PatientDetailsBean patientDetails = new PatientDetailsBean();
		Connection con = dbCon.connect();
		
		if (con== null) {
			return "Error while connecting to the database for updating.";
		}
		
		output += "<table border=\\\"1\\\"><tr><th>No Appointments - Patient List</th>";
		
		String showPatient = "SELECT DISTINCT(p.patientID) FROM patientdetails p, appointmentdetails a WHERE p.patientID != A.patientID";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(showPatient);
			
			while(rs.next()) {
				patientDetails.setPatientID(rs.getString("patientID"));
				
				output += "<tr><td>" + patientDetails.getPatientID() + "</td></tr>";
			}
			
			output += "</table";
			con.close();
			
			
			
		} catch (SQLException e) {
			output = "Error while reading the Patient Details";
			System.err.println(e.getMessage());
		}
		
		
		
		return output;
	}
	
	
	
	// ---------------------------------View Emergency data
	// ----------------------------------------

	public String viewEmergencyDetails() {
		String output = "";

		PatientDetailsBean patientDetails = new PatientDetailsBean();
		try {
			Connection con = dbCon.connect();

			if (con == null) {
				return "Error while connecting to the database for reading";
			}

			output = "<table border=\"1\"><tr>Emergency Details</tr><tr><th>Patient ID</th>"
					+ "<th>Emergency Contact Name</th><th>Relationship</th>" + "<th>Contact Number</th>" + "</tr>";

			String emerRetriveQuery = "SELECT p.patientID, p.emergencyCNanme, p.emergencyCrelationship, p.emergencynumber FROM patientdetails p";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(emerRetriveQuery);

			while (rs.next()) {
				patientDetails.setPatientID(rs.getString("patientID"));
				patientDetails.setEmergencyCNanme(rs.getString("emergencyCNanme"));
				patientDetails.setEmergencyCrelationship(rs.getString("emergencyCrelationship"));
				patientDetails.setEmergencynumber(rs.getInt("emergencynumber"));

				// ---------------------------------------Add
				// details-------------------------------------

				output += "<tr><td>" + patientDetails.getPatientID() + "</td>";
				output += "<td>" + patientDetails.getEmergencyCNanme() + "</td>";
				output += "<td>" + patientDetails.getEmergencyCrelationship() + "</td>";

				output += "<td>" + patientDetails.getEmergencynumber() + "</td></tr>";

			}

			con.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the Patient Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
