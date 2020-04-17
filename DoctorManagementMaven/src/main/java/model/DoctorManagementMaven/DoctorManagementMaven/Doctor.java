package model.DoctorManagementMaven.DoctorManagementMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.DoctorManagementMaven.DoctorManagementMaven.DoctorBean;

public class Doctor {

		//=========================DB CONNECTION=========================================================
	
		// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health-system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}	
		
		//=================================INSERT DOCTOR METHOD=========================================================
		
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

				
				//int vac = 0;
				
				//String q2 = "SELECT d.Staff_Vacancies INTO " + vac + "FROM departments d"
						//+ "WHERE d.DepartmenaName = " + dept;
				
				/*String q1 = "create function getVacancies(@DepartmentName varchar) returns int\r\n" + 
						"as\r\n" + 
						"begin\r\n" + 
						"	declare @vac int\r\n" + 
						"	select d.Staff_Vacancies\r\n" + 
						"	from department d \r\n" + 
						"	where d.Department_Name = @DepartmentName\r\n" + 
						"	return @tot\r\n" + 
						"end\r\n" + 
						"declare @t int\r\n" + 
						"exec @t = getVacancies " + dept +
						"print @t";*/
				
				//System.out.println(vac);
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				/*String q3 = "PRINT"
						+ "SELECT * FROM departments";
				
				String q4 = "SELECT * FROM departments d";
				
				System.out.println(q4);*/

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
		
		//=======================================VIEW ALL DOCTORS METHOD=======================================================
		
		public String readDoctors() {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>DoctorName</th><th>NIC</th><th>Address</th><th>MobileNo</th><th>Email</th><th>Specialization</th><th>HospitalName</th><th>DepartmentName</th><th>Update</th><th>Remove</th></tr>";

				String query = "select * from doctors";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String docID = Integer.toString(rs.getInt("DoctorID"));
					String docName = rs.getString("DoctorName");
					String nic = rs.getString("NIC");
					String address = rs.getString("Address");
					String mobNo = Integer.toString(rs.getInt("MobileNo"));
					String email = rs.getString("Email");
					String spec = rs.getString("Specialization");
					String hosp = rs.getString("HospitalName");
					String dept = rs.getString("DepartmentName");

					// Add into the html table
					output += "<tr><td>" + docName + "</td>";
					output += "<td>" + nic + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + mobNo + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + spec + "</td>";
					output += "<td>" + hosp + "</td>";
					output += "<td>" + dept + "</td>";

					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"        "
							+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"doctors.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
							+ "class=\"btn btn-danger\">" + "<input name=\"docID\" type=\"hidden\" value=\"" + docID
							+ "\">" + "</form></td></tr>";
				}

				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}

			return output;

		}
		
		//========================================UPDATE DOCTORS METHOD============================================================
		
		public String updateDoctor(String ID, String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE doctors SET DoctorName=?,NIC=?,Address=?,MobileNo=?,Email=?,Specialization=?,HospitalName=?,DepartmentName=?WHERE DoctorID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, docName);
				preparedStmt.setString(2, nic);
				preparedStmt.setString(3, address);
				preparedStmt.setInt(4, Integer.parseInt(mobNo));
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, spec);
				preparedStmt.setString(7, hosp);
				preparedStmt.setString(8, dept);
				preparedStmt.setInt(9, Integer.parseInt(ID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		public String deleteDoctor(String docID) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from doctors where DoctorID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(docID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//===========================================SEARCH DOCTORS METHOD======================================================
		
		//view list of doctors
		public List<DoctorBean> viewDoctors() {
			
			return	viewDoctors(0);

		}
		
		//show the type by ID
		public DoctorBean ShowDoctorById(int DoctorID) {
		List<DoctorBean> list =viewDoctors(DoctorID);
			if(!list.isEmpty()) {
				return	list.get(0);
			}
			return null;
		}
		
		//view method
		public List<DoctorBean> viewDoctors(int DoctorID) {
				List <DoctorBean> DoctorList = new ArrayList<DoctorBean>();
				
			try 
			{
				Connection con = connect();
				if (con == null) {
					
					System.out.println("Error While reading from database");
					return DoctorList;
				}

				String query;
				
				if(DoctorID==0) {
				query = "select * from doctors";
				}
				else {
					query = "select * from doctors where DoctorID="+DoctorID;	
				}
				Statement stmt = con.createStatement();
				ResultSet results = stmt.executeQuery(query);

				while (results.next()) {
					DoctorBean doctor = new DoctorBean(
											results.getInt("DoctorID"),
											results.getString("DoctorName"),
											results.getString("NIC"),
											results.getString("Address"),
											results.getInt("MobileNo"),
											results.getString("Email"),
											results.getString("Specialization"),
											results.getString("HospitalName"),
											results.getString("DepartmentName")
										);
					DoctorList.add(doctor);
				}
				con.close();
			}
			catch (Exception e) {
				System.out.println("Error While Reading");
				System.err.println(e.getMessage());
			}
			
			return DoctorList;
		}
}
