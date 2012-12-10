package tn.espritcs.chikaka.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="\"GameRule\"")
public class GameRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "game")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "rule")
	private Rule rule;

	public GameRule() {}

	public int  getId  () {return id  ;}
	public Game getGame() {return game;}
	public Rule getRule() {return rule;}
	
	public void setId  (int  id  ) {this.id   = id  ;}
	public void setGame(Game game) {this.game = game;}
	public void setRule(Rule rule) {this.rule = rule;}
}