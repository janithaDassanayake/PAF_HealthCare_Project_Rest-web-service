package bean;

public class DepartmentBean {

	private int Department_ID;
	private int Hospital_ID;
	private String Department_Name;
	private int Head;
	private int Staff_Vacancies;
	
	public DepartmentBean() {
		
	}
	
	public DepartmentBean(int Department_ID, int Hospital_ID, String Department_Name) {
		this.Department_ID = Department_ID;
		this.Hospital_ID = Hospital_ID;
		this.Department_Name = Department_Name;
	}
	
	public int getDepartment_ID() {
		return Department_ID;
	}
	
	public int getHospital_ID() {
		return Hospital_ID;
	}
	
	public String getDepartment_Name() {
		return Department_Name;
	}
	
	public int getHead() {
		return Head;
	}
	
	public int getStaff_Vacancies() {
		return Staff_Vacancies;
	}
	
	public void setDepartment_ID(int Department_ID) {
		this.Department_ID = Department_ID;
	}
	
	public void setHospital_ID(int Hospital_ID) {
		this.Hospital_ID = Hospital_ID;
	}
	
	public void setDepartment_Name(String Department_Name) {
		this.Department_Name = Department_Name;
	}
	
	public void setHead(int Head) {
		this.Head = Head;
	}
	
	public void setStaff_Vacancies(int Staff_Vacancies) {
		this.Staff_Vacancies = Staff_Vacancies;
	}
}