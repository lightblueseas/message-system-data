package de.alpharogroup.message.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.message.system.daos.MessageRecipientsDao;
import de.alpharogroup.message.system.domain.MessageRecipient;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.mapper.MessageRecipientsMapper;
import de.alpharogroup.message.system.service.api.MessageRecipientService;
import de.alpharogroup.message.system.service.api.MessageRecipientsService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

public class MessageRecipientDomainService extends
		AbstractDomainService<Integer, MessageRecipient, MessageRecipients, MessageRecipientsDao, MessageRecipientsMapper>
		implements MessageRecipientService {

	/** The {@link MessageRecipientsService}. */
	@Autowired
	@Getter
	@Setter
	private MessageRecipientsService messageRecipientsService;

	/**
	 * Sets the specific {@link MessageRecipientsDao}.
	 *
	 * @param messageRecipientsDao
	 *            the new {@link MessageRecipientsDao}.
	 */
	@Autowired
	public void setMessageRecipientsDao(MessageRecipientsDao messageRecipientsDao) {
		setDao(messageRecipientsDao);
	}
}
