package tn.espritcs.chikaka.model.game;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class SessionPowerUp implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "session", insertable=false, updatable=false)
	private Session session;

	@ManyToOne
	@JoinColumn(name = "powerup", insertable=false, updatable=false)
	private PowerUp powerup;

	public SessionPowerUp() {}

	public int     getId     () {return id     ;}
	public PowerUp getPowerup() {return powerup;}
	public Session getSession() {return session;}
	
	public void setId     (int     id     ) {this.id      = id     ;}
	public void setPowerup(PowerUp powerup) {this.powerup = powerup;}
	public void setSession(Session session) {this.session = session;}
}