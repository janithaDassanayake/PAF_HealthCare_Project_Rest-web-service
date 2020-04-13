package bean;

public class DoctorBean {

	private int DoctorID;
	private String DoctorName;
	private String NIC;
	private String Address;
	private int MobileNo;
	private String Email;
	private String Specialization;
	private String HospitalName;
	private String DepartmentName;
	
	public DoctorBean(int doctorID, String doctorName, String nIC, String address, int mobileNo, String email,
			String specialization, String hospitalName, String departmentName) {
		super();
		DoctorID = doctorID;
		DoctorName = doctorName;
		NIC = nIC;
		Address = address;
		MobileNo = mobileNo;
		Email = email;
		Specialization = specialization;
		HospitalName = hospitalName;
		DepartmentName = departmentName;
	}

	public int getDoctorID() {
		return DoctorID;
	}

	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}

	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(int mobileNo) {
		MobileNo = mobileNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	
	
	
	
}
