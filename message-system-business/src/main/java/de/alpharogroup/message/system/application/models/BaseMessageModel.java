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

import de.alpharogroup.message.system.application.models.send.SendMessageModel;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.xml.XmlExtensions;

/**
 * The Class BaseMessageModel.
 */
public class BaseMessageModel extends SendMessageModel implements IBaseMessageModel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/** The message state. */
	private MessageState messageState;
	
	/** The message type. */
	private MessageType messageType;

    /**
     * {@inheritDoc}
     */
	@Override
	public MessageState getMessageState() {
		return messageState;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public MessageType getMessageType() {
		return messageType;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void setMessageState(MessageState messageState) {
		this.messageState = messageState;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
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
	public BaseMessageModel toObject(String xml) {
		BaseMessageModel model = XmlExtensions.toObjectWithXStream(xml);
		return model;
	}
}
