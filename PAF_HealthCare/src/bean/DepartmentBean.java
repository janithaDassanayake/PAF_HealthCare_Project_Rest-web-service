package bean;

public class DepartmentBean {

	private int Department_ID;
	private String Department_Name;
	private String Head;
	private int Staff_Vacancies;
	
	public int Department_ID() {
		return Department_ID;
	}
	
	public String Department_Name() {
		return Department_Name;
	}
	
	public String Head() {
		return Head;
	}
	
	public int Staff_Vacancies() {
		return Staff_Vacancies;
	}
	
	public void setDepartment_ID(int Department_ID) {
		this.Department_ID = Department_ID;
	}
	
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
