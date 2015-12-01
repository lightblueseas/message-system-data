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
	 * @param parent the new parent
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
