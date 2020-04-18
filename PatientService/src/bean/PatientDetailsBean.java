package bean;

import java.sql.Date;

public class PatientDetailsBean {

	private int id;
	private String patientID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String sex;
	private Date dob;
	private float height;
	private float weight;
	private int contactNumber;
	private String 	maritalStatus;
	private String streetAddL1;
	private String streetAddL2;
	private String city;
	private String state;
	private int postalCode;
	private String password;
	
	private String emergencyCNanme;
	private String emergencyCrelationship;
	private int emergencynumber;
	private boolean takeMedication;
	private String currentMedication;
	private int age;
	
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmergencyCNanme() {
		return emergencyCNanme;
	}
	public void setEmergencyCNanme(String emergencyCNanme) {
		this.emergencyCNanme = emergencyCNanme;
	}
	public String getEmergencyCrelationship() {
		return emergencyCrelationship;
	}
	public void setEmergencyCrelationship(String emergencyCrelationship) {
		this.emergencyCrelationship = emergencyCrelationship;
	}
	public int getEmergencynumber() {
		return emergencynumber;
	}
	public void setEmergencynumber(int emergencynumber) {
		this.emergencynumber = emergencynumber;
	}
	public boolean isTakeMedication() {
		return takeMedication;
	}
	public void setTakeMedication(boolean takeMedication) {
		this.takeMedication = takeMedication;
	}
	public String getCurrentMedication() {
		return currentMedication;
	}
	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getStreetAddL1() {
		return streetAddL1;
	}
	public void setStreetAddL1(String streetAddL1) {
		this.streetAddL1 = streetAddL1;
	}
	public String getStreetAddL2() {
		return streetAddL2;
	}
	public void setStreetAddL2(String streetAddL2) {
		this.streetAddL2 = streetAddL2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	

}
