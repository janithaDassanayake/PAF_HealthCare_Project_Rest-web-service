package beans;

/**
 * 
 * @author Ishanka
 * payment class 
 * which describe the user's payments for appointment
 * 
 */
public class PaymentBean {
	
	private int payment_id;
	private int appointment_id;
	private int paymentScheme_id;
	private double total_charge;

	

	public int getPaymentScheme_id() {
		return paymentScheme_id;
	}
	public void setPaymentScheme_id(int paymentScheme_id) {
		this.paymentScheme_id = paymentScheme_id;
	}
	
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	
	
	public double getTotal_charge() {
		return total_charge;
	}
	public void setTotal_charge(double total_charge) {
		this.total_charge = total_charge;
	}
	
	

}
