package tn.espritcs.chikaka.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.espritcs.chikaka.model.wrappers.AccountWrapper;
import tn.espritcs.chikaka.util.StatusMessage;

@Stateless
public class GuestServices {
	@Inject AccountServices accountServices;
	
	public StatusMessage signUp(AccountWrapper account){
		StatusMessage status = new StatusMessage(false, "KO");
		try{
			status = accountServices.isEmailAvailable(account.getEmail());
			if(!status.getStatus())  {return status;}
			status = accountServices.isUserNameAvailable(account.getUserName());
			if(!status.getStatus())  {return status;}
			status = accountServices.isPasswordNotSound(account.retriveDispatcherCriteria());
			if(!status.getStatus()) {return status;}
			status = accountServices.register(account, account.retriveDispatcherCriteria());
			return status;
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	}
}
