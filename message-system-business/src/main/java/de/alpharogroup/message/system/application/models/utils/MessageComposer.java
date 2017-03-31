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
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.message.system.application.models.send.MessageContentModel;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;
import de.alpharogroup.velocity.VelocityExtensions;

/**
 * The Class MessageComposer.
 */
public class MessageComposer {

	/** The Constant logger. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(MessageComposer.class.getName());

	/**
	 * Creates the message model.
	 *
	 * @param contextModel
	 *            the velocity context model
	 * @param xmlMailTemplateName
	 *            the xml mail template name
	 * @param locale
	 *            the locale
	 * @return the message model
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParseException
	 *             the parse exception
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	public static IMessageContentModel createMessageModel(Map<String, Object> contextModel, String xmlMailTemplateName,
			Locale locale) throws IOException, ParseException, URISyntaxException {
		String localizedName = getXmlMailTemplateName(xmlMailTemplateName, locale, false);
		IMessageContentModel xmlMailTemplate = getEmailTemplate(localizedName);

		Template subjectTemplate = VelocityExtensions.getTemplate(xmlMailTemplate.getSubject());

		Template contentTemplate = VelocityExtensions.getTemplate(xmlMailTemplate.getContent());

		VelocityContext context = new VelocityContext(contextModel);
		StringWriter contentWriter = new StringWriter();
		contentTemplate.merge(context, contentWriter);
		StringWriter subjectWriter = new StringWriter();
		subjectTemplate.merge(context, subjectWriter);

		IMessageContentModel result = new MessageContentModel();
		result.setSubject(subjectWriter.toString());
		result.setContent(contentWriter.toString());
		return result;
	}

	/**
	 * Gets the email template as a Message Model that contains the velocity
	 * templates.
	 *
	 * @param name
	 *            The resource name that represents an IMessageModel as an xml
	 *            file.
	 * @return the email template
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	public static IMessageContentModel getEmailTemplate(String name) throws IOException, URISyntaxException {
		InputStream is = ClassExtensions.getResourceAsStream(name);
		String xmlString = ReadFileExtensions.inputStream2String(is);
		IMessageContentModel messageModel = new MessageContentModel().toObject(xmlString);
		return messageModel;
	}

	/**
	 * Gets the xml mail template name from the given String and Locale.
	 *
	 * @param xmlMailTemplateName
	 *            the xml mail template name
	 * @param locale
	 *            the locale
	 * @param withCountry
	 *            the with country
	 * @return the xml mail template name
	 */
	public static String getXmlMailTemplateName(String xmlMailTemplateName, Locale locale, boolean withCountry) {
		StringBuilder localizedName = new StringBuilder();
		localizedName.append(xmlMailTemplateName);
		localizedName.append(LocaleExtensions.getLocaleFileSuffix(locale, withCountry));
		localizedName.append(".xml");
		return localizedName.toString().trim();
	}

}
