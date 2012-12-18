package tn.espritcs.chikaka.controller;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.service.AccountServices;

@Model
public class AccountController {
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
      initNewAccount();
   }

   @PostConstruct
   public void initNewAccount() {
	   newAccount = new Account();
   }
}
