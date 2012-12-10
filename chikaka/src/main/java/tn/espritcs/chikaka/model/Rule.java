package tn.espritcs.chikaka.model;

import java.util.Set                       ;
import javax.persistence.*                 ;
import java.io.Serializable                ;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="\"Rule\"")
public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull @Column(name="\"label\""      , length=100)private String label      ;
	@NotNull @Column(name="\"graphic\""    , length=100)private String graphic    ;
	@NotNull @Column(name="\"description\"", length=100)private String description;

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