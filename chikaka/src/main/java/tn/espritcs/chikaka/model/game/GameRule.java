package tn.espritcs.chikaka.model.game;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class GameRule implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "game", insertable=false, updatable=false)
	private Game game;

	@ManyToOne
	@JoinColumn(name = "rule", insertable=false, updatable=false)
	private Rule rule;

	public GameRule() {}

	public int  getId  () {return id  ;}
	public Game getGame() {return game;}
	public Rule getRule() {return rule;}
	
	public void setId  (int  id  ) {this.id   = id  ;}
	public void setGame(Game game) {this.game = game;}
	public void setRule(Rule rule) {this.rule = rule;}
}