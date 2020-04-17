package com.DoctorManagementMaven.DoctorManagementMaven;

import model.DoctorManagementMaven.DoctorManagementMaven.Doctor;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("DoctorService")
public class DoctorService {

	Doctor docObj = new Doctor();
	
	//==============================VIEW ALL DOCTORS============================================
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return docObj.readDoctors();
    }
	
	//==============================INSERT DOCTORS============================================
	
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
	
	//==============================UPDATE DOCTORS============================================
	
	@PUT 
    @Path("/update") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String updateDoctor(String docData) {  

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
    } 
	
	//==============================DELETE DOCTORS============================================
	
	@DELETE 
    @Path("/delete") 
    @Consumes(MediaType.APPLICATION_XML) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String deleteDoctor(String docData) {  
    	
    	//Convert the input string to an XML document  
    	Document doc = Jsoup.parse(docData, "", Parser.xmlParser());     
    	
    	//Read the value from the element <itemID>  
    	String DoctorID = doc.select("DoctorID").text(); 
     
    	String output = docObj.deleteDoctor(DoctorID); 
     
    	return output; 
    	
    }
	
	
}
