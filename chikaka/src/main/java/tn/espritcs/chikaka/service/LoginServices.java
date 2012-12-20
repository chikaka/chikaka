package tn.espritcs.chikaka.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.model.game.Session;
import tn.espritcs.chikaka.model.wrappers.AccountWrapper;
import tn.espritcs.chikaka.util.StatusMessage;

@Stateless
public class LoginServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private AccountServices accountServices;
	
	public StatusMessage login(AccountWrapper account){
		try{
			Account userAccount = accountServices.lookupAccountByUserName(account.getUserName()).toAccount();
			Session activeSession = userAccount.getActiveSession();
			if(activeSession == null){
				activeSession = new Session();
				activeSession.setAccount(userAccount);
				activeSession.setActive(true);
				em.persist(activeSession);
				return new StatusMessage(true, "Logged in");
			}else{
				return new StatusMessage(false, "Already logged in");
			}
		}catch(Exception  e){
			return new StatusMessage(false, e.getMessage());
		}
	}
		
}
