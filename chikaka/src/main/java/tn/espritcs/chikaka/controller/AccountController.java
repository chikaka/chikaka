package tn.espritcs.chikaka.controller;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.service.AccountServices;

@Model
public class AccountController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private AccountServices accountRegistration;

   private Account newAccount;

   @Produces
   @Named
   public Account getNewAccount() {
      return newAccount;
   }

   public void register() throws Exception {
	  accountRegistration.register(newAccount);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewAccount();
   }

   @PostConstruct
   public void initNewAccount() {
	   newAccount = new Account();
   }
}
