package tn.espritcs.chikaka.model.authentification;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
public class SystemRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull private String name;

	public int             getId   () {return id   ;}
	public String          getName () {return name ;}
	
	public void setId   (int             id   ) {this.id    = id   ;}
	public void setName (String          name ) {this.name  = name ;}
}
