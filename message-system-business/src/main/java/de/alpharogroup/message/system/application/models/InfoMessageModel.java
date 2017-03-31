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

import java.io.Serializable;

import de.alpharogroup.xml.XmlExtensions;
import de.alpharogroup.xml.api.Transformable;

import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;

public class InfoMessageModel implements Serializable, Transformable<InfoMessageModel> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String applicationSenderAddress;
	private String applicationDomainName;
	private String recipientEmailContact; 
	private String recipientFullName;
	/** The data of message. */
	private IMessageContentModel messageContentModel;
	public String getApplicationSenderAddress() {
		return applicationSenderAddress;
	}
	public void setApplicationSenderAddress(String applicationSenderAddress) {
		this.applicationSenderAddress = applicationSenderAddress;
	}
	public String getApplicationDomainName() {
		return applicationDomainName;
	}
	public void setApplicationDomainName(String applicationDomainName) {
		this.applicationDomainName = applicationDomainName;
	}
	public String getRecipientEmailContact() {
		return recipientEmailContact;
	}
	public void setRecipientEmailContact(String recipientEmailContact) {
		this.recipientEmailContact = recipientEmailContact;
	}
	public String getRecipientFullName() {
		return recipientFullName;
	}
	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
	}
	public IMessageContentModel getMessageContentModel() {
		return messageContentModel;
	}
	public void setMessageContentModel(IMessageContentModel messageModel) {
		this.messageContentModel = messageModel;
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
	public InfoMessageModel toObject(String xml) {
		InfoMessageModel model = XmlExtensions.toObjectWithXStream(xml);
		return model;
	}
	

}
