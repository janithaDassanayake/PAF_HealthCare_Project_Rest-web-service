package bean;

import java.sql.Date;
import java.sql.Time;

public class AppoinmentSchedulingBean {


	private int Schedule_id;
	private Date Date;
	private Time Start_Time;
	private Time End_Time;
	private int D_id;
	private int H_id;
	private int App_id;
	
	
	public int getSchedule_id() {
		return Schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		Schedule_id = schedule_id;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Time getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(Time start_Time) {
		Start_Time = start_Time;
	}
	public Time getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(Time end_Time) {
		End_Time = end_Time;
	}
	public int getD_id() {
		return D_id;
	}
	public void setD_id(int d_id) {
		D_id = d_id;
	}
	public int getH_id() {
		return H_id;
	}
	public void setH_id(int h_id) {
		H_id = h_id;
	}
	public int getApp_id() {
		return App_id;
	}
	public void setApp_id(int app_id) {
		App_id = app_id;
	}
	
}
