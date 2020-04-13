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
	public String readItems()  
	{   
		return docObj.readItems();  
	} 
	
} 