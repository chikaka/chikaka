package tn.espritcs.chikaka.service;

import tn.espritcs.chikaka.model.Account;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class AccountServices {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Account> memberEventSrc;

	public void register(Account account) throws Exception {
		log.info("Registering " + account.getLogin());
		em.persist(account);
		memberEventSrc.fire(account);
	}

	public void update(Account account) throws Exception {
		log.info("Updating " + account.getId());
		Account oldAccount = em.find(Account.class, account.getId());
		
		if(oldAccount == null){
			throw new Exception("Request object not found in the database");
		}
		
		oldAccount.setEmail    (account.getEmail    ());
		oldAccount.setLogin    (account.getLogin    ());
		oldAccount.setAvatar   (account.getAvatar   ());
		oldAccount.setLastName (account.getLastName ());
		oldAccount.setPassword (account.getPassword ());
		oldAccount.setFirstName(account.getFirstName());
		
		em.persist(oldAccount);
	}
}
