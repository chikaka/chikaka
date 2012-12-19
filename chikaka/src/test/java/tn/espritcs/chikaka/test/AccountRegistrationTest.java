package tn.espritcs.chikaka.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import tn.espritcs.chikaka.model.authentification.SystemUser;
import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.model.utils.Role;
import tn.espritcs.chikaka.service.AccountServices;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AccountRegistrationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, Role.class.getPackage())
				.addPackages(true, Account.class.getPackage())
				.addPackages(true, AccountServices.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	AccountServices accountService;

	@Test
	public void testRegister() throws Exception {
		Account newAccount = initAccount("default.png","test@test.com","douda","chadoud","doudo","chikaka");
		accountService.register(newAccount);
		assertNotNull(newAccount.getId());
	}

	@Test
	public void testInvalidRegistere() {
		try{
			Account newAccount1  = initAccount("default.png","test1@test.com","douda","chadoud","doudo1","chikaka");
			accountService.register(newAccount1);
			assertNotNull(newAccount1.getId());
		}catch(Exception E){
			fail("Not excepting error here !!");
		}
		try {
			Account newAccount2 = initAccount("default.png","test1@test.com","douda","chadoud","doudo1","chikaka");
			accountService.register(newAccount2);
			fail("Expected exception");
		} catch (Exception e) {
		}
	}
	
	private Account initAccount(String avatar, String email, String firstName, String lastName, String login, String password){
		SystemUser user = new SystemUser();
		user.setUserName(login);
		user.changePassword(password);
		Account account = new Account();
		account.setAvatar(avatar);
		account.setEmail(email);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setUser(user);
		return account;
	}
}
