package tn.espritcs.chikaka.model.authentification;

import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
public class SystemRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull private String name;
	
	@OneToMany(mappedBy = "role")
	private Set<SystemUser> users;

	public int       getId   () {return id   ;}
	public String    getName () {return name ;}
	public Set<SystemUser> getUsers() {return users;}
	
	public void setId   (int       id   ) {this.id    = id   ;}
	public void setName (String    name ) {this.name  = name ;}
	public void setUsers(Set<SystemUser> users) {this.users = users;}
}
