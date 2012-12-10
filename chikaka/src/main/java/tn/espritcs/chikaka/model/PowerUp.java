package tn.espritcs.chikaka.model;

import javax.persistence.*                 ;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name="\"PowerUp\"")
public class PowerUp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull @Column(name="\"cost\""       )private int    cost       ;
	@NotNull @Column(name="\"label\""      )private String label      ;
	@NotNull @Column(name="\"graphic\""    )private String graphic    ;
	@NotNull @Column(name="\"description\"")private String description;

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