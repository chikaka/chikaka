package tn.espritcs.chikaka.util;

public class StatusMessage {
	private boolean status;
	private String  message;
	
	public StatusMessage(boolean status, String message){
		this.status  = status ;
		this.message = message;
	}
	
	public boolean getStatus () {return status ;}
	public String  getMessage() {return message;}
	
	public void setStatus (boolean status ) {this.status   = status ;}
	public void setMessage(String  message) {this.message  = message;}
	public void addMessage(String  message) {this.message += message;}
}
