package de.alpharogroup.message.system.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.message.system.daos.MessageRecipientsDao;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.service.api.MessageRecipientsService;
import de.alpharogroup.user.management.entities.Users;

@Transactional
@Service("messageRecipientsService")
public class MessageRecipientsBusinessService extends AbstractBusinessService<MessageRecipients, Integer, MessageRecipientsDao> implements MessageRecipientsService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public void setMessageRecipientsDao(final MessageRecipientsDao messageRecipientsDao) {
		setDao(messageRecipientsDao);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean deleteMessageRecipient(final Users recipient,
			final Messages message) {
		final String hqlSelectString =
				"select distinct mr from " + MessageRecipients.class.getSimpleName() + " mr " +
				"where mr.recipient=:recipient " +
				"and mr.message=:message";

		final Query query = getQuery(hqlSelectString);
		query.setParameter("recipient", recipient);
		query.setParameter("message", message);
		final List<MessageRecipients> toDel = query.getResultList();
		if (toDel != null && !toDel.isEmpty()) {
			for (final Iterator<MessageRecipients> iterator = toDel.iterator(); iterator
					.hasNext();) {
				MessageRecipients messageRecipient = iterator.next();
				messageRecipient.setMessage(null);
				messageRecipient.setRecipient(null);
				messageRecipient = merge(messageRecipient);
				delete(messageRecipient);
			}
			return true;
		}
		return false;
	}
}