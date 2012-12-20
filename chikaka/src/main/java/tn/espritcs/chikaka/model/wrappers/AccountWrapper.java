package tn.espritcs.chikaka.model.wrappers;

import javax.inject.Inject;

import tn.espritcs.chikaka.model.authentification.SystemRole;
import tn.espritcs.chikaka.model.authentification.SystemUser;
import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.service.SystemRoleServices;

public class AccountWrapper{
	@Inject
	SystemRoleServices systemRoleServices;
	
	private Account account;
	private String dispatcherCriteria;
	
	public AccountWrapper(){
		account = new Account();
		this.account.setUser(new SystemUser());
	}
	
	public AccountWrapper(Account account){
		this.account = account;
	}
	
	public AccountWrapper(String userName, String firstName, String lastName, String email, String avatar) throws Exception{
		account = new Account();
		account.setEmail    (email    );
		account.setAvatar   (avatar   );
		account.setLastName (lastName );
		account.setFirstName(firstName);
		
		SystemUser user = new SystemUser();
		user.setUserName   (userName);
		try{
			SystemRole userRole = systemRoleServices.getRoleByName("User");
			user.setRole(userRole);
		}catch(Exception e){
			throw new Exception("Failed to acquire the User role");
		}
		account.setUser(user);
	}
	
	public String getEmail             (){return this.account.getEmail             ();}
	public String getAvatar            (){return this.account.getAvatar            ();}
	public String getUserName          (){return this.account.getUser().getUserName();}
	public String getLastName          (){return this.account.getLastName          ();}
	public String getFirstName         (){return this.account.getFirstName         ();}
	
	public String retriveDispatcherCriteria(){return this.dispatcherCriteria             ;}
	
	public void setEmail             (String email             ){this.account.setEmail             (email    );}
	public void setAvatar            (String avatar            ){this.account.setAvatar            (avatar   );}
	public void setUserName          (String userName          ){this.account.getUser().setUserName(userName );}
	public void setLastName          (String lastName          ){this.account.setLastName          (lastName );}
	public void setFirstName         (String firstName         ){this.account.setFirstName         (firstName);}
	public void setDispatcherCriteria(String dispatcherCriteria){this.dispatcherCriteria = dispatcherCriteria ;}
	
	public Account toAccount(){return this.account;}	
}
