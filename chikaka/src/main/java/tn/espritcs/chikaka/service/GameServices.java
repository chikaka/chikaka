package tn.espritcs.chikaka.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

import tn.espritcs.chikaka.model.game.Account;
import tn.espritcs.chikaka.model.game.Game;
import tn.espritcs.chikaka.model.game.Session;
import tn.espritcs.chikaka.model.wrappers.GameWrapper;
import tn.espritcs.chikaka.util.StatusMessage;

@Stateless
public class GameServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private AccountServices accountServices;
	
	public List<GameWrapper> listAllGames(){
		@SuppressWarnings("unchecked")
		final List<Game> games = em.createQuery("select g from Game g").getResultList();
		List<GameWrapper> results = new ArrayList<GameWrapper>(); 
		for(Game game: games){
			results.add(new GameWrapper(game));
		}
		return results;
	}

	public StatusMessage createGame(GameWrapper game, String userName) {
		try{
			Account account = accountServices.lookupAccountByUserName(userName).toAccount();
			Session session = account.getActiveSession();
			Game newGame = game.toGame();
			em.persist(newGame);
			session.setGame(newGame);
			em.persist(session);
			return new StatusMessage(true, "Created");
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	}

	public StatusMessage logout(String userName) {
		try{
			Account account = accountServices.lookupAccountByUserName(userName).toAccount();
			Session session = account.getActiveSession();
			//session.setActive(false);
			//em.persist(session);
			return new StatusMessage(true, "Lgged out");
		}catch(Exception e){
			return new StatusMessage(false, e.getMessage());
		}
	} 
}
