package tn.espritcs.chikaka.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="\"SessionPowerUp\"")
public class SessionPowerUp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "session")
	private Session sessions;

	@ManyToOne
	@JoinColumn(name = "powerup")
	private PowerUp powerup;

	public SessionPowerUp() {}

	public int      getId      () {return id      ;}
	public PowerUp  getPowerup () {return powerup ;}
	public Session getSessions() {return sessions;}
	
	public void setId     (int     id       ) {this.id       = id      ;}
	public void setPowerup(PowerUp powerup  ) {this.powerup  = powerup ;}
	public void setSession(Session sessions) {this.sessions = sessions;}
}