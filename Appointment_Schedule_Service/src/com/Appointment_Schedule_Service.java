
package com;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.ScheduleBean;
import model.AppoinmentSchedule;


@Path("/AppoinmentSchedule")
public class Appointment_Schedule_Service {

	AppoinmentSchedule aObj2 = new AppoinmentSchedule();

	
	// get all Schedules
	@RolesAllowed({ "admin", "doctors", "patient" })
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllSchedules() {
		return aObj2.viewAllSchedule();
	}

	
	
	// View a schedule by given by day

	@RolesAllowed({ "admin", "doctors" })
	@GET
	@Path("/{Date}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ScheduleBean> readScheduleByGiven_DAY(@PathParam("Date") String date) {
		List<ScheduleBean> list;
		list = aObj2.View_Shedules_By_given_Day(date);
		return list;

	}
	
	
	
	
	// View a schedule by given by Schedule id
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/schedule/{schedule_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleBean readScheduleByGiven_ID(@PathParam("schedule_id") int S_id) {
		return aObj2.Show_schedule_By_Id(S_id);
	}

	

	
	
	// add schedule
	@RolesAllowed({ "admin" })
	@POST
	@Path("/")
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
		

		DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

		try {
			java.util.Date stime = (java.util.Date) formatTime.parse(appstart);
			java.sql.Time startTime = convertUtilToSqlTime(stime);

			java.util.Date etime = (java.util.Date) formatTime.parse(append);
			java.sql.Time endTime = convertUtilToSqlTime(etime);

			String output = aObj2.add_Appoinment_Schedule(appdate, startTime, endTime, dId, hId, appId);
			result = output;

		} catch (ParseException e) {
			e.printStackTrace();
			result = e.toString();
		}
		return result;

	}

	
	
	// update Schedules
	@RolesAllowed({ "admin" })
	@PUT
	@Path("/")
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
		

		DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

		try {
			java.util.Date stime = (java.util.Date) formatTime.parse(appstart);
			java.sql.Time startTime = convertUtilToSqlTime(stime);

			java.util.Date etime = (java.util.Date) formatTime.parse(append);
			java.sql.Time endTime = convertUtilToSqlTime(etime);

			String output = aObj2.updateAppointmentSchedule(Integer.parseInt(sId), appdate, startTime, endTime, dId,
					hId, appId);
			result = output;

		} catch (ParseException e) {
			e.printStackTrace();
			result = e.toString();
		}
		return result;

	}

	// =========************* return sql time*********==========

	private static java.sql.Time convertUtilToSqlTime(java.util.Date uDate) {
		java.sql.Time sTime = new java.sql.Time(uDate.getTime());
		return sTime;
	}

	// delete Schedules
	@RolesAllowed({ "admin" })
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointmentSchedule(String scheduleData) {
		// Convert the input string to a JSON object
		JsonObject doc = new JsonParser().parse(scheduleData).getAsJsonObject();

		ScheduleBean appSchedule = new ScheduleBean();
		// Read the value from the element <ID>
		appSchedule.setSchedule_id(doc.get("schedule_id").getAsInt());

		String output = aObj2.deleteAppointmentSchedule(appSchedule);
		return output;
	}

}
