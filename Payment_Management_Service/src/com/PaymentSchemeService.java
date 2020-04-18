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

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPaymentScheme(String psData) {
		
		// Convert the input string to a JSON object
				JsonObject jsonObj = new JsonParser().parse(psData).getAsJsonObject();
				
				psBean.setDoc_id(jsonObj.get("doc_id").getAsInt());
				psBean.setHospital_id(jsonObj.get("hospital_id").getAsInt());
				psBean.setDoc_charge(jsonObj.get("doc_charge").getAsDouble());
				psBean.setHosp_charge(jsonObj.get("hosp_charge").getAsDouble());
				psBean.setTax(jsonObj.get("tax").getAsDouble());
				
				String output = psObj.insertPaymentScheme(psBean);
				
				
				return output;
		
		
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymentScheme(String psData) {
		String output = "";
		
		JsonObject doc = new JsonParser().parse(psData).getAsJsonObject();
		
		PaymentSchemeBean psBean = new PaymentSchemeBean();
		
		psBean.setId(doc.get("id").getAsInt());
		
		output = psObj.deletePayementScheme(psBean);
			return output;
	}
	

	
	
}
