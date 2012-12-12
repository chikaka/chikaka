package tn.espritcs.chikaka.rest.guest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/guest/hello")
@RequestScoped
public class GuestResourceRESTService {
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response hello() {
	   Response.ResponseBuilder builder = Response.ok().entity("Chikaka");
	   return builder.build();
   }
}
