package com;

import model.Doctor;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/DocServ")
public class DoctorService {

	Doctor docObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() 
	{
		//return "Hello";
		return docObj.readDoctors();
	}
	
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDoctor(@FormParam("DoctorName") String docName,       
							@FormParam("NIC") String nic,    
							@FormParam("Address") String address,       
							@FormParam("MobileNo") String mobNo, 
							@FormParam("Email") String email,
							@FormParam("Specialization") String spec,
							@FormParam("HospitalName") String hosp,
							@FormParam("DepartmentName") String dept) 
	{  
		String output = docObj.insertDoctor(docName, nic, address, mobNo, email, spec, hosp, dept);  
		return output; 
	}
}
