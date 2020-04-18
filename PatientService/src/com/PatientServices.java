package com;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.swing.text.Document;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.Data;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bean.PatientDetailsBean;
import model.Patient;

@Path("/Patients")
public class PatientServices {

	Patient patient = new Patient();

	// All Patient Details
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/patientdetails")
	@Produces(MediaType.TEXT_HTML)
	public String readAll() {
		return patient.viewPatientDetails();
	}

	//View Emergency details
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/patientEmergency")
	@Produces(MediaType.TEXT_HTML)
	public String readEmergency() {
		return patient.viewEmergencyDetails();
	}
	
	
	//View no appoinment Patients
	@RolesAllowed({"admin"})
	@GET
	@Path("/show-nonPatients")
	@Produces(MediaType.TEXT_HTML)
	public String readNonPatients() {
		return patient.showTempPatients();
	}
	
	// Add new Patients
	@RolesAllowed({"admin","patient"})
	@POST
	@Path("/add-Patient")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPatient(@FormParam("patientID") String patientID, @FormParam("firstName") String firstName,
			@FormParam("middleName") String middleName, @FormParam("lastName") String lastName,
			@FormParam("email") String email, @FormParam("sex") String sex, @FormParam("dob") Date dob,
			@FormParam("weight") float weight, @FormParam("height") float height,
			@FormParam("contactNumber") int contactNumber, @FormParam("maritalStatus") String maritalStatus,
			@FormParam("streetAddL1") String streetAddL1, @FormParam("streetAddL2") String streetAddL2,
			@FormParam("city") String city, @FormParam("state") String state, @FormParam("postalCode") int postalCode,
			@FormParam("password") String password, @FormParam("emergencyCNanme") String emergencyCNanme,
			@FormParam("emergencyCrelationship") String emergencyCrelationship,
			@FormParam("emergencynumber") int emergencynumber, @FormParam("takeMedication") boolean takeMedication,
			@FormParam("currentMedication") String currentMedication)

	{

		String output = patient.addPatient(patientID, firstName, middleName, lastName, email, sex, dob, weight, height,
				contactNumber, maritalStatus, streetAddL1, streetAddL2, city, state, postalCode, password,
				emergencyCNanme, emergencyCrelationship, emergencynumber, takeMedication, currentMedication);

		return output;
	}

	

	// Update
	@RolesAllowed({"admin","patient"})
	@PUT
	@Path("/update-patient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientDetails(String TypeData) {
		String result = "";

		JsonObject jObj = new JsonParser().parse(TypeData).getAsJsonObject();

		String pid = jObj.get("patientID").getAsString();
		String fName = jObj.get("firstName").getAsString();
		String mName = jObj.get("middleName").getAsString();
		String lName = jObj.get("lastName").getAsString();
		String email = jObj.get("email").getAsString();
		String sex = jObj.get("sex").getAsString();
		String sDob = jObj.get("dob").getAsString();
		float height = jObj.get("height").getAsFloat();
		float weight = jObj.get("weight").getAsFloat();
		int cNumber = jObj.get("contactNumber").getAsInt();
		String mStatus = jObj.get("maritalStatus").getAsString();
		String sAddL1 = jObj.get("streetAddL1").getAsString();
		String sAddL2 = jObj.get("streetAddL2").getAsString();
		String city = jObj.get("city").getAsString();
		String state = jObj.get("state").getAsString();
		int pCode = jObj.get("postalCode").getAsInt();

		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {

			java.util.Date fDob = (java.util.Date) dFormat.parse(sDob);
			java.sql.Date dob = convertUtilToSql(fDob);

			String output = patient.updatePatient(pid, fName, mName, lName, email, sex, dob, weight, height, cNumber,
					mStatus, sAddL1, sAddL2, city, state, pCode);

			result = output;
			
		} catch (ParseException e) {
			e.printStackTrace();
			result = e.toString();
		}

		return result;
	}

	private static java.sql.Date convertUtilToSql(java.util.Date Date) {
		java.sql.Date dob = new java.sql.Date(Date.getTime());
		return dob;
	}

	
	
	//delete
	@RolesAllowed({"admin"})
	@DELETE
	@Path("/delete-patient")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient (String patientData) {
		org.jsoup.nodes.Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());
		
		String patientID = doc.select("patientID").text();
		String output = patient.deletePatient(patientID);
		
		return output;
	}
	
	
	
	
	
	
}
