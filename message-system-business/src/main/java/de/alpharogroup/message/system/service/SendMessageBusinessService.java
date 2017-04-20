/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
import de.alpharogroup.string.StringExtensions;

/**
 * The Class SendMessageBusinessService.
 */
public class SendMessageBusinessService
{
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
		.getLogger(SendMessageBusinessService.class.getName());

	/**
	 * Send email with default email headers.
	 *
	 * @param emailSender
	 *            the email sender
	 * @param senderEmail
	 *            the sender email
	 * @param senderPersonal
	 *            the sender personal
	 * @param recipientEmail
	 *            the recipient email
	 * @param recipientPersonal
	 *            the recipient personal
	 * @param subject
	 *            the subject
	 * @param content
	 *            the content
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public static void sendEmail(final SendEmail emailSender, final String senderEmail,
		final String senderPersonal, final String recipientEmail, final String recipientPersonal,
		final String subject, final String content) throws MessagingException
	{
		sendEmail(emailSender, senderEmail, senderPersonal, recipientEmail, recipientPersonal,
			subject, content, null);
	}

	/**
	 * Send email with the given arguments.
	 *
	 * @param emailSender
	 *            the email sender
	 * @param senderEmail
	 *            the sender email
	 * @param senderPersonal
	 *            the sender personal
	 * @param recipientEmail
	 *            the recipient email
	 * @param recipientPersonal
	 *            the recipient personal
	 * @param subject
	 *            the subject
	 * @param content
	 *            the content
	 * @param emailHeaders
	 *            the email headers
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public static void sendEmail(final SendEmail emailSender, final String senderEmail,
		final String senderPersonal, final String recipientEmail, final String recipientPersonal,
		final String subject, final String content, final Map<String, String> emailHeaders)
		throws MessagingException
	{
		sendEmail(emailSender, senderEmail, senderPersonal, recipientEmail, recipientPersonal,
			subject, content, emailHeaders, false);
	}

	/**
	 * Send email with the given arguments.
	 *
	 * @param emailSender
	 *            the email sender
	 * @param senderEmail
	 *            the sender email
	 * @param senderPersonal
	 *            the sender personal
	 * @param recipientEmail
	 *            the recipient email
	 * @param recipientPersonal
	 *            the recipient personal
	 * @param subject
	 *            the subject
	 * @param content
	 *            the content
	 * @param emailHeaders
	 *            the email headers
	 * @param withSession
	 *            the flag if the message will be created with the mail session of the
	 *            {@link SendEmail}
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public static void sendEmail(final SendEmail emailSender, final String senderEmail,
		final String senderPersonal, final String recipientEmail, final String recipientPersonal,
		String subject, final String content, final Map<String, String> emailHeaders,
		final boolean withSession) throws MessagingException
	{
		final EmailMessage emailMessage;
		if (withSession)
		{
			emailMessage = new EmailMessage(emailSender.getSession());
		}
		else
		{
			emailMessage = new EmailMessage();
		}
		// Set the sender...
		EmailExtensions.setFromToEmailMessage(senderEmail, senderPersonal,
			EmailConstants.CHARSET_UTF8, emailMessage);
		// Set recipient
		EmailExtensions.addToRecipientToEmailMessage(recipientEmail, recipientPersonal,
			EmailConstants.CHARSET_UTF8, emailMessage);
		// Set subject
		LOGGER.debug("Subject:" + subject);
		// Remove new line characters from subject. If the subject contains new
		// line characters a strange behavior occurs...
		subject = StringExtensions.removeNewlineCharacters(subject);
		emailMessage.setSubject(subject);
		// Set content...
		LOGGER.debug("Content:" + content);
		emailMessage.setUtf8Content(content);
		// Set email header
		if (emailHeaders != null && !emailHeaders.isEmpty())
		{
			for (final Entry<String, String> emailHeaderEntry : emailHeaders.entrySet())
			{
				emailMessage.setHeader(emailHeaderEntry.getKey(), emailHeaderEntry.getValue());
			}
		}
		emailSender.sendEmailMessage(emailMessage);
	}

	/**
	 * Send info email.
	 *
	 * @param emailSender
	 *            the email sender
	 * @param model
	 *            the model
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public static void sendInfoEmail(final SendEmail emailSender, final InfoMessageModel model)
		throws MessagingException
	{
		sendInfoEmail(emailSender, model, null);
	}

	/**
	 * Send info email.
	 *
	 * @param emailSender
	 *            the email sender
	 * @param model
	 *            the model
	 * @param emailHeaders
	 *            the email headers
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public static void sendInfoEmail(final SendEmail emailSender, final InfoMessageModel model,
		final Map<String, String> emailHeaders) throws MessagingException
	{
		sendEmail(emailSender, model.getApplicationSenderAddress(),
			model.getApplicationDomainName(), model.getRecipientEmailContact(),
			model.getRecipientFullName(), model.getMessageContentModel().getSubject(),
			model.getMessageContentModel().getContent(), emailHeaders);
	}

}
