package com;

import model.Department;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*;

import bean.DepartmentBean;

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Department") 
public class DepartmentService {
	Department departmentObj = new Department();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readDepartments()  {   
		return departmentObj.readDepartments();
	}
	
//	@POST 
//	@Path("/") 
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
//	@Produces(MediaType.TEXT_PLAIN) 
//	public String insertDepartments(@FormParam("Hospital_ID") String Hospital_ID,
//							@FormParam("Department_Name") String Department_Name,
//							@FormParam("Head") String Head,    
//							@FormParam("Staff_Vacancies") String Staff_Vacancies)
//	{  
//		String output = departmentObj.insertDepartments(Hospital_ID, Department_Name, Head, Staff_Vacancies);  
//		return output; 
//	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDeps(String DepData) {
		// Convert the input string to a JSON object
		JsonObject depjosnObj = new JsonParser().parse(DepData).getAsJsonObject();
	
		DepartmentBean dep_Bean = new DepartmentBean();
		
		dep_Bean.setHospital_ID(depjosnObj.get("Hospital_ID").getAsInt());
		dep_Bean.setDepartment_Name(depjosnObj.get("Department_Name").getAsString());
		dep_Bean.setHead(depjosnObj.get("Head").getAsString());
		dep_Bean.setStaff_Vacancies(depjosnObj.get("Staff_Vacancies").getAsInt());
		
		// Read the values from the JSON object
		String output = departmentObj.insertDepartments(dep_Bean);
		return output;

	}
	
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDeps(String DepData) { 
		//Convert the input string to a JSON object  
		JsonObject dep_Object = new JsonParser().parse(DepData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String Department_ID = dep_Object.get("Department_ID").getAsString();  
		String Hospital_ID = dep_Object.get("Hospital_ID").getAsString();  
		String Department_Name = dep_Object.get("Department_Name").getAsString();  
		String Head = dep_Object.get("Head").getAsString();  
		String Staff_Vacancies = dep_Object.get("Staff_Vacancies").getAsString();
		 
		String output = departmentObj.updateDepartments(Department_ID, Hospital_ID, Department_Name, Head, Staff_Vacancies); 
		 
		return output; 
	}
}
