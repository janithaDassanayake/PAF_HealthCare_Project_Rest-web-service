package model.DoctorManagementMaven.DoctorManagementMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Doctor {

	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}	
		
		public String insertDoctor(String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}

				// create a prepared statement
				String query = " insert into doctors (`DoctorID`,`DoctorName`,`NIC`,`Address`,`MobileNo`, `Email`,`Specialization`,`HospitalName`,`DepartmentName`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, docName);
				preparedStmt.setString(3, nic);
				preparedStmt.setString(4, address);
				preparedStmt.setInt(5, Integer.parseInt(mobNo));
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, spec);
				preparedStmt.setString(8, hosp);
				preparedStmt.setString(9, dept);

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
}
