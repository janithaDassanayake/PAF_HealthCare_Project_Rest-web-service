package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBConnection;

public class Doctor {

	DBConnection dbObj = new DBConnection();
	
	public String insertItem(String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = dbObj.connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting."; 
			} 
	 
			// create a prepared statement    
			String query = " insert into doctors('DoctorID','DoctorName','NIC','Address','MobileNo','Email','Specialization','HospitalName','DepartmentName')"      
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
		}   
		catch (Exception e)   
		{    
			output = "Error while inserting the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String readItems()  
	{   
		String output = ""; 
	
	 
		try   
		{    
			Connection con = dbObj.connect(); 
		
	 
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			}
	 
			// Prepare the html table to be displayed    
			output = "<table border=\"1\"><tr><th>Doctor Name</th><th>NIC</th><th>Address</th><th>Email</th><th>Specialization</th><th>Hospital Name</th><th>Department Name</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from doctors";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String DoctorID = Integer.toString(rs.getInt("DoctorID"));     
				String DoctorName = rs.getString("DoctorName");     
				String NIC = rs.getString("NIC");  
				String Address = rs.getString("Address"); 
				String MobileNo = Integer.toString(rs.getInt("MobileNo"));    
				String Email = rs.getString("Email"); 
				String Specialization = rs.getString("Specialization");
				String HospitalName = rs.getString("HospitalName");
				String DepartmentName = rs.getString("DepartmentName");
			
	 
				// Add into the html table    
				output += "<tr><td>" + DoctorID + "</td>";     
				output += "<td>" + DoctorName + "</td>";     
				output += "<td>" + NIC + "</td>";     
				output += "<td>" + Address + "</td>"; 
				output += "<td>" + MobileNo + "</td>"; 
				output += "<td>" + Email + "</td>"; 
				output += "<td>" + Specialization + "</td>"; 
				output += "<td>" + HospitalName + "</td>"; 
				output += "<td>" + DepartmentName + "</td>"; 
	 
				// buttons     
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"      
						+ "<td><form method=\"post\" action=\"doctors.jsp\">"      
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"      
						+ "<input name=\"DoctorID\" type=\"hidden\" value=\"" + DoctorID      + "\">" + "</form></td></tr>";    
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the items.";    
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	}
	
	public String updateItem(String docID, String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = dbObj.connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating."; 
			} 
	 
			// create a prepared statement    
			String query = "UPDATE doctors SET DoctorName=?,NIC=?,Address=?,MobileNo=?,Email=?,Specialization=?,HospitalName=?,DepartmentName=?WHERE itemID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(1, docName);    
			preparedStmt.setString(2, nic);   
			preparedStmt.setString(3, address); 
			preparedStmt.setInt(4, Integer.parseInt(mobNo));    
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, spec); 
			preparedStmt.setString(7, hosp); 
			preparedStmt.setString(8, dept); 
			preparedStmt.setInt(9, Integer.parseInt(docID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			output = "Updated successfully";   
		}   
		catch (Exception e)   
		{   
			output = "Error while updating the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	
}
