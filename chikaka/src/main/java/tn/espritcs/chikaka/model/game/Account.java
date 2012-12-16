package tn.espritcs.chikaka.model.game;

import java.util.Set                       ;
import javax.persistence.*                 ;
import java.io.Serializable                ;
import javax.validation.constraints.NotNull;

import tn.espritcs.chikaka.model.authentification.SystemUser;

@Entity
@SuppressWarnings("serial")
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull @Column(length=50, unique=true)private String email    ;
	@NotNull @Column(length=50             )private String avatar   ;
	@NotNull @Column(length=50             )private String lastName ;
	@NotNull @Column(length=50             )private String firstName;

	@NotNull @OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
	private Set<Session> sessions;
	
	@OneToOne
	private SystemUser user;

	public Account() {}

	public int          getId       () {return id       ;}
	public SystemUser   getUser     () {return user     ;}
	public String       getEmail    () {return email    ;}
	public String       getAvatar   () {return avatar   ;}
	public String       getLastName () {return lastName ;}
	public Set<Session> getSessions () {return sessions ;}
	public String       getFirstName() {return firstName;}

	public void setId       (int          id       ) {this.id        = id       ;}
	public void setUser     (SystemUser   user     ) {this.user      = user     ;}
	public void setEmail    (String       email    ) {this.email     = email    ;}
	public void setAvatar   (String       avatar   ) {this.avatar    = avatar   ;}
	public void setLastName (String       lastName ) {this.lastName  = lastName ;}
	public void setSessions (Set<Session> sessions ) {this.sessions  = sessions ;}
	public void setFirstName(String       firstName) {this.firstName = firstName;}
}