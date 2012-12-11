package tn.espritcs.chikaka.model;

import java.util.Set       ;
import javax.persistence.* ;

import tn.espritcs.chikaka.model.utils.Role;

import java.io.Serializable;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"account", "game"})})
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Role    role  ;
	private int     score ;
	private boolean winner;

	@ManyToOne
	@JoinColumn(name = "account", insertable=false, updatable=false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "game", insertable=false, updatable=false)
	private Game game;

	@OneToMany(mappedBy = "sessions")
	private Set<SessionPowerUp> sessionsPowerUps;

	public Session() {}

	public int                 getId              () {return id              ;}
	public Role                getRole            () {return role            ;}
	public Game                getGame            () {return game            ;}
	public int                 getScore           () {return score           ;}
	public boolean             isWinner           () {return winner          ;}
	public Account             getAccount         () {return account         ;}
	public Set<SessionPowerUp> getSessionsPowerUps() {return sessionsPowerUps;}
	
	public void setId              (int                   id            ) {this.id               = id              ;}
	public void setRole            (Role                  role          ) {this.role             = role            ;}
	public void setGame            (Game                  game          ) {this.game             = game            ;}
	public void setScore           (int                   score         ) {this.score            = score           ;}
	public void setWinner          (boolean               winner        ) {this.winner           = winner          ;}
	public void setAccount         (Account               account       ) {this.account          = account         ;}
	public void setSessionsPowerUps(Set<SessionPowerUp> sessionsPowerUps) {this.sessionsPowerUps = sessionsPowerUps;}
}