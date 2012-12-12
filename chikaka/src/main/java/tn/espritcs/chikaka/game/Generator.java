package tn.espritcs.chikaka.game;

import java.util.List;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import tn.espritcs.chikaka.model.game.Game;
import tn.espritcs.chikaka.model.game.Session;
import tn.espritcs.chikaka.model.utils.Role;

@SessionScoped
@SuppressWarnings("serial")
public class Generator implements Serializable {
	private int defaultThiefCount   = 2;
	private java.util.Random random = new java.util.Random(System.currentTimeMillis());

	Generator() {}

	public void initRoles(Game game) throws Exception{
		initRoles(game, defaultThiefCount);
	}
	
	public void initRoles(Game game, int thiefCount) throws Exception {
		List<Session> sessions = game.getSessions();
		int sessionSize        = sessions.size();
		
		
		if(thiefCount > sessionSize){
			throw new Exception("We know that you like thiefs, but for this sessions we can only support "+sessionSize);
		}
		
		for(Session session : sessions){
			session.setRole(Role.POLICE);
		}
		
		for(int i=0;i<thiefCount;i++){
			do{
				Session potentielThief = sessions.get(random.nextInt(sessionSize));
				if(potentielThief.getRole() != Role.THIEF){
					potentielThief.setRole(Role.THIEF);
					break;
			}}while(true);
		}	
	}
}