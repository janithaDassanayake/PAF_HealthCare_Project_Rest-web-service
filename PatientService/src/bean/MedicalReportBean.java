package bean;

import java.sql.Date;

public class MedicalReportBean {

	//private String patientID;
	private int aID;
	private int padId;
	private Date date;
	//private String firstName;
	private String diagnosis;
	private String disabilities;
	private String surgery;
	private float bloodPressure;
	private float pulseRate;
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	public int getPadId() {
		return padId;
	}
	public void setPadId(int padId) {
		this.padId = padId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDisabilities() {
		return disabilities;
	}
	public void setDisabilities(String disabilities) {
		this.disabilities = disabilities;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public float getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(float bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public float getPulseRate() {
		return pulseRate;
	}
	public void setPulseRate(float pulseRate) {
		this.pulseRate = pulseRate;
	}
	
	
}
