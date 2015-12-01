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
