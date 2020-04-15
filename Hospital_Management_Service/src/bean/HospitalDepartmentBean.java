package bean;

public class HospitalDepartmentBean {

	private int Hospital_ID;
	private String Hospital_Name;
	private String Hospital_Address;
	private String Hospital_City;
	private String Hospital_Phone;
	private String Hospital_Email;
	private String Hospital_Description;
	private int Open_Hours;
	
	//Related to department management
	private int Department_ID;
	//private int Hospital_ID;
	private String Department_Name;
	private String Head;
	private int Staff_Vacancies;
	
	//Getters and setters 
	public int getHospital_ID() {
		return Hospital_ID;
	}
	
	public String getHospital_Name() {
		return Hospital_Name;
	}
	
	public String getHospital_Address() {
		return Hospital_Address;
	}
	
	public String getHospital_City() {
		return Hospital_City;
	}
	
	public String getHospital_Phone() {
		return Hospital_Phone;
	}
	
	public String getHospital_Email() {
		return Hospital_Email;
	}
	
	public String getHospital_Description() {
		return Hospital_Description;
	}
	
	public int getOpen_Hours() {
		return Open_Hours;
	}
	
	public void setHospital_ID(int Hospital_ID) {
		this.Hospital_ID = Hospital_ID;
	}
	
	public void setHospital_Name(String Hospital_Name) {
		this.Hospital_Name = Hospital_Name;
	}
	
	public void setHospital_Address(String Hospital_Address) {
		this.Hospital_Address = Hospital_Address;
	}
	
	public void setHospital_City(String Hospital_City) {
		this.Hospital_City = Hospital_City;
	}
	
	public void setHospital_Phone(String Hospital_Phone) {
		this.Hospital_Phone = Hospital_Phone;
	}
	
	public void setHospital_Email(String Hospital_Email) {
		this.Hospital_Email = Hospital_Email;
	}
	
	public void setHospital_Description(String Hospital_Description) {
		this.Hospital_Description = Hospital_Description;
	}
	
	public void setOpen_Hours(int Open_Hours) {
		this.Open_Hours = Open_Hours;
	}
	
	//Related to department management
	public int getDepartment_ID() {
		return Department_ID;
	}
	
//	public int getHospital_ID() {
//		return Hospital_ID;
//	}
	
	public String getDepartment_Name() {
		return Department_Name;
	}
	
	public String getHead() {
		return Head;
	}
	
	public int getStaff_Vacancies() {
		return Staff_Vacancies;
	}
	
	public void setDepartment_ID(int Department_ID) {
		this.Department_ID = Department_ID;
	}
	
//	public void setHospital_ID(int Hospital_ID) {
//		this.Hospital_ID = Hospital_ID;
//	}
	
	public void setDepartment_Name(String Department_Name) {
		this.Department_Name = Department_Name;
	}
	
	public void setHead(String Head) {
		this.Head = Head;
	}
	
	public void setStaff_Vacancies(int Staff_Vacancies) {
		this.Staff_Vacancies = Staff_Vacancies;
	}
}
