package com.DoctorManagementMaven.DoctorManagementMaven;

import model.DoctorManagementMaven.DoctorManagementMaven.Doctor;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
