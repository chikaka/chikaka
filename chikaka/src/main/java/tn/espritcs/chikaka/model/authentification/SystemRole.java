package tn.espritcs.chikaka.model.authentification;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class SystemRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50, nullable=false, unique=true, updatable=false, insertable=false) 
	private String name;

	public int    getId   () {return id   ;}
	public String getName () {return name ;}
	
	public void setId   (int    id   ) {this.id    = id   ;}
	public void setName (String name ) {this.name  = name ;}
}
