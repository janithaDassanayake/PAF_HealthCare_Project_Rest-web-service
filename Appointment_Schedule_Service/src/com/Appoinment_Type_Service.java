package com;



import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.AppoinmentTypeBean;

import model.AppoinmentType;

@Path("/AppoinmentTypes")
public class Appoinment_Type_Service {
	
	AppoinmentType appoinmentType = new AppoinmentType();

	
	// get all types
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return appoinmentType.viewAppointmentTypes();
	}

	
	
	
	// View a appointment type identified by id
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/{appointment_Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppoinmentTypeBean ShowTypeById(@PathParam("appointment_Id") int id) {
		return appoinmentType.ShowTypeById(id);
	}

	


	// add types
	@RolesAllowed({"admin"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String enterType(String TypeData) {
		// Convert the input string to a JSON object
		JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	
		AppoinmentTypeBean	appTypeBean = new AppoinmentTypeBean();
		
		appTypeBean.setAppointment_Type(djosnObj.get("Appointment_Type").getAsString());
		appTypeBean.setAppointment_Name(djosnObj.get("Appointment_Name").getAsString());
		appTypeBean.setAppointment_Desc(djosnObj.get("Appointment_Desc").getAsString());
		// Read the values from the JSON object
	
		String output = appoinmentType.addAppointmentType(appTypeBean);
		return output;

	}

	
	
	
	// update Types
		@RolesAllowed({"admin"})
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppType(String TypeData) {

			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			AppoinmentTypeBean	appTypeBean = new AppoinmentTypeBean();

			appTypeBean.setAppointment_Id(djosnObj.get("Appointment_Id").getAsInt());
			appTypeBean.setAppointment_Type(djosnObj.get("Appointment_Type").getAsString());
			appTypeBean.setAppointment_Name(djosnObj.get("Appointment_Name").getAsString());
			appTypeBean.setAppointment_Desc(djosnObj.get("Appointment_Desc").getAsString());
			
			String output = appoinmentType.updateAppointmentType(appTypeBean);
			return output;
		}
	
	
	


		// delete Types
		@RolesAllowed({"admin"})
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletetype(String TypeData) {
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(TypeData).getAsJsonObject();

//			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
	    	AppoinmentTypeBean	appTypeBean = new AppoinmentTypeBean();
			
			// Read the value from the element <ID>
	    	appTypeBean.setAppointment_Id(doc.get("Appointment_Id").getAsInt());
			//String id = doc.get("appointment_Id").getAsString();
			String output = appoinmentType.deleteAppointmentTypes(appTypeBean);
			return output;
		}
	
}
