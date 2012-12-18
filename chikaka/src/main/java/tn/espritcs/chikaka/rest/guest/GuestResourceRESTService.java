package tn.espritcs.chikaka.rest.guest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.annotation.security.PermitAll;

@Path("/guest/hello")
@RequestScoped
public class GuestResourceRESTService {
	@Context
	javax.security.auth.login.LoginContext lcontext;
	
	@Context
    private SecurityContext securityContext;
	
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @PermitAll
   public Response hello() {
	   boolean s = securityContext.isUserInRole("1");
	   Response.ResponseBuilder builder = Response.ok().entity("Chikakaka to " + securityContext.getUserPrincipal().getName()+ " => "+ s);
	   return builder.build();
   }
}
