package tn.espritcs.chikaka.rest.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.service.AccountServices;

@Path("/admin/accounts")
@RequestScoped
public class AccountResourceRESTService {
   @Inject
   private EntityManager em;
   
   @Inject
   private AccountServices registry;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Account> listAllAccounts() {
      @SuppressWarnings("unchecked")
      final List<Account> results = em.createQuery("select a from Account a").getResultList();
      return results;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces(MediaType.APPLICATION_JSON)
   public Account lookupAccountById(@PathParam("id") Integer id) {
      return em.find(Account.class, id);
   }
   
   @GET
   @Path("/emails/{email:.*}")
   @Produces(MediaType.APPLICATION_JSON)
   public Account lookupAccountByEmail(@PathParam("email") String email){
	   Query query = em.createQuery("SELECT a FROM Account a WHERE a.email = :email");
	   query.setParameter("email", email);
	   return (Account) query.getSingleResult();
   }
   
   @POST
   @Path("/create/single/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewSingleAccount(Account account){
	   Response.ResponseBuilder builder = null;
	   try {
           registry.register(account);
           builder = Response.ok();
       }catch(Exception e){
    	   Map<String, String> responseObj = new HashMap<String, String>();
           responseObj.put("error", e.getMessage());
           builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
       }
	   return builder.build();
   }
   
   @POST
   @Path("/create/multiple/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewMultipleAccount(Account[] accounts){
	   Response.ResponseBuilder builder = null;
	   try {
		   for(Account account : accounts){
			   registry.register(account);
		   }
           builder = Response.ok();
       }catch(Exception e){
    	   Map<String, String> responseObj = new HashMap<String, String>();
           responseObj.put("error", e.getMessage());
           builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
       }
	   return builder.build();
   }
   
   @PUT
   @Path("/update/single/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateSingleAccount(Account account){
	   Response.ResponseBuilder builder = null;
	   try {
           registry.update(account);
           builder = Response.ok();
       }catch(Exception e){
    	   Map<String, String> responseObj = new HashMap<String, String>();
           responseObj.put("error", e.getMessage());
           builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
       }
	   return builder.build();
   }
}
