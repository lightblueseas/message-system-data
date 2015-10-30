package de.alpharogroup.message.system.subethamail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

public class WiserStarter {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws MessagingException {
		Wiser wiser = new Wiser();

		wiser.setPort(2500); // Default is 25

		wiser.start();

		for (WiserMessage message : wiser.getMessages()) {
			String envelopeSender = message.getEnvelopeSender();
			String envelopeReceiver = message.getEnvelopeReceiver();

			MimeMessage mess = message.getMimeMessage();

			// now do something fun!
			System.out.println(mess.toString());
		}
	}

}
