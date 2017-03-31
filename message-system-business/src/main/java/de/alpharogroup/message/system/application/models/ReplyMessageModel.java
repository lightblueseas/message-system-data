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
package de.alpharogroup.message.system.application.models;

import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.xml.XmlExtensions;

/**
 * The Class ReplyMessageModel.
 */
public class ReplyMessageModel extends BaseMessageModel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The parent of the reply message. */
	private Messages parent;

	/** The response message. */
	private String responseMessage;

	/** The response subject. */
	private String responseSubject;

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public Messages getParent() {
		return parent;
	}

	/**
	 * Gets the response message.
	 * 
	 * @return the response message
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * Gets the response subject.
	 * 
	 * @return the response subject
	 */
	public String getResponseSubject() {
		return responseSubject;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent
	 *            the new parent
	 */
	public void setParent(Messages parent) {
		this.parent = parent;
	}

	/**
	 * Sets the response message.
	 * 
	 * @param responseMessage
	 *            the new response message
	 */
	public void setResponseMessage(final String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * Sets the response subject.
	 * 
	 * @param responseSubject
	 *            the new response subject
	 */
	public void setResponseSubject(final String responseSubject) {
		this.responseSubject = responseSubject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toXml() {
		String xml = XmlExtensions.toXmlWithXStream(this);
		return xml;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReplyMessageModel toObject(String xml) {
		ReplyMessageModel model = XmlExtensions.toObjectWithXStream(xml);
		return model;
	}

}
