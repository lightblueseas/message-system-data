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
package de.alpharogroup.message.system.application.models.send.api;

import java.io.Serializable;
import java.util.Locale;

import de.alpharogroup.xml.api.Xmlable;

/**
 * The Interface IMessageModel.
 */
public interface IMessageContentModel extends Serializable, Xmlable {

	/**
	 * Gets the message content.
	 *
	 * @return the message content
	 */
	String getContent();

	/**
	 * Gets the locale for the message content.
	 *
	 * @return the locale for the message content.
	 */
	Locale getLocale();

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	String getSubject();

	/**
	 * Sets the message content.
	 *
	 * @param messageContent
	 *            the new message content
	 */
	void setContent(String messageContent);

	/**
	 * Sets the locale for the message content.
	 *
	 * @param locale
	 *            the locale for the message content.
	 */
	void setLocale(Locale locale);

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the new subject
	 */
	void setSubject(String subject);

}