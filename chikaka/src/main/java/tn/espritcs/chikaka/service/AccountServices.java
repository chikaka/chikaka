package tn.espritcs.chikaka.service;

import java.util.ArrayList;
import java.util.List;

import tn.espritcs.chikaka.model.authentification.SystemRole;
import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.model.wrappers.AccountWrapper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AccountServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private Event<Account> memberEventSrc;
	
	@Inject
	SystemRoleServices systemRoleServices;
	
	public List<AccountWrapper> listAllAccounts(){
		@SuppressWarnings("unchecked")
		final List<Account> accounts = em.createQuery("select a from Account a").getResultList();
		List<AccountWrapper> results = new ArrayList<AccountWrapper>();
		for(Account account : accounts){
			results.add(new AccountWrapper(account));
		}
		return results;
	}
	
	public AccountWrapper lookupAccountByEmail(String email){
	   Query query = em.createQuery("SELECT a FROM Account a WHERE a.email = :email");
	   query.setParameter("email", email);
	   return new AccountWrapper((Account) query.getSingleResult());
	}
	
	public AccountWrapper lookupAccountByUserName(String userName){
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.user.userName = :userName");
		query.setParameter("userName", userName);
		return new AccountWrapper((Account) query.getSingleResult());
	}
	
	public AccountWrapper lookupAccountById(Integer id) {
      return new AccountWrapper(em.find(Account.class, id));
   }

	public boolean register(AccountWrapper account, String password){
		return register(account, password, false);
	}
	
	public boolean register(AccountWrapper account, String password, boolean isAdmin){
		try{
			Account newAcccount = account.toAccount();
			newAcccount.getUser().changePassword(password);
			if(isAdmin){
				SystemRole adminRole = systemRoleServices.getRoleByName("Admin");
				newAcccount.getUser().setRole(adminRole);
			}
			return register(newAcccount);
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean register(Account account){
		try{
			em.persist(account);
			memberEventSrc.fire(account);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean updateByUserName(AccountWrapper account, String oldUserName) {
		try{
			Account oldAccount = lookupAccountByUserName(oldUserName).toAccount();
			em.persist(updateAccount(oldAccount, account));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean updateByEmail(AccountWrapper account, String oldEmail) {
		try{
			Account oldAccount = lookupAccountByEmail(oldEmail).toAccount();
			em.persist(updateAccount(oldAccount, account));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	private Account updateAccount(Account oldAccount, AccountWrapper account){
		oldAccount.setEmail    (account.getEmail    ());
		oldAccount.setAvatar   (account.getAvatar   ());
		oldAccount.setLastName (account.getLastName ());
		oldAccount.setFirstName(account.getFirstName());
		
		oldAccount.getUser().setUserName(account.getUserName());
		
		return oldAccount;
	}
}
