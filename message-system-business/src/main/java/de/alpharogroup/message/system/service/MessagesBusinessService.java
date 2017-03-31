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
package de.alpharogroup.message.system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.application.models.utils.MessageModelConverter;
import de.alpharogroup.message.system.daos.MessagesDao;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.message.system.factories.MessageSystemFactory;
import de.alpharogroup.message.system.service.api.MessageRecipientsService;
import de.alpharogroup.message.system.service.api.MessagesService;
import de.alpharogroup.user.entities.Users;
import de.alpharogroup.user.management.service.api.UsersService;

@Transactional
@Service("messagesService")
public class MessagesBusinessService extends AbstractBusinessService<Messages, Integer, MessagesDao>
		implements MessagesService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** The MessageRecipients service. */
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	/** The Users service. */
	@Autowired
	private UsersService usersService;

	@Autowired
	public void setMessagesDao(final MessagesDao messagesDao) {
		setDao(messagesDao);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Messages> findMessagesChildren(final Messages parent) {
		final String hqlString = "select distinct m " + "from " + Messages.class.getSimpleName() + " as m "
				+ "where m.parent=:parent";
		final Query query = getQuery(hqlString);
		query.setParameter("parent", parent);
		final List<Messages> messages = query.getResultList();
		if (null != messages && !messages.isEmpty()) {
			return messages;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Messages> findAllChildren(final Messages parent) {
		final List<Messages> children = findMessagesChildren(parent);
		final List<Messages> childElements = new ArrayList<Messages>();
		childElements.addAll(children);
		if (children != null) {
			final Iterator<Messages> it = children.iterator();
			while (it.hasNext()) {
				final Messages child = it.next();
				childElements.addAll(findAllChildren(child));
			}
		}
		return childElements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Messages> findMessages(final Users user) {
		final String hqlString = "select distinct mr.message " + "from " + MessageRecipients.class.getSimpleName()
				+ " as mr " + "where mr.recipient=:user "
				+ "and mr.message.recipientDeletedFlag=:recipientDeletedFlag ";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("recipientDeletedFlag", Boolean.FALSE);
		final List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Messages> findMessages(final Users user, final MessageState state) {
		final String hqlString = "select distinct mr.message " + "from " + MessageRecipients.class.getSimpleName()
				+ " as mr " + "where mr.recipient=:user " + "and mr.message.state=:state "
				+ "and mr.message.recipientDeletedFlag=:recipientDeletedFlag ";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("state", state);
		query.setParameter("recipientDeletedFlag", Boolean.FALSE);
		final List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Messages> findSentMessages(final Users user) {
		final String hqlString = "select distinct m " + "from " + Messages.class.getSimpleName() + " as m "
				+ "where m.sender=:user " + "and m.senderDeletedFlag=:senderDeletedFlag";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("senderDeletedFlag", Boolean.FALSE);
		final List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Messages> findReplyMessages(final Users user) {
		final String hqlString = "select distinct m " + "from " + Messages.class.getSimpleName() + " as m "
				+ "where m.sender=:user " + "and m.parent is not null " + "and m.messagetype=:messagetype";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("messagetype", MessageType.REPLY);
		final List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Messages saveMessageWithRecipients(final IBaseMessageModel model) {
		if (model.getSendInformationModel().getSender() == null) {
			throw new IllegalArgumentException(
					"Message should have a Sender. Currently Sender is null. Sender should not be null.");
		}
		if (model.getSendInformationModel().getRecipients() == null
				|| model.getSendInformationModel().getRecipients().isEmpty()) {
			throw new IllegalArgumentException(
					"Message should have recipients. Currently recipients is null or empty. At least one recipient should be set.");
		}
		// Create a messages object from the given model...
		Messages message = MessageModelConverter.createMessage(model);
		// Save the created message object so give opportunity to save the
		// recipients from the message...
		message = merge(message);
		// Get all recipients from the model...
		final Set<Users> recipients = model.getSendInformationModel().getRecipients();
		// Save all recipients to the db..
		for (Users recipient : recipients) {
			recipient = usersService.get(recipient.getId());
			// Create association between message and recipient...
			final MessageRecipients messageRecipient = MessageSystemFactory.getInstance().newMessageRecipients(message,
					recipient);
			// Save the association to the database...
			messageRecipientsService.merge(messageRecipient);
		}
		// Update the message and return it...
		return merge(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Set<Users> getRecipients(final Messages message) {
		final String hqlString = "select mr.recipient " + "from " + MessageRecipients.class.getSimpleName() + " mr "
				+ "where mr.message=:message";
		final Query query = getQuery(hqlString);
		query.setParameter("message", message);
		final List<Users> recipients = query.getResultList();
		if (recipients != null && !recipients.isEmpty()) {
			return new HashSet<Users>(recipients);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRecipientFrom(final Messages message, final Users user) {
		final Set<Users> recipients = getRecipients(message);
		if (recipients != null && recipients.contains(user)) {
			return true;
		}
		return false;
	}

	public MessageRecipientsService getMessageRecipientsService() {
		return messageRecipientsService;
	}

	public void setMessageRecipientsService(final MessageRecipientsService messageRecipientsService) {
		this.messageRecipientsService = messageRecipientsService;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(final UsersService usersService) {
		this.usersService = usersService;
	}

}