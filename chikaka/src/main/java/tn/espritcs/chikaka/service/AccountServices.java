package tn.espritcs.chikaka.service;

import tn.espritcs.chikaka.model.game.Account;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private Event<Account> memberEventSrc;

	public void register(Account account) throws Exception {
		em.persist(account);
		memberEventSrc.fire(account);
	}

	public void update(Account account) throws Exception {
		Account oldAccount = em.find(Account.class, account.getId());
		
		if(oldAccount == null){
			throw new Exception("Request object not found in the database");
		}
		
		oldAccount.setUser     (account.getUser     ());
		oldAccount.setEmail    (account.getEmail    ());
		oldAccount.setAvatar   (account.getAvatar   ());
		oldAccount.setLastName (account.getLastName ());
		oldAccount.setFirstName(account.getFirstName());
		
		em.persist(oldAccount);
	}
}
