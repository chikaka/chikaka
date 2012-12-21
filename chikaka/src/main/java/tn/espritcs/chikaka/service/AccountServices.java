package tn.espritcs.chikaka.service;

import java.util.ArrayList;
import java.util.List;

import tn.espritcs.chikaka.model.authentification.SystemRole;
import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.model.wrappers.AccountWrapper;
import tn.espritcs.chikaka.util.StatusMessage;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject; 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AccountServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private Event<Account> accountEventSrc;
	
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

	public StatusMessage register(AccountWrapper account, String password){
		return register(account, password, false);
	}
	
	public StatusMessage register(AccountWrapper account, String password, boolean isAdmin){
		try{
			Account newAcccount = account.toAccount();
			newAcccount.getUser().changePassword(password);
			if(isAdmin){
				SystemRole adminRole = systemRoleServices.getRoleByName("Admin");
				newAcccount.getUser().setRole(adminRole);
			}
			return register(newAcccount);
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	}
	
	public StatusMessage register(Account account){
		try{
			em.persist(account.getUser());
			em.persist(account);
			accountEventSrc.fire(account);
			return new StatusMessage(true, "Created");
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
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
	
	public StatusMessage isEmailAvailable(String email) throws Exception{
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.email = :email");
		query.setParameter("email", email);
		try{
			query.getSingleResult();
			return new StatusMessage(false, "The email address is already used");
		}catch(NoResultException e){
			return new StatusMessage(true, "Email address available");
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	}
	
	public StatusMessage isUserNameAvailable(String userName) throws Exception{
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.user.userName = :userName");
		query.setParameter("userName", userName);
		try{
			query.getSingleResult();
			return new StatusMessage(false, "The userName address is already used");
		}catch(NoResultException e){
			return new StatusMessage(true, "UserName address available");
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	}
	
	public StatusMessage isPasswordNotSound(String password){
		final int    MIN_PASSWORD_LENGTH   = 8;
		final int    MAX_PASSWORD_LENGTH   = 20;
		final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
		
		
		
		if (password.isEmpty()) {
			return new StatusMessage(true, "Password is empty");
		}
		
	    password = password.trim  ();
	    int len  = password.length();
	    if(len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
	    	return new StatusMessage(true, "Wrong size, it must have at least 8 characters and less than 20.");
	    }
	    
	    boolean upper = false, lower = false, digit = false, special = false;
	    
	    for(char c : password.toCharArray()) {
	        if      (!upper && Character.isUpperCase(c)) { upper = true;} 
	        else if (!lower && Character.isLowerCase(c)) { lower = true;}
	        else if (!digit && Character.isDigit    (c)) { digit = true;}
	        else if (!special && SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) { special = true; }
	        else {
	        	return new StatusMessage(true, "Invalid character ("+c+")");
	        }
	    }
	    
	    
	    if(!upper)  {return new StatusMessage(true, "No upper case character present");}
	    if(!lower)  {return new StatusMessage(true, "No lower case character present");}
	    if(!digit)  {return new StatusMessage(true, "No digit character present"     );}
	    if(!special){return new StatusMessage(true, "No special character present"   );}
	    
	    return new StatusMessage(false, "Password accepted");
	}
}
