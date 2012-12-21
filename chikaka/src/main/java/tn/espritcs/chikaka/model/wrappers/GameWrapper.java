package tn.espritcs.chikaka.model.wrappers;

import java.util.Date;

import tn.espritcs.chikaka.model.game.Game;

public class GameWrapper {
	private Game game; 
	
	public GameWrapper(Game game){
		this.game = game; 
		this.game.setDate(new Date());
	}
	public GameWrapper(){
		this.game = new Game();
		this.game.setDate(new Date());
	}
	
	public Game toGame(){
		return this.game;
	}
	
	public int getAiPlayersCount() {
		return this.game.getAiPlayersCount();
	}
	
	public int getHumanplayersCount() {
		return this.game.getHumanplayersCount();
	}
	
	public int getInitialCredit() {
		return this.game.getInitialCredit();
	}
	
	public void setAiPlayersCount(int aiPlayersCount) {
		this.game.setAiPlayersCount(aiPlayersCount);
	}
	
	public void setHumanplayersCount(int humanplayersCount) {
		this.game.setHumanplayersCount(humanplayersCount);
	}
	
	public void setInitialCredit(int initialCredit) {
		this.game.setInitialCredit(initialCredit);
	}


}
