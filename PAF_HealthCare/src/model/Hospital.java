package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.HospitalBean;
import util.DBConnection;

public class Hospital {
	DBConnection dbObj = new DBConnection();
	
	HospitalBean hosReadbean = new HospitalBean();
	//Read Hospitals Details
	public String readHospitals() {  
		String output = "";  


		try {  
			Connection con = dbObj.connect();
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

		// Prepare the html table to be displayed   
		output = "<table border=\"1\"><tr><th>Hospital Name</th>"    +""
				+ "<th>Hospital Address</th><th>Hospital City</th>"    + ""
				+ "<th>Hospital Phone</th><th>Hospital Email</th>"    + ""
				+ "<th>Hospital Description</th><th>Open Hours</th>";
//		+ ""
//						+ "<th>Update</th><th>Remove</th></tr>"; 

		  String query = "select * from hospitals";   
		  Statement stmt = con.createStatement();   
		  ResultSet rs = stmt.executeQuery(query); 

		  // iterate through the rows in the result set   
		  while (rs.next())   {  
			  hosReadbean.setHospital_ID(rs.getInt("Hospital_ID"));
			  hosReadbean.setHospital_Name(rs.getString("Hospital_Name"));
			  hosReadbean.setHospital_Address(rs.getString("Hospital_Address"));
			  hosReadbean.setHospital_City(rs.getString("Hospital_City"));
			  hosReadbean.setHospital_Phone(rs.getString("Hospital_Phone"));
			  hosReadbean.setHospital_Email(rs.getString("Hospital_Email"));
			  hosReadbean.setHospital_Description(rs.getString("Hospital_Description"));
			  hosReadbean.setOpen_Hours(rs.getInt("Open_Hours"));

		   // Add into the html table    
		  output += "<tr><td>" + hosReadbean.getHospital_ID() + "</td>";    
		  output += "<td>" + hosReadbean.getHospital_Name() + "</td>";
		  output += "<td>" + hosReadbean.getHospital_Address() + "</td>";    
		  output += "<td>" + hosReadbean.getHospital_City() + "</td>"; 
		  output += "<td>" + hosReadbean.getHospital_Phone() + "</td>";    
		  output += "<td>" + hosReadbean.getHospital_Email() + "</td>";
		  output += "<td>" + hosReadbean.getOpen_Hours() + "</td>"; 

//		   // buttons    
//		  output += "<td><input name=\"btnUpdate\" "     + " "
//		  		+ "type=\"button\" value=\"Update\"></td>"     + ""
//		  				+ "<td><form method=\"post\" action=\"hospitals.jsp\">"     + ""
//		  						+ "<input name=\"btnRemove\" "     + " "
//		  								+ "type=\"submit\" value=\"Remove\">"     + ""
//		  										+ "<input name=\"Hospital_ID\" type=\"hidden\" "     + " "
//		  												+ "value=\"" + 
//		  										Hospital_ID + "\">" + "</form></td></tr>";   
		  } 

		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Hospital data.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}
	
	//Insert Hospitals
	public String insertHospitals(HospitalBean hos_bean) {
		String output = "";

		try {
			Connection con = dbObj.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into hospitals (`Hospital_ID`,`Hospital_Name`,`Hospital_Address`,`Hospital_City`,`Hospital_Phone`,`Hospital_Email`,`Hospital_Description`,`Open_Hours`)"+" values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0);   
			preparedStmt.setString(2, hos_bean.getHospital_Name());   
			preparedStmt.setString(3, hos_bean.getHospital_Address());    
			preparedStmt.setString(4, hos_bean.getHospital_City());
			preparedStmt.setString(5, hos_bean.getHospital_Phone());
			preparedStmt.setString(6, hos_bean.getHospital_Email());
			preparedStmt.setString(7, hos_bean.getHospital_Description());
			preparedStmt.setInt(8, hos_bean.getOpen_Hours());  

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			output = "Inserted successfully";
		}
		catch (Exception e) {   
			output = "Error while inserting the Hospitals.";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
	
	//Update hospitals
	public String updateHospitals(String hos_id, String name, String address, String city, String phone, String email, String desc, String hours)  {   
		String output = ""; 
	 
	  try   {   
		  Connection con = dbObj.connect();
	 
		  if (con == null)    {
			  return "Error while connecting to the database for updating."; 
		  } 
	 
	   // create a prepared statement    
	   String query = "UPDATE hospitals SET Hospital_Name=?,Hospital_Address=?,Hospital_City=?,Hospital_Phone=?,Hospital_Email=?,Hospital_Description=?,Open_Hours=?      "
	   		+ "			WHERE Hospital_ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, address);    
	   preparedStmt.setString(3, city);
	   preparedStmt.setString(4, phone);
	   preparedStmt.setString(5, email);
	   preparedStmt.setString(6, desc);
	   preparedStmt.setInt(7, Integer.parseInt(hours));
	   preparedStmt.setInt(8, Integer.parseInt(hos_id));
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Updated successfully";   
	   }   catch (Exception e)   {    
		   output = "Error while updating the Hospitals.";    
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	public String deleteHospitals(String hos_id) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = dbObj.connect();
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "delete from hospitals where Hospital_ID=?"; 
	 
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values   
	  preparedStmt.setInt(1, Integer.parseInt(hos_id));       
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	  output = "Deleted successfully";  
	  }  catch (Exception e)  {   
		  output = "Error while deleting the hospital.";   
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
}
