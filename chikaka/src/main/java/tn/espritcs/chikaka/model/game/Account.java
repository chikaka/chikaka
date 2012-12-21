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

	@OneToMany(mappedBy = "account", cascade=CascadeType.ALL)
	@Column(nullable=true)
	private Set<Session> sessions;
	
	@OneToOne
	private SystemUser user;

	public Account() {}

	public int          getId       () {return id       ;}
	public SystemUser   getUser     () {return user     ;}
	public String       getEmail    () {return email    ;}
	public String       getAvatar   () {return avatar   ;}
	public String       getLastName () {return lastName ;}
	public String       getFirstName() {return firstName;}

	public void setId       (int          id       ) {this.id        = id       ;}
	public void setUser     (SystemUser   user     ) {this.user      = user     ;}
	public void setEmail    (String       email    ) {this.email     = email    ;}
	public void setAvatar   (String       avatar   ) {this.avatar    = avatar   ;}
	public void setLastName (String       lastName ) {this.lastName  = lastName ;}
	public void setFirstName(String       firstName) {this.firstName = firstName;}
	
	public Session getActiveSession() throws Exception{
		Session activeSession  = null;
		int activeSessionCount = 0;
		for(Session active : sessions){
			if(active.isActive()){
				activeSession = active;
				activeSessionCount++;
			}
		}
		if(activeSession!=null){
			if(activeSessionCount > 1){
				throw new Exception("More than one active session");
		}}
		return activeSession;
	}
	
	public Set<Session> listAllSessions(){ return sessions;}
}