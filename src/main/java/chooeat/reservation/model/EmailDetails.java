package chooeat.reservation.model;

import org.springframework.stereotype.Component;

//Java Program to Illustrate EmailDetails Class
@Component
public class EmailDetails {

	// 收件人
	private String recipient;
	//信件內容
	private String msgBody;
	//信件主旨
	private String subject;
	
	//以下get set

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}