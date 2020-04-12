package com;

import model.Hospital;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*;

import bean.AppoinmentTypeBean;
import bean.HospitalBean;

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Hospital") 
public class HospitalService {  
	Hospital hospitalObj = new Hospital();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readHospitals()  {   
		return hospitalObj.readHospitals();
	}

//	@POST 
//	@Path("/") 
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
//	@Produces(MediaType.TEXT_PLAIN) 
//	public String insertHospitals(@FormParam("Hospital_Name") String Hospital_Name,
//							@FormParam("Hospital_Address") String Hospital_Address,    
//							@FormParam("Hospital_City") String Hospital_City,       
//							@FormParam("Hospital_Phone") String Hospital_Phone,
//							@FormParam("Hospital_Email") String Hospital_Email,       
//							@FormParam("Hospital_Description") String Hospital_Description,
//							@FormParam("Open_Hours") String Open_Hours) 
//	{  
//		String output = hospitalObj.insertHospitals(Hospital_Name, Hospital_Address, Hospital_City, Hospital_Phone, Hospital_Email, Hospital_Description, Open_Hours);  
//		return output; 
//	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitals(String HospitalData) {
		// Convert the input string to a JSON object
		JsonObject hosjosnObj = new JsonParser().parse(HospitalData).getAsJsonObject();
	
		HospitalBean hosBean = new HospitalBean();
		
		hosBean.setHospital_Name(hosjosnObj.get("Hospital_Name").getAsString());
		hosBean.setHospital_Address(hosjosnObj.get("Hospital_Address").getAsString());
		hosBean.setHospital_City(hosjosnObj.get("Hospital_City").getAsString());
		hosBean.setHospital_Phone(hosjosnObj.get("Hospital_Phone").getAsString());
		hosBean.setHospital_Email(hosjosnObj.get("Hospital_Email").getAsString());
		hosBean.setHospital_Description(hosjosnObj.get("Hospital_Description").getAsString());
		hosBean.setOpen_Hours(hosjosnObj.get("Open_Hours").getAsInt());
		
		// Read the values from the JSON object
		String output = hospitalObj.insertHospitals(hosBean);
		return output;

	}	
}
