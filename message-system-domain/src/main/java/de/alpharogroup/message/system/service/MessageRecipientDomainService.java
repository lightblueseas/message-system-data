package de.alpharogroup.message.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.message.system.daos.MessageRecipientsDao;
import de.alpharogroup.message.system.domain.MessageRecipient;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.mapper.MessageRecipientsMapper;
import de.alpharogroup.message.system.service.api.MessageRecipientService;
import de.alpharogroup.message.system.service.api.MessageRecipientsService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;


@Transactional
@Service("messageRecipientDomainService")
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
	public void setMessageRecipientsDao(final MessageRecipientsDao messageRecipientsDao) {
		setDao(messageRecipientsDao);
	}
}
