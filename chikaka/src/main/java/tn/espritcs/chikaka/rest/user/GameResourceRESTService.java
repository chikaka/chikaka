package tn.espritcs.chikaka.rest.user;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext; 
import javax.annotation.security.RolesAllowed;

import tn.espritcs.chikaka.model.wrappers.GameWrapper;
import tn.espritcs.chikaka.service.GameServices;

@Path("/user/games")
@RequestScoped
public class GameResourceRESTService {
	@Context
	private SecurityContext securityContext;
	
	@Inject
	private GameServices gameServices;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"User"})
	public Response listAllGames() { 
		List<GameWrapper> games =  gameServices.listAllGames();
		ResponseBuilder responseBuilder = null;
		if(games.isEmpty()){
			responseBuilder = Response.status(Status.NOT_FOUND);
			responseBuilder.entity("there is no games in progress");
		}else {
			responseBuilder = Response.ok(games, MediaType.APPLICATION_JSON);
		} 
		return responseBuilder.build(); 
	}

}
