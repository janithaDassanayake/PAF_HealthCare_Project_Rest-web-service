package com.DoctorManagementMaven.DoctorManagementMaven;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("DoctorService")
public class DoctorService {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Me thiyenne Doctor Service eka";
    }
}
