package de.alpharogroup.message.system.service;

import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import de.alpharogroup.email.messages.EmailConstants;
import de.alpharogroup.email.messages.EmailMessage;
import de.alpharogroup.email.send.SendEmail;
import de.alpharogroup.email.utils.EmailExtensions;
import de.alpharogroup.message.system.application.models.InfoMessageModel;
import de.alpharogroup.string.StringUtils;

/**
 * The Class SendMessageBusinessService.
 */
public class SendMessageBusinessService {
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(SendMessageBusinessService.class.getName());

	/**
	 * Send info email.
	 *
	 * @param emailSender the email sender
	 * @param model the model
	 * @throws MessagingException the messaging exception
	 */
	public static void sendInfoEmail(final SendEmail emailSender,
			InfoMessageModel model) throws MessagingException {
		sendInfoEmail(emailSender, model, null);
	}

	/**
	 * Send info email.
	 *
	 * @param emailSender the email sender
	 * @param model the model
	 * @param emailHeaders the email headers
	 * @throws MessagingException the messaging exception
	 */
	public static void sendInfoEmail(final SendEmail emailSender,
			InfoMessageModel model, Map<String, String> emailHeaders) throws MessagingException {
		sendEmail(emailSender, 
				model.getApplicationSenderAddress(), 
				model.getApplicationDomainName(), 
				model.getRecipientEmailContact(), 
				model.getRecipientFullName(), 
				model.getMessageContentModel().getSubject(), 
				model.getMessageContentModel().getContent(), emailHeaders);
	}

	/**
	 * Send email.
	 *
	 * @param emailSender the email sender
	 * @param senderEmail the sender email
	 * @param senderPersonal the sender personal
	 * @param recipientEmail the recipient email
	 * @param recipientPersonal the recipient personal
	 * @param subject the subject
	 * @param content the content
	 * @param emailHeaders the email headers
	 * @throws MessagingException the messaging exception
	 */
	public static void sendEmail(final SendEmail emailSender, String senderEmail,
			final String senderPersonal, String recipientEmail,
			String recipientPersonal, String subject, String content, Map<String, String> emailHeaders)
			throws MessagingException {
		final EmailMessage emailMessage = new EmailMessage();
		// Set the sender...
		EmailExtensions.setFromToEmailMessage(senderEmail, senderPersonal,
				EmailConstants.CHARSET_UTF8, emailMessage);
		// Set recipient
		EmailExtensions.addToRecipientToEmailMessage(recipientEmail,
				recipientPersonal, EmailConstants.CHARSET_UTF8, emailMessage);
		// Set subject
		LOGGER.debug("Subject:"+subject);
		// Remove new line characters from subject. If the subject contains new line characters a strange behavior occurs... 
		subject = StringUtils.removeNewlineCharacters(subject);
		emailMessage.setSubject(subject);
		// Set content...
		LOGGER.debug("Content:"+content);
		emailMessage.setUtf8Content(content);
		// Set email header
		if(emailHeaders != null && !emailHeaders.isEmpty()) {
			for (Entry<String, String> emailHeaderEntry : emailHeaders.entrySet()) {
				emailMessage.setHeader(emailHeaderEntry.getKey(), emailHeaderEntry.getValue());
			}
		}
		emailSender.sendEmailMessage(emailMessage);
	}

}
