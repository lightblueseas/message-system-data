package de.alpharogroup.message.system.subethamail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

public class WiserStarter {

	@SuppressWarnings("unused")
	public static void main(final String[] args) throws MessagingException {
		final Wiser wiser = new Wiser();

		wiser.setPort(25); // Default is 25

		wiser.start();

		for (final WiserMessage message : wiser.getMessages()) {
			final String envelopeSender = message.getEnvelopeSender();
			final String envelopeReceiver = message.getEnvelopeReceiver();

			final MimeMessage mess = message.getMimeMessage();

			// now do something fun!
			System.out.println(mess.toString());
		}
	}

}
