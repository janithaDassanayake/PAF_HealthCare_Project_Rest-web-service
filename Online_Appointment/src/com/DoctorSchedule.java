package com;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Schedule;

@Path("/Schedule")
public class DoctorSchedule {

	Schedule aObj = new Schedule();

	@RolesAllowed({"patient","admin","doctors"})
	// get doctors' Schedules
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllSchedules() {
		return aObj.viewAllSchedule();
	}
}
