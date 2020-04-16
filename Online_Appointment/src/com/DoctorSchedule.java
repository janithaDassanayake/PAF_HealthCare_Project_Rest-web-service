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

import model.Schedule;

@Path("/Schedule")
public class DoctorSchedule {

	Schedule aObj = new Schedule();

	// get doctors' Schedules
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllSchedules() {
		return aObj.viewAllSchedule();
	}
}
