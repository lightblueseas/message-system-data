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
package de.alpharogroup.message.system.application.models.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.alpharogroup.message.system.application.models.InfoMessageModel;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.resourcebundle.locale.Locales;

import org.apache.velocity.runtime.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MessageComposer.
 */
public class EmailComposer {

	/** The Constant logger. */
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(EmailComposer.class.getName());

	/**
	 * The main method.
	 *
	 * @param strings the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static final void main(String... strings) throws IOException, ParseException, URISyntaxException {
		InfoMessageModel model = EmailComposer
				.createEmailMessageForForgottenPassword("xy@z.com", "z.com",
						"abc", "Albert Einstein", "a.e@gmail.com", "yyy",
						"http://www.xy.com/bla=fasel", Locales.GREEK);
		System.out.println(model.getMessageContentModel().getSubject());
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("recipientFullName", "Albert Einstein");
		context.put("urlForForgottenPassword", "http://www.xy.com/bla=fasel");
		context.put("username", "albert");
		context.put("newPassword", "xxx");
		context.put("applicationDomainName", "z.com");
		IMessageContentModel messageModel = MessageComposer.createMessageModel(context, "mail/templates/forgotten/pw/ForgottenPassword", null);
		System.out.println("Subject:\n"+messageModel.getSubject());
		System.out.println("Content:\n"+messageModel.getContent());
		messageModel = MessageComposer.createMessageModel(context, "mail/templates/forgotten/pw/ForgottenPassword", Locales.GREEK);
		System.out.println("Subject:\n"+messageModel.getSubject());
		System.out.println("Content:\n"+messageModel.getContent());
		messageModel = MessageComposer.createMessageModel(context, "mail/templates/forgotten/pw/ForgottenPassword", Locale.GERMAN);
		System.out.println("Subject:\n"+messageModel.getSubject());
		System.out.println("Content:\n"+messageModel.getContent());
		messageModel = MessageComposer.createMessageModel(context, "mail/templates/forgotten/pw/ForgottenPassword", Locale.ENGLISH);
		System.out.println("Subject:\n"+messageModel.getSubject());
		System.out.println("Content:\n"+messageModel.getContent());
	}

	/**
	 * Creates the email message for forgotten password.
	 *
	 * @param applicationSenderAddress the application sender address
	 * @param applicationDomainName the application domain name
	 * @param username the username
	 * @param recipientFullName the recipient full name
	 * @param recipientEmailContact the recipient email contact
	 * @param newPassword the new password
	 * @param urlForForgottenPassword the url for forgotten password
	 * @param locale the locale
	 * @return the info message model
	 */
	public static InfoMessageModel createEmailMessageForForgottenPassword(
			String applicationSenderAddress,
			final String applicationDomainName, String username,
			String recipientFullName, final String recipientEmailContact,
			final String newPassword, String urlForForgottenPassword,
			Locale locale) {	
		String xmlMailTemplatePath = "mail/templates/forgotten/pw/";
		String xmlMailTemplateFileName = "ForgottenPassword";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("recipientFullName", recipientFullName);
		context.put("urlForForgottenPassword", urlForForgottenPassword);
		context.put("username", username);
		context.put("newPassword", newPassword);
		context.put("applicationDomainName", applicationDomainName);
		IMessageContentModel messageModel = null;
		try {
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		} catch (IOException e) {
			LOGGER.error("Xml file could not be found.", e);
		} catch (ParseException e) {
			LOGGER.error("Template could not be parsed.", e);
		} catch (URISyntaxException e) {
			LOGGER.error("Xml file could not be found.", e);
		}
		
		InfoMessageModel infoMessageModel = new InfoMessageModel();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}
	
	public static InfoMessageModel createEmailForMemberSendResponse(
			String applicationSenderAddress,
			final String applicationDomainName, 
			String username,
			String recipientFullName, 
			final String recipientEmailContact,
			final String messagePart,
			Locale locale){
		String xmlMailTemplatePath = "mail/templates/response/sent/";
		String xmlMailTemplateFileName = "MemberSendResponse";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("recipientFullName", recipientFullName);
		context.put("memberUsername", username); 
		context.put("messagePart", messagePart);
		context.put("applicationDomainName", applicationDomainName);
		IMessageContentModel messageModel = null;
		try {
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		} catch (IOException e) {
			LOGGER.error("Xml file could not be found.", e);
		} catch (ParseException e) {
			LOGGER.error("Template could not be parsed.", e);
		} catch (URISyntaxException e) {
			LOGGER.error("Xml file could not be found.", e);
		}
		
		InfoMessageModel infoMessageModel = new InfoMessageModel();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}
	
	public static InfoMessageModel createEmailMessageForRecommendProfile(
			String applicationSenderAddress,
			final String applicationDomainName, String memberUsername,
			String recipientFullName, final String recipientEmailContact,
			final String profileData, final String profileLink, String invitationText,
			final String senderProfileLink, String signupLink,
			Locale locale) {	
			// TODO set paths and filename and context...
		String xmlMailTemplatePath = "mail/templates/recommend/profile/";
		String xmlMailTemplateFileName = "MemberSendRecommendation";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("memberUsername", memberUsername);
		context.put("profileData", profileData);
		context.put("profileLink", profileLink);
		context.put("invitationText", invitationText);
		context.put("senderProfileLink", senderProfileLink);
		context.put("signupLink", signupLink);
		context.put("applicationDomainName", applicationDomainName);
		IMessageContentModel messageModel = null;
		try {
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		} catch (IOException e) {
			LOGGER.error("Xml file could not be found.", e);
		} catch (ParseException e) {
			LOGGER.error("Template could not be parsed.", e);
		} catch (URISyntaxException e) {
			LOGGER.error("Xml file could not be found.", e);
		}
		
		InfoMessageModel infoMessageModel = new InfoMessageModel();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}

}
