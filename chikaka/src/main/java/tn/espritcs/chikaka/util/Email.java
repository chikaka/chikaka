package tn.espritcs.chikaka.util;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@SessionScoped
public class Email implements Serializable {

	private static final long serialVersionUID = 1544680932114626710L;

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session session;

	private String to;

	private String from;

	private String subject;

	private String body;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void send() throws Exception {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		Address toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		message.setSubject(subject);
		message.setContent(body, "text/plain");
		Transport.send(message);
	}
}
