package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.AppointmentBean;
import model.Appointment;

@Path("/A")
public class PatientAppointments {
	Appointment appObj = new Appointment();

//	@GET
//	@Path("/{patient_Id}")
//	@Produces(MediaType.TEXT_HTML)
//	public AppointmentBean readItems(int id) {
//		return  appObj.ShowTypeById(id);
//	}
	
	@GET
	@Path("/{patient_Id}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppointmentBean ShowTypeById(@PathParam("patient_Id") int id) {
		return appObj.ShowTypeById(id);
	}
}
