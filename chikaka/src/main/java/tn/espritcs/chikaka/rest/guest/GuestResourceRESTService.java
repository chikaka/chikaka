package tn.espritcs.chikaka.rest.guest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.RolesAllowed;

import tn.espritcs.chikaka.model.wrappers.AccountWrapper;
import tn.espritcs.chikaka.service.GuestServices;
import tn.espritcs.chikaka.util.StatusMessage;
import org.jboss.mx.util.MBeanServerLocator;

@Path("/guest")
@RequestScoped
public class GuestResourceRESTService {
	@Inject
	private GuestServices guestServices;
	
   @POST
   @Path("/signup")
   @RolesAllowed({"Guest"})
   @Consumes(MediaType.APPLICATION_JSON)
   public Response signUp(AccountWrapper account) {
	   Response.ResponseBuilder builder = null;
	   StatusMessage status = guestServices.signUp(account);
	   if(!status.getStatus()){
		   builder = Response.status(Status.BAD_REQUEST);
	   }else{
		   builder = Response.status(Status.CREATED);
	   }
	   builder.entity(status.getMessage());
	   return builder.build();
   }
   
   @GET
   @Path("/test")
   @RolesAllowed({"Guest"})
   public void getMBeanServer(){
	   MBeanServer server = MBeanServerLocator.locateJBoss();
   }
}
