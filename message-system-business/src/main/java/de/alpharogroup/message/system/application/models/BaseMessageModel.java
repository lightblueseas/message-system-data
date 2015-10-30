package de.alpharogroup.message.system.application.models;

import de.alpharogroup.message.system.application.models.send.SendMessageModel;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.xml.XmlUtils;

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
		String xml = XmlUtils.toXmlWithXStream(this);
		return xml;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public BaseMessageModel toObject(String xml) {
		BaseMessageModel model = XmlUtils.toObjectWithXStream(xml);
		return model;
	}
}
