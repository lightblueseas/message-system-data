package de.alpharogroup.message.system.application.models.send.api;

import java.io.Serializable;
import java.util.Locale;

import de.alpharogroup.xml.api.Xmlable;

/**
 * The Interface IMessageModel.
 */
public interface IMessageContentModel extends Serializable, Xmlable {

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	String getSubject();

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	void setSubject(String subject);

	/**
	 * Gets the message content.
	 *
	 * @return the message content
	 */
	String getContent();

	/**
	 * Sets the message content.
	 *
	 * @param messageContent the new message content
	 */
	void setContent(String messageContent);
	
	/**
	 * Gets the locale for the message content.
	 *
	 * @return the locale for the message content.
	 */
	Locale getLocale();
	
	/**
	 * Sets the locale for the message content.
	 *
	 * @param locale the locale for the message content.
	 */
	void setLocale(Locale locale);

}