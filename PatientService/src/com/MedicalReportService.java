package com;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bean.MedicalReportBean;
//import beans.AppoinmentTypeBean;
//import beans.AppoinmentTypeBean;
import model.MedicalReport;

@Path("/Reports")
public class MedicalReportService {

	MedicalReport report = new MedicalReport();

//Add Details To report
	@POST
	@Path("/add-report")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addReportDetails(@FormParam("patientID") String patientID, @FormParam("aID") String aID,
			@FormParam("diagnosis") String diagnosis, @FormParam("disabilities") String disabilities,
			@FormParam("surgery") String surgery, @FormParam("bloodPressure") float bloodPressure,
			@FormParam("pulseRate") float pulseRate) {

		String output = report.AddDetailsToMedicatReport(patientID, aID, diagnosis, disabilities, surgery,
				bloodPressure, pulseRate);

		return output;
	}

	
	
//delete Report
	@DELETE
	@Path("/delete-report")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointmentSchedule(String reportDate) {
		// Convert the input string to a JSON object
		JsonObject doc = new JsonParser().parse(reportDate).getAsJsonObject();

		// Read the value from the element <ID>
		String aIDs = doc.get("aID").getAsString();
		String output = report.deleteMedicalReport(aIDs);
		return output;
	}
	
	
	@GET
	@Path("/{aID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String showReport(@PathParam("aID") String id) {
		return report.viewReport(id);
	}
	
/*
	
	//View Patient by Appointment ID
	@GET
	@Path("/{aID}")
	@Produces(MediaType.TEXT_HTML)
	public String viewReportDetails(@FormParam("aID") int aID) {
		return report.viewReport(aID);
	}
	
	/*
	@GET
	@Path("/{appointment_Id}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppoinmentTypeBean ShowTypeById(@PathParam("appointment_Id") int id) {
		return appoinmentType.ShowTypeById(id);
	}
	
	*/
	
}
