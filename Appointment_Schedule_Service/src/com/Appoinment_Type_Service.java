package com;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.AppoinmentTypeBean;
import model.AppoinmentSchedule;
import model.AppoinmentType;

@Path("/AppoinmentTypes")
public class Appoinment_Type_Service {
	
	AppoinmentType appoinmentType = new AppoinmentType();

	
	// get all types
	@GET
	@Path("/appointment-type")
	@Produces(MediaType.TEXT_HTML)
	public String readAllTypes() {
		return appoinmentType.viewAppointmentTypes();
	}

	
////	//add types
//	@POST
//	@Path("/appointment-type")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN) 
//	public String enterType(
//			@FormParam("appointment_Type") String app_type,
//			@FormParam("appointment_Name") String app_name,
//			@FormParam("appointment_Desc") String app_desc) {
//
//		String output = aObj.addAppointmentType(app_type, app_name, app_desc);
//		return output;
//	}

//	//add Schedules
//	@POST
//	@Path("/appointment-schedule")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String enterSchedule(
//			@FormParam("Date") Date date,
//			@FormParam("Start_Time") Time stratTime,
//			@FormParam("End_Time") Time endTime,
//			@FormParam("D_id") int dId,
//			@FormParam("H_id") int hId,
//			@FormParam("App_id") int appId) {
//
//		String output = aObj.makeAppoinment(date, stratTime, endTime, dId, hId, appId);
//		return output;
//	}


	// add types
	@POST
	@Path("/appointment-type")
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
		@PUT
		@Path("/appointment-type")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppType(String TypeData) {

			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(TypeData).getAsJsonObject();
			AppoinmentTypeBean	appTypeBean = new AppoinmentTypeBean();

			// Read the values from the JSON object
//			int appId = djosnObj.get("appointment_Id").getAsInt();
//			String appType = djosnObj.get("appointment_Type").getAsString();
//			String appName = djosnObj.get("appointment_Name").getAsString();
//			String appDesc = djosnObj.get("appointment_Desc").getAsString();
			appTypeBean.setAppointment_Id(djosnObj.get("Appointment_Id").getAsInt());
			appTypeBean.setAppointment_Type(djosnObj.get("Appointment_Type").getAsString());
			appTypeBean.setAppointment_Name(djosnObj.get("Appointment_Name").getAsString());
			appTypeBean.setAppointment_Desc(djosnObj.get("Appointment_Desc").getAsString());
			
			String output = appoinmentType.updateAppointmentType(appTypeBean);
			return output;
		}
	
	
	


		// delete Types
		@DELETE
		@Path("/appointment-type")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteDoctor(String TypeData) {
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
