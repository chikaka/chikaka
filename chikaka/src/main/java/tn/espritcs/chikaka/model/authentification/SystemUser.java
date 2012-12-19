package tn.espritcs.chikaka.model.authentification;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.jboss.security.auth.spi.Util;

@Entity
public class SystemUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50, unique=true, nullable=false)
	private String userName;
	@Column(length=300, nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role")
	private SystemRole role;
	
	public int        getId      () {return id      ;}
	public SystemRole getRole    () {return role    ;}
	public String     getUserName() {return userName;}
	
	public void setId      (int        id      ) {this.id       = id      ;}
	public void setRole    (SystemRole role    ) {this.role     = role    ;}
	public void setUserName(String     userName) {this.userName = userName;}
	
	private String hashPassword(String password){
		return Util.createPasswordHash("SHA-256", "BASE64", null, null, password);
	}
	
	public void changePassword(String password){
		this.password = hashPassword(password);
	}
	
	public boolean checkPassword(String password){
		return hashPassword(password).equals(this.password);
	}
}
