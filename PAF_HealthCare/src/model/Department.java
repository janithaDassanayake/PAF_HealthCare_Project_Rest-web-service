package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.DepartmentBean;
import util.DBConnection;

public class Department {
	DBConnection dbObj = new DBConnection();
	
	//Read Department Details
	public String readDepartments() {  
		String output = "";  


		try {  
			Connection con = dbObj.connect();
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

		// Prepare the html table to be displayed   
		output = "<table border=\"1\"><tr><th>Department Name</th>"    +""
				+ "<th>Hospital Name</th><th>Head of Department</th>"    + ""
				+ "<th>Number of Staff Vaconcies</th>";
		//+ ""
		//				+ "<th>Update</th><th>Remove</th></tr>"; 

		  String query1 = "select d.Department_ID,d.Department_Name,h.Hospital_Name,d.Head,d.Staff_Vacancies from departments d,hospitals h where d.Hospital_ID = h.Hospital_ID";
		  Statement stmt = con.createStatement();   
		  ResultSet rs1 = stmt.executeQuery(query1); 
		  
		  // iterate through the rows in the result set   
		  while (rs1.next())   {
			  String Department_ID = Integer.toString(rs1.getInt("Department_ID"));   
			  String Department_Name = rs1.getString("Department_Name");
			  String Hospital_Name = rs1.getString("Hospital_Name");
			  String Head = rs1.getString("Head");
			  String Staff_Vacancies = Integer.toString(rs1.getInt("Staff_Vacancies"));  

		   // Add into the html table    
		  output += "<tr><td>" + Department_Name + "</td>";    
		  output += "<td>" + Hospital_Name + "</td>";
		  output += "<td>" + Head + "</td>";
		  output += "<td>" + Staff_Vacancies + "</td>";
		   

//		   // buttons    
//		  output += "<td><input name=\"btnUpdate\" "     + " "
//		  		+ "type=\"button\" value=\"Update\"></td>"     + ""
//		  				+ "<td><form method=\"post\" action=\"departments.jsp\">"     + ""
//		  						+ "<input name=\"btnRemove\" "     + " "
//		  								+ "type=\"submit\" value=\"Remove\">"     + ""
//		  										+ "<input name=\"Department_ID\" type=\"hidden\" "     + " "
//		  												+ "value=\"" + 
//		  												Department_ID + "\">" + "</form></td></tr>";   
		  } 

		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Department data.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}
	
	//Insert departments
	
	public String insertDepartments(DepartmentBean depBean) {
		String output = "";

		try {
			Connection con = dbObj.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into departments (`Department_ID`,`Hospital_ID`,`Department_Name`,`Head`,`Staff_Vacancies`)"+" values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0);   
			preparedStmt.setInt(2, depBean.getHospital_ID());
			preparedStmt.setString(3, depBean.getDepartment_Name());    
			preparedStmt.setString(4, depBean.getHead());
			preparedStmt.setInt(5, depBean.getStaff_Vacancies());  

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			output = "Inserted successfully";
		}
		catch (Exception e) {   
			output = "Error while inserting Departments to the hospitals.";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
}
