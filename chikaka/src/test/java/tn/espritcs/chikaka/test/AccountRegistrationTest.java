package tn.espritcs.chikaka.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import tn.espritcs.chikaka.model.Account;
import tn.espritcs.chikaka.model.Game;
import tn.espritcs.chikaka.model.GameRule;
import tn.espritcs.chikaka.model.PowerUp;
import tn.espritcs.chikaka.model.Rule;
import tn.espritcs.chikaka.model.Session;
import tn.espritcs.chikaka.model.SessionPowerUp;
import tn.espritcs.chikaka.service.AccountServices;
import tn.espritcs.chikaka.util.Resources;
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
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(
            		Rule               .class,
            		Game               .class, 
            		Account            .class, 
            		PowerUp            .class,
            		Session           .class, 
            		Resources          .class,
            		GameRule          .class,
            		SessionPowerUp   .class,
            		AccountServices.class 
            )
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
   }

   @Inject
   AccountServices accountRegistration;

   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
      Account newAccount = new Account();
      newAccount.setAvatar("default.png");
      newAccount.setEmail("test@test.com");
      newAccount.setFirstName("douda");
      newAccount.setLastName("chadoud");
      newAccount.setLogin("doudo");
      newAccount.setPassword("chikaka");
      accountRegistration.register(newAccount);
      assertNotNull(newAccount.getId());
      log.info(newAccount.getLogin() + " was persisted with id " + newAccount.getId());
   }
   
   @Test(expected = Exception.class)
   public void testInvalidRegistere()throws Exception {
	  Account newAccount1 = new Account();
      newAccount1.setAvatar("default.png");
      newAccount1.setEmail("test1@test.com");
      newAccount1.setFirstName("douda");
      newAccount1.setLastName("chadoud");
      newAccount1.setLogin("doudo1");
      newAccount1.setPassword("chikaka");
      accountRegistration.register(newAccount1);
      assertNotNull(newAccount1.getId());
      try{
          Account newAccount2 = new Account();
          newAccount2.setAvatar("default.png");
          newAccount2.setEmail("test1@test.com");
          newAccount2.setFirstName("douda");
          newAccount2.setLastName("chadoud");
          newAccount2.setLogin("doudo1");
          newAccount2.setPassword("chikaka");
          accountRegistration.register(newAccount2);
          fail("Expected exception");
      }catch(Exception E){
      }
   }
}
