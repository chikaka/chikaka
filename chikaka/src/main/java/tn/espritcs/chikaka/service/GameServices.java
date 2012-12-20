package tn.espritcs.chikaka.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 
import tn.espritcs.chikaka.model.game.Game;
import tn.espritcs.chikaka.model.wrappers.GameWrapper;



@Stateless
public class GameServices {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	public List<GameWrapper> listAllGames(){
		@SuppressWarnings("unchecked")
		final List<Game> games = em.createQuery("select g from Game g").getResultList();
		List<GameWrapper> results = new ArrayList<GameWrapper>(); 
		for(Game game: games){
			results.add(new GameWrapper(game));
		}
		return results;
	} 
}
