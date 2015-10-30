package de.alpharogroup.message.system.service;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import de.alpharogroup.message.system.application.models.InfoMessageModel;

public final class CommonsEmailSendService {
	
	public static void sendEmail(final EmailConfiguration config, InfoMessageModel model) throws EmailException {
		// TODO make class for email config...
		Email email = new SimpleEmail();
		email.setHostName(config.getHostName());
		email.setSmtpPort(config.getSmtpPort());
		email.setFrom(model.getApplicationSenderAddress());
		email.setSubject(model.getMessageContentModel().getSubject());
		email.setMsg(model.getMessageContentModel().getContent());
		email.addTo(model.getRecipientEmailContact());
		email.send();
	}

}
