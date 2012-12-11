package tn.espritcs.chikaka.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SessionPowerUp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "session", insertable=false, updatable=false)
	private Session sessions;

	@ManyToOne
	@JoinColumn(name = "powerup", insertable=false, updatable=false)
	private PowerUp powerup;

	public SessionPowerUp() {}

	public int      getId      () {return id      ;}
	public PowerUp  getPowerup () {return powerup ;}
	public Session getSessions() {return sessions;}
	
	public void setId     (int     id       ) {this.id       = id      ;}
	public void setPowerup(PowerUp powerup  ) {this.powerup  = powerup ;}
	public void setSession(Session sessions) {this.sessions = sessions;}
}