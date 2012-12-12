package tn.espritcs.chikaka.model.game;

import javax.persistence.*                 ;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.List;

@Entity
@SuppressWarnings("serial")
public class PowerUp implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull private int    cost       ;
	@NotNull private String label      ;
	@NotNull private String graphic    ;
	@NotNull private String description;

	@OneToMany(mappedBy = "powerup")
	private List<SessionPowerUp> sessionsPowerUps;

	public PowerUp() {}

	public int                    getId              () {return id            ;}
	public int                    getCost            () {return cost          ;}
	public String                 getLabel           () {return label         ;}
	public String                 getGraphic         () {return graphic       ;}
	public String                 getDescription     () {return description   ;}
	public List<SessionPowerUp> getSessionsPowerUps() {return sessionsPowerUps;}
	
	public void setId              (int                    id              ) {this.id               = id              ;}
	public void setCost            (int                    cost            ) {this.cost             = cost            ;}
	public void setLabel           (String                 label           ) {this.label            = label           ;}
	public void setGraphic         (String                 graphic         ) {this.graphic          = graphic         ;}
	public void setDescription     (String                 description     ) {this.description      = description     ;}
	public void setSessionsPowerUps(List<SessionPowerUp> sessionsPowerUps) {this.sessionsPowerUps = sessionsPowerUps;}
}