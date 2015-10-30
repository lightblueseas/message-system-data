package de.alpharogroup.message.system.service.api;

import java.util.List;
import java.util.Set;

import de.alpharogroup.db.service.jpa.BusinessService;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.user.management.entities.Users;

/**
 * The Interface MessagesService.
 */
public interface MessagesService extends BusinessService<Messages, Integer>{
	
	/**
	 * Creates a Messages object and saves it with all recipients from the given BaseMessageModel object.
	 *
	 * @param model the model
	 * @return the messages
	 */
	Messages saveMessageWithRecipients(IBaseMessageModel model);
	
	/**
	 * Gets the recipients from the given Messages object.
	 *
	 * @param message the message
	 * @return the recipients
	 */
	Set<Users> getRecipients(Messages message);
	
	/**
	 * Checks if the given user is recipient from the given Messages object.
	 *
	 * @param message the message
	 * @param user the user
	 * @return true if the user is a recipient otherwise false
	 */
	boolean isRecipientFrom(Messages message, Users user);	
	
	/**
	 * Find all messages from the given user.
	 *
	 * @param user the user.
	 * @return the list of all messages from the given user.
	 */
	List<Messages> findMessages(Users user);

	/**
	 * Find all messages from the given user and state.
	 *
	 * @param user the user.
	 * @param state the state of the Message @see {@link MessageState}.
	 * @return the list of all messages from the given user.
	 */
	List<Messages> findMessages(final Users user, final MessageState state);
	
	/**
	 * Find sent messages from the given user that represents the sender of the message.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Messages> findSentMessages(final Users user);
	
	/**
	 * Find reply messages from the given user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Messages> findReplyMessages(final Users user);
	
	/**
	 * Find messages children.
	 *
	 * @param parent the parent
	 * @return the list
	 */
	List<Messages> findMessagesChildren(Messages parent);
	
	/**
	 * Find all children and childrens children.
	 *
	 * @param parent the parent
	 * @return the list
	 */
	List<Messages> findAllChildren(final Messages parent);
}