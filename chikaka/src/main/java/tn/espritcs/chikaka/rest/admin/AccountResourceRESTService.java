package tn.espritcs.chikaka.rest.admin;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import tn.espritcs.chikaka.model.wrappers.AccountWrapper;
import tn.espritcs.chikaka.service.AccountServices;

@Path("/admin/accounts")
@RequestScoped
public class AccountResourceRESTService {
	@Context
    private SecurityContext securityContext;
	
    @Inject
    private AccountServices accountServices;

   @GET
   @RolesAllowed({"Admin"})
   @Produces(MediaType.APPLICATION_JSON)
   public List<AccountWrapper> listAllAccounts() {
      return accountServices.listAllAccounts();
   }
  
   @GET
   @RolesAllowed({"Admin"})
   @Path("/emails/{email:.*}")
   @Produces(MediaType.APPLICATION_JSON)
   public AccountWrapper lookupAccountByEmail(@PathParam("email") String email){
	   return accountServices.lookupAccountByEmail(email);
   }
   
   @POST
   @Path("/create/user/single/")
   @RolesAllowed({"Admin", "Guest"})
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewSingleAccount(AccountWrapper account){
	   Response.ResponseBuilder builder = null;
	   String password = account.retriveDispatcherCriteria();
       if(accountServices.register(account, password)){
    	   builder = Response.status(Response.Status.CREATED);
       }else{
    	   builder = Response.status(Response.Status.BAD_REQUEST);
       }
	   return builder.build();
   }
   
   @POST
   @Path("/create/admin/single/")
   @RolesAllowed({"Admin"})
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewSingleAdminAccount(AccountWrapper account){
	   Response.ResponseBuilder builder = null;
	   String password = account.retriveDispatcherCriteria();
       if(accountServices.register(account, password, true)){
    	   builder = Response.status(Response.Status.CREATED);
       }else{
    	   builder = Response.status(Response.Status.BAD_REQUEST);
       }
	   return builder.build();
   }
   
   @PUT
   @RolesAllowed({"Admin", "User"})
   @Path("/update/single/username/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateSingleAccountByUserName(AccountWrapper account){
	   Response.ResponseBuilder builder = null;
	   String oldUserName = account.retriveDispatcherCriteria();
	   if(securityContext.isUserInRole("User")){
		   if(!oldUserName.equals(securityContext.getUserPrincipal().getName())){
			   builder = Response.status(Response.Status.FORBIDDEN);
	   }}else if(accountServices.updateByUserName(account, oldUserName)){
    	   builder = Response.ok();        	   
       }else{
    	   builder = Response.status(Response.Status.BAD_REQUEST);
       }
	   return builder.build();
   }
   
   @PUT
   @RolesAllowed({"Admin", "User"})
   @Path("/update/single/email/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateSingleAccountByEmail(AccountWrapper account){
	   Response.ResponseBuilder builder = null;
	   String oldEmail = account.retriveDispatcherCriteria();
	   if(securityContext.isUserInRole("User")){
		   String emailOwner = accountServices.lookupAccountByEmail(oldEmail).getUserName();
		   if(!emailOwner.equals(securityContext.getUserPrincipal().getName())){
			   builder = Response.status(Response.Status.FORBIDDEN);
	   }}else
       if(accountServices.updateByEmail(account, oldEmail)){
    	   builder = Response.ok();        	   
       }else{
    	   builder = Response.status(Response.Status.BAD_REQUEST);
       }
	   return builder.build();
   }
}
