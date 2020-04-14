package bean;

import java.sql.Date;

public class DoctorAppoinmentBean {
	private int appointmentid;
	private int patientid;
	private Date dueDate;
	private int scheduleid;
	private int status;
	
	public int getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
