package de.alpharogroup.message.system.application.models.send;

import java.io.Serializable;

import de.alpharogroup.xml.XmlUtils;
import de.alpharogroup.xml.api.Transformable;

/**
 * The Class SendMessagePanelModel.
 */
public class SendMessagePanelModel implements Serializable, Transformable<SendMessagePanelModel> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The from. */
	private String recipient;

	/** The subject. */
	private String subject;

	/** The message content. */
	private String messageContent;

	/**
	 * Instantiates a new send message model.
	 */
	public SendMessagePanelModel() {
	}

	/**
	 * Gets the message content.
	 * 
	 * @return the message content
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * Gets the recipient.
	 * 
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the message content.
	 * 
	 * @param messageContent
	 *            the new message content
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	/**
	 * Sets the recipient.
	 * 
	 * @param recipient
	 *            the new recipient
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public String toXml() {
		String xmlString = XmlUtils.toXmlWithXStream(this);
		return xmlString;
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public SendMessagePanelModel toObject(String xml) {
		SendMessagePanelModel model = XmlUtils.toObjectWithXStream(xml);
		return model;
	}

}
