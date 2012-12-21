package tn.espritcs.chikaka.model.game;

import java.util.List;
import java.util.Set       ;
import java.util.Date      ;
import javax.persistence.* ;

import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class Game implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date             ;
	private int  initialCredit    ;
	private int  aiPlayersCount   ;
	private int  humanplayersCount;

	@OneToMany(mappedBy = "game")
	@Column(nullable=true)
	private List<Session> sessions;

	@OneToMany(mappedBy = "game")
 	@Column(nullable=true)
	private Set<GameRule> rules;

	public Game() {}

	public int           getId               () {return id               ;}
	public Date          getDate             () {return date             ;}
	public Set<GameRule> getRules            () {return rules            ;}
	public List<Session> getSessions         () {return sessions         ;}
	public int           getInitialCredit    () {return initialCredit    ;}
	public int           getAiPlayersCount   () {return aiPlayersCount   ;}
	public int           getHumanplayersCount() {return humanplayersCount;}
	
	public void setId               (int           id               ) {this.id                = id               ;}
	public void setDate             (Date          date             ) {this.date              = date             ;}
	public void setRules            (Set<GameRule> rules            ) {this.rules             = rules            ;}
	public void setSessions         (List<Session> sessions         ) {this.sessions          = sessions         ;}
	public void setInitialCredit    (int           initialCredit    ) {this.initialCredit     = initialCredit    ;}
	public void setAiPlayersCount   (int           aiPlayersCount   ) {this.aiPlayersCount    = aiPlayersCount   ;}
	public void setHumanplayersCount(int           humanplayersCount) {this.humanplayersCount = humanplayersCount;}
}