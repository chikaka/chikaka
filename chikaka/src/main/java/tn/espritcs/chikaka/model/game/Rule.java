package tn.espritcs.chikaka.model.game;

import java.util.Set                       ;
import javax.persistence.*                 ;

import java.io.Serializable                ;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("serial")
public class Rule implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull @Column(length=100)private String label      ;
	@NotNull @Column(length=100)private String graphic    ;
	@NotNull @Column(length=100)private String description;

	@OneToMany(mappedBy = "rule")
	private Set<GameRule> gameRules;

	public Rule() {}

	public int           getId         () {return id         ;}
	public String        getLabel      () {return label      ;}
	public String        getGraphic    () {return graphic    ;}
	public Set<GameRule> getGameRules  () {return gameRules  ;}
	public String        getDescription() {return description;}
	
	public void setId         (int           id         ) {this.id          = id         ;}
	public void setLabel      (String        label      ) {this.label       = label      ;}
	public void setGraphic    (String        graphic    ) {this.graphic     = graphic    ;}
	public void setGameRules  (Set<GameRule> gameRules  ) {this.gameRules   = gameRules  ;}
	public void setDescription(String        description) {this.description = description;}
}