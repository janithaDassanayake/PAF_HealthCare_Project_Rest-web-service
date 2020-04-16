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

		
		
		// add schedule
		@POST
		@Path("/appointment-schedule")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String enterSchedule(String scheduleData) {
			String result = null;
			// Convert the input string to a JSON object
			JsonObject djosnObj = new JsonParser().parse(scheduleData).getAsJsonObject();

			String appdate = djosnObj.get("Date").getAsString();
			String appstart = djosnObj.get("Start_Time").getAsString();
			String append = djosnObj.get("End_Time").getAsString();
			int dId = djosnObj.get("D_id").getAsInt();
			int hId = djosnObj.get("H_id").getAsInt();
			int appId = djosnObj.get("App_id").getAsInt();

			// ==================convert util time to sql time=====================
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

			try {
				java.util.Date date = (java.util.Date) format.parse(appdate);
				java.sql.Date sDate = convertUtilToSql(date);

				java.util.Date stime = (java.util.Date) formatTime.parse(appstart);
				java.sql.Time startTime = convertUtilToSqlTime(stime);

				java.util.Date etime = (java.util.Date) formatTime.parse(append);
				java.sql.Time endTime = convertUtilToSqlTime(etime);

				String output = aObj2.makeAppoinment(sDate, startTime, endTime, dId, hId, appId);
				result = output;

			} catch (ParseException e) {
				e.printStackTrace();
				result = e.toString();
			}
			return result;

		}
		
		
		
	
		
		// update Schedules
		@PUT
		@Path("/appointment-schedule")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppschedule(String TypeData) {
			String result = null;
			// Convert the input string to a JSON object
			JsonObject sjosnObj = new JsonParser().parse(TypeData).getAsJsonObject();

			// Read the values from the JSON object
			String sId = sjosnObj.get("schedule_id").getAsString();
			String appdate = sjosnObj.get("Date").getAsString();
			String appstart = sjosnObj.get("Start_Time").getAsString();
			String append = sjosnObj.get("End_Time").getAsString();
			int dId = sjosnObj.get("D_id").getAsInt();
			int hId = sjosnObj.get("H_id").getAsInt();
			int appId = sjosnObj.get("App_id").getAsInt();

			// ==================convert util time to sql time=====================
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

			try {
				java.util.Date date = (java.util.Date) format.parse(appdate);
				java.sql.Date sDate = convertUtilToSql(date);

				java.util.Date stime = (java.util.Date) formatTime.parse(appstart);
				java.sql.Time startTime = convertUtilToSqlTime(stime);

				java.util.Date etime = (java.util.Date) formatTime.parse(append);
				java.sql.Time endTime = convertUtilToSqlTime(etime);

				String output = aObj2.updateAppointmentType(Integer.parseInt(sId), sDate, startTime, endTime, dId, hId,
						appId);
				result = output;

			} catch (ParseException e) {
				e.printStackTrace();
				result = e.toString();
			}
			return result;

		}

		// =========************* return sql date and time*********==========
		private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
			java.sql.Date sDate = new java.sql.Date(uDate.getTime());
			return sDate;
		}

		private static java.sql.Time convertUtilToSqlTime(java.util.Date uDate) {
			java.sql.Time sTime = new java.sql.Time(uDate.getTime());
			return sTime;
		}

		
		
		// delete Schedules
		@DELETE
		@Path("/appointment-schedule")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteAppointmentSchedule(String scheduleData) {
			// Convert the input string to a JSON object
			JsonObject doc = new JsonParser().parse(scheduleData).getAsJsonObject();

			// Read the value from the element <ID>
			String id = doc.get("schedule_id").getAsString();
			String output = aObj2.deleteAppointmentSchedule(id);
			return output;
		}
		
}
