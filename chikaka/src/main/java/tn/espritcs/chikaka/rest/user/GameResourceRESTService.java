package tn.espritcs.chikaka.rest.user;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import tn.espritcs.chikaka.util.StatusMessage;

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
		ResponseBuilder response = null;
		List<GameWrapper> games =  gameServices.listAllGames();
		if(games.isEmpty()){
			response = Response.status(Status.NOT_FOUND);
			response.entity("there is no games in progress");
		}else {
			response = Response.ok(games, MediaType.APPLICATION_JSON);
		} 
		return response.build(); 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"User"})
	public Response createGame(GameWrapper game){
		ResponseBuilder response = null;
		String userName = securityContext.getUserPrincipal().getName();
		StatusMessage status = gameServices.createGame(game, userName);
		if(status.getStatus()){
			response = Response.status(Status.CREATED);
		}else{
			response = Response.status(Status.BAD_REQUEST);
		}
		response.entity(status.getMessage());
		return response.build();
	}
}
