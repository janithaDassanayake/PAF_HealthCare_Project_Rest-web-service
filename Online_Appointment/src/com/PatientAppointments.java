package com;

import java.util.List;

import javax.annotation.security.RolesAllowed;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.AppointmentBean;
import model.Appointment;

@Path("/A")
public class PatientAppointments {
	Appointment appObj = new Appointment();
	
	@RolesAllowed({"patient"})
	@GET
	@Path("/{patientid}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AppointmentBean> ShowAppointmentById(@PathParam("patientid") String id) {
		List<AppointmentBean> list;
		list = appObj.View_Appointments_By_given_ID(id);
		return list;
	}
}
