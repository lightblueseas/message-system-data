package de.alpharogroup.message.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.message.system.daos.MessagesDao;
import de.alpharogroup.message.system.domain.Message;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.mapper.MessagesMapper;
import de.alpharogroup.message.system.service.api.MessageService;
import de.alpharogroup.message.system.service.api.MessagesService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

@Transactional
@Service("messageDomainService")
public class MessageDomainService extends AbstractDomainService<Integer, Message, Messages, MessagesDao, MessagesMapper>
		implements MessageService {

	/** The {@link MessagesService}. */
	@Autowired
	@Getter
	@Setter
	private MessagesService messagesService;

	/**
	 * Sets the specific {@link MessagesDao}.
	 *
	 * @param messagesDao
	 *            the new {@link MessagesDao}.
	 */
	@Autowired
	public void setMessagesDao(final MessagesDao messagesDao) {
		setDao(messagesDao);
	}
}
