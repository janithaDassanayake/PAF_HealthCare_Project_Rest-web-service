package com;

import javax.ws.rs.Path;
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

import model.AppoinmentSchedule;
import model.AppoinmentType;

@Path("/AppoinmentSchedule")
public class Appointment_Schedule_Service {

	AppoinmentSchedule aObj2 = new AppoinmentSchedule();
	
	// get all Schedules
		@GET
		@Path("/appointment-schedule")
		@Produces(MediaType.TEXT_HTML)
		public String readAllSchedules() {
			return aObj2.viewAllSchedule();
		}

}
