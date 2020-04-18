package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.PaymentSchemeBean;
import model.PaymentScheme;

/**
 * 
 * @author Ishanka
 * all the services regarding to PaymentSchemes are implemented here
 */

@Path("/PaymentScheme")
public class PaymentSchemeService {

	PaymentScheme psObj = new PaymentScheme();
	
	PaymentSchemeBean psBean = new PaymentSchemeBean();
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentSchemes() {
		return psObj.viewAllPaymentSchemes();
	}

	
	
}
