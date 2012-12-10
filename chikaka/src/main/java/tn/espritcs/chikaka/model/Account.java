package tn.espritcs.chikaka.model;

import java.util.Set                       ;
import javax.persistence.*                 ;
import java.io.Serializable                ;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="\"Account\"")
@SuppressWarnings("serial")
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull @Column(name="\"email\""    ,length=50, unique=true)private String email    ;
	@NotNull @Column(name="\"login\""    ,length=50, unique=true)private String login    ;
	@NotNull @Column(name="\"avatar\""   ,length=50             )private String avatar   ;
	@NotNull @Column(name="\"password\"" ,length=50             )private String password ;
	@NotNull @Column(name="\"lastName\"" ,length=50             )private String lastName ;
	@NotNull @Column(name="\"firstName\"",length=50             )private String firstName;

	@OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
	private Set<Session> sessions;

	public Account() {}

	public int          getId       () {return id       ;}
	public String       getEmail    () {return email    ;}
	public String       getLogin    () {return login    ;}
	public String       getAvatar   () {return avatar   ;}
	public String       getPassword () {return password ;}
	public String       getLastName () {return lastName ;}
	public Set<Session> getSessions () {return sessions ;}
	public String       getFirstName() {return firstName;}

	public void setId       (int          id       ) {this.id        = id       ;}
	public void setEmail    (String       email    ) {this.email     = email    ;}
	public void setLogin    (String       login    ) {this.login     = login    ;}
	public void setAvatar   (String       avatar   ) {this.avatar    = avatar   ;}
	public void setPassword (String       password ) {this.password  = password ;}
	public void setLastName (String       lastName ) {this.lastName  = lastName ;}
	public void setSessions (Set<Session> sessions ) {this.sessions  = sessions ;}
	public void setFirstName(String       firstName) {this.firstName = firstName;}
}