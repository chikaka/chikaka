package tn.espritcs.chikaka.model.authentification;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

@Entity
public class SystemUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull private String userName;
	@NotNull private String password;
	
	@ManyToOne
	@JoinColumn(name = "role")
	private SystemRole role;
	
	public int    getId      () {return id      ;}
	public SystemRole   getRole    () {return role    ;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	
	public void setId      (int    id      ) {this.id       = id      ;}
	public void setRole    (SystemRole   role    ) {this.role     = role    ;}
	public void setUserName(String userName) {this.userName = userName;}
	public void setPassword(String password) {this.password = password;}
}
