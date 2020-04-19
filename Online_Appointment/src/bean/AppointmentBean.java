package bean;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AppointmentBean {
	private int appointmentid;
	private String patientid;
	private String dueDate;
	private int scheduleid;
	private boolean status;
	
	public AppointmentBean() {
		
	}
	public AppointmentBean(int appointmentid, String patientid, String dueDate, int scheduleid, boolean status) {
		this.appointmentid = appointmentid;
		this.patientid = patientid;
		this.dueDate = dueDate;
		this.scheduleid = scheduleid;
		this.status = status;
	}
	public int getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public int getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
