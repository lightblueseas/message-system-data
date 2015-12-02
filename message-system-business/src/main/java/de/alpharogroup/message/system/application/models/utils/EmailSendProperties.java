package de.alpharogroup.message.system.application.models.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import de.alpharogroup.email.send.SendEmail;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;

public final class EmailSendProperties {

	public static Properties getGmailProperties() {
		Properties gmailProperties = new Properties();
		gmailProperties.put("mail.smtp.auth", "true");
		gmailProperties.put("mail.smtp.starttls.enable", "true");
		gmailProperties.put("mail.smtp.host", "smtp.gmail.com");
		gmailProperties.put("mail.smtp.port", "587");
		return gmailProperties;
	}
	
	public static Properties getEmailSendProperties() throws IOException {
		Properties properties = PropertiesExtensions.loadProperties("emailsender.properties");
		return properties;
	}	

	public static SendEmail getGmailSender(final String username, final String password)
			throws IOException, MessagingException {
		Properties properties = EmailSendProperties.getGmailProperties();
		
		SendEmail sender = new SendEmail(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		return sender;		
	}
}
