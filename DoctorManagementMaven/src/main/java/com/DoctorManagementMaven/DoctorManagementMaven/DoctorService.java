package com.DoctorManagementMaven.DoctorManagementMaven;

import model.DoctorManagementMaven.DoctorManagementMaven.Doctor;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("DoctorService")
public class DoctorService {

	Doctor docObj = new Doctor();
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return docObj.readDoctors();
    }
	
	@POST 
    @Path("/insert") 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String insertDoctor(@FormParam("DoctorName") String DoctorName,       
    						@FormParam("NIC") String NIC,    
    						@FormParam("Address") String Address,   
    						@FormParam("MobileNo") String MobileNo,
    						@FormParam("Email") String Email,
    						@FormParam("Specialization") String Specialization,
    						@FormParam("HospitalName") String HospitalName,
    						@FormParam("DepartmentName") String DepartmentName) 
    {  
    	String output = docObj.insertDoctor(DoctorName,NIC,Address,MobileNo,Email,Specialization,HospitalName,DepartmentName);  
    	return output; 
    }
	
	@PUT 
    @Path("/update") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String updateDoctor(String docData) {  

    	//Convert the input string to a JSON object  
    	JsonObject itemObject = new JsonParser().parse(docData).getAsJsonObject(); 
     
    	//Read the values from the JSON object  
    	String DoctorID = itemObject.get("DoctorID").getAsString();  
    	String DoctorName = itemObject.get("DoctorName").getAsString();  
    	String NIC = itemObject.get("NIC").getAsString();  
    	String Address = itemObject.get("Address").getAsString();  
    	String MobileNo = itemObject.get("MobileNo").getAsString(); 
    	String Email = itemObject.get("Email").getAsString(); 
    	String Specialization = itemObject.get("Specialization").getAsString(); 
    	String HospitalName = itemObject.get("HospitalName").getAsString(); 
    	String DepartmentName = itemObject.get("DepartmentName").getAsString(); 
     
        String output = docObj.updateDoctor(DoctorID, DoctorName, NIC, Address, MobileNo, Email, Specialization, HospitalName, DepartmentName); 
     
    	return output; 
    } 
}
