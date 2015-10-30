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
import de.alpharogroup.user.management.entities.Users;
import de.alpharogroup.user.management.service.api.UsersService;


@Transactional
@Service("messagesService")
public class MessagesBusinessService extends AbstractBusinessService<Messages, Integer, MessagesDao> implements MessagesService {
	
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
	public void setMessagesDao(MessagesDao messagesDao) {
		setDao(messagesDao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Messages> findMessagesChildren(Messages parent) {
		final String hqlString = 
				"select distinct m "
				+ "from Messages as m "
				+ "where m.parent=:parent";
		final Query query = getQuery(hqlString);
		query.setParameter("parent", parent);
		List<Messages> messages = query.getResultList();
		if (null != messages && !messages.isEmpty()) {
			return messages;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
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
	@SuppressWarnings("unchecked")
	public List<Messages> findMessages(Users user) {
		final String hqlString = 
				"select distinct mr.message "
				+ "from MessageRecipients as mr "
				+ "where mr.recipient=:user " +
				"and mr.message.recipientDeletedFlag=:recipientDeletedFlag ";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("recipientDeletedFlag", Boolean.FALSE);
		List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Messages> findMessages(final Users user, final MessageState state) {
		final String hqlString = 
				"select distinct mr.message "
				+ "from MessageRecipients as mr "
				+ "where mr.recipient=:user " +
				"and mr.message.state=:state " +
				"and mr.message.recipientDeletedFlag=:recipientDeletedFlag ";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("state", state);
		query.setParameter("recipientDeletedFlag", Boolean.FALSE);
		List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Messages> findSentMessages(final Users user) {
		final String hqlString = 
				"select distinct m "
				+ "from Messages as m "
				+ "where m.sender=:user " +
				"and m.senderDeletedFlag=:senderDeletedFlag";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("senderDeletedFlag", Boolean.FALSE);
		List<Messages> messages = query.getResultList();
		return messages;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Messages> findReplyMessages(final Users user) {
		final String hqlString = 
				"select distinct m "
				+ "from Messages as m "
				+ "where m.sender=:user " 
				+ "and m.parent is not null "
				+ "and m.messagetype=:messagetype";
		final Query query = getQuery(hqlString);
		query.setParameter("user", user);
		query.setParameter("messagetype", MessageType.REPLY);
		List<Messages> messages = query.getResultList();
		return messages;
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	public Messages saveMessageWithRecipients(IBaseMessageModel model) {
		if(model.getSendInformationModel().getSender() == null) {
			throw new IllegalArgumentException("Message should have a Sender. Currently Sender is null. Sender should not be null.");
		}
		if(model.getSendInformationModel().getRecipients() == null || model.getSendInformationModel().getRecipients().isEmpty()) {
			throw new IllegalArgumentException("Message should have recipients. Currently recipients is null or empty. At least one recipient should be set.");
		}
		// Create a messages object from the given model...
		Messages message = MessageModelConverter.createMessage(model);
		// Save the created message object so give opportunity to save the recipients from the message...
		message = merge(message);
		// Get all recipients from the model...
		Set<Users> recipients = model.getSendInformationModel().getRecipients();
		// Save all recipients to the db..
		for (Users recipient : recipients) {
			recipient = usersService.get(recipient.getId());
			// Create association between message and recipient...
			MessageRecipients messageRecipient = MessageSystemFactory
					.getInstance().newMessageRecipients(message,
							recipient);
			// Save the association to the database...
			messageRecipientsService
					.merge(messageRecipient);
		}
		// Update the message and return it...
		return merge(message);		
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> getRecipients(Messages message) {
		final String hqlString = "select mr.recipient " +
				"from MessageRecipients mr " +
				"where mr.message=:message";
		final Query query = getQuery(hqlString);
		query.setParameter("message", message);
		final List<Users> recipients = query.getResultList();
		if(recipients != null && !recipients.isEmpty()){
			return new HashSet<Users>(recipients);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isRecipientFrom(Messages message, Users user) {
		Set<Users> recipients = getRecipients(message);
		if(recipients != null && recipients.contains(user)){
			return true;
		}
		return false;
	}

	public MessageRecipientsService getMessageRecipientsService() {
		return messageRecipientsService;
	}

	public void setMessageRecipientsService(
			MessageRecipientsService messageRecipientsService) {
		this.messageRecipientsService = messageRecipientsService;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

}