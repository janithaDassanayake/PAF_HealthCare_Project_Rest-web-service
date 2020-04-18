package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.PaymentBean;
import model.Payment;

/**
 * 
 * @author Ishanka
 * Service of user's payments
 * 
 */
@Path("/Payment")
public class PaymentService {
	
	Payment payment = new Payment();
	
	PaymentBean pBean = new PaymentBean();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
								@FormParam("appointment_id") int appointment_id,
								@FormParam("schedule_id") int schedule_id) {
		System.out.println("testing");
		String output = payment.addPayment(appointment_id, schedule_id);
		
		return output;
		
	}
	

}
