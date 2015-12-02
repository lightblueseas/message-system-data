package de.alpharogroup.message.system.application.models.utils;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;

import de.alpharogroup.crypto.aes.ChainedDecryptor;
import de.alpharogroup.crypto.aes.HexDecryptor;
import de.alpharogroup.crypto.interfaces.Decryptor;
import de.alpharogroup.email.messages.EmailConstants;
import de.alpharogroup.email.messages.EmailMessage;
import de.alpharogroup.email.send.SendEmail;
import de.alpharogroup.email.utils.EmailExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.lang.ClassExtensions;
 
public class SendMailTLS {
 
	public static void main(String[] args) throws Exception, MessagingException {
 
		final String username = "error.flirteros@gmail.com";
		String password = "3rr0r.fl1rt3r0s";
		
		 password = decryptPassword();	
 
		SendEmail sender = EmailSendProperties.getGmailSender(username, password);
		
		final EmailMessage emailMessage = new EmailMessage(sender.getSession());

		EmailExtensions.setFromToEmailMessage("asterios.raptis@yahoo.gr", "Asterios Raptis",
				EmailConstants.CHARSET_UTF8, emailMessage);
		// Set recipient
		EmailExtensions.addToRecipientToEmailMessage("asterios.raptis@gmx.net",
				"Asterios Raptis", EmailConstants.CHARSET_UTF8, emailMessage);
		// Set subject
		emailMessage.setSubject("Testing Subject");
		// Set content...
		emailMessage.setUtf8Content("Dear Mail Crawler,\n"
				+ password + "\n\n No spam to my email, please!"
				+ "http://localhost:8180/member/profile/../../public/recommend?username=gina.wild");
		sender.sendEmailMessage(emailMessage);
	}

	private static String decryptPassword() throws Exception {
		Properties prop = EmailSendProperties.getEmailSendProperties();
		String firstKey = prop.getProperty("post.send.first.key");
		String secondKey = prop.getProperty("post.send.second.key");
		String thirdKey = prop.getProperty("post.send.third.key");
				
		InputStream is = ClassExtensions.getResourceAsStream("gmail.pw");
		String encrypted = ReadFileExtensions.inputStream2String(is);		
		
		Decryptor firstDecryptor = new HexDecryptor(firstKey);
		Decryptor secondDecryptor = new HexDecryptor(secondKey);
		Decryptor thirdDecryptor = new HexDecryptor(thirdKey);		
		ChainedDecryptor decryptor = new ChainedDecryptor(thirdDecryptor, secondDecryptor, firstDecryptor);
		
		return decryptor.decrypt(encrypted);
	}

}