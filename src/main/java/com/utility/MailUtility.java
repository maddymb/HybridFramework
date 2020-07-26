package com.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MailUtility {

	public static void main(String args[]) throws EmailException {
		// Create the attachment
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("/Users/maddy/eclipse-workspace/HybridFrameworkSuper/src/main/java/com/reports/Report_13_03_2018_09_21_15.html");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Picture of John");
		  attachment.setName("John");

		

		 

		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.googlemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("madhurbhrdwj3@gmail.com", ".mm18akgm"));
		  email.setSSLOnConnect(true);
		  email.setFrom("madhurbhrdwj3@gmail.com");
		  email.setSubject("TestMail");
		  email.setMsg("This is a test mail ... :-)");
		  email.addTo("madhurbhrdwaj@gmail.com");
		  

		  // add the attachment
		  email.attach(attachment);
		  
		  // send the email
		  email.send();
		  System.out.println("sent");
	
	}
}
