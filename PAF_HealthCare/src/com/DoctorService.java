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

@Path("/Doctors")
public class DoctorService {

	Doctor docObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor() {
		//return docObj.readDoctor();
		return "Hello";
	}

	/*@POST
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
			@FormParam("Department") String dept) {
		String output = docObj.insertDoctor(DoctorName,NIC,Address,MobileNo,Email,Specialization,HospitalName,Department);
		return output;
	}*/

	/*@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String docData) 
	{  
		//Convert the input string to a JSON object  
		JsonObject docObject = new JsonParser().parse(docData).getAsJsonObject(); 
		 
		//Read the values from the JSON object  
		String DoctorID = docObject.get("DoctorID").getAsString(); 
		String DoctorName = docObject.get("DoctorName").getAsString();  
		String NIC = docObject.get("NIC").getAsString();  
		String Address = docObject.get("Address").getAsString();  
		String MobileNo = docObject.get("MobileNo").getAsString();
		String Email = docObject.get("Email").getAsString();
		String Specialization = docObject.get("Specialization").getAsString();
		String HospitalName = docObject.get("HospitalName").getAsString();
		String DepartmentName = docObject.get("DepartmentName").getAsString();
		 
		String output = docObj.updateDoctor(DoctorID, DoctorName, NIC, Address, MobileNo, Email, Specialization, HospitalName, DepartmentName); 
		 
		 return output; 
		 
	} */
	
	/*@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String docData) 
	{  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(docData, "", Parser.xmlParser());     
		
		//Read the value from the element <itemID>  
		String DoctorID = doc.select("DoctorID").text(); 
		 
		 String output = docObj.deleteDoctor(DoctorID); 
		 
		 return output; 
	} */
	
}