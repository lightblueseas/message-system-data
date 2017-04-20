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
package de.alpharogroup.message.system.service.api;

import java.util.List;
import java.util.Set;

import de.alpharogroup.db.service.api.BusinessService;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.user.entities.Users;

/**
 * The Interface MessagesService.
 */
public interface MessagesService extends BusinessService<Messages, Integer>
{

	/**
	 * Find all children and childrens children.
	 *
	 * @param parent
	 *            the parent
	 * @return the list
	 */
	List<Messages> findAllChildren(final Messages parent);

	/**
	 * Find all messages from the given user.
	 *
	 * @param user
	 *            the user.
	 * @return the list of all messages from the given user.
	 */
	List<Messages> findMessages(Users user);

	/**
	 * Find all messages from the given user and state.
	 *
	 * @param user
	 *            the user.
	 * @param state
	 *            the state of the Message @see {@link MessageState}.
	 * @return the list of all messages from the given user.
	 */
	List<Messages> findMessages(final Users user, final MessageState state);

	/**
	 * Find messages children.
	 *
	 * @param parent
	 *            the parent
	 * @return the list
	 */
	List<Messages> findMessagesChildren(Messages parent);

	/**
	 * Find reply messages from the given user.
	 *
	 * @param user
	 *            the user
	 * @return the list
	 */
	List<Messages> findReplyMessages(final Users user);

	/**
	 * Find sent messages from the given user that represents the sender of the message.
	 *
	 * @param user
	 *            the user
	 * @return the list
	 */
	List<Messages> findSentMessages(final Users user);

	/**
	 * Gets the recipients from the given Messages object.
	 *
	 * @param message
	 *            the message
	 * @return the recipients
	 */
	Set<Users> getRecipients(Messages message);

	/**
	 * Checks if the given user is recipient from the given Messages object.
	 *
	 * @param message
	 *            the message
	 * @param user
	 *            the user
	 * @return true if the user is a recipient otherwise false
	 */
	boolean isRecipientFrom(Messages message, Users user);

	/**
	 * Creates a Messages object and saves it with all recipients from the given BaseMessageModel
	 * object.
	 *
	 * @param model
	 *            the model
	 * @return the messages
	 */
	Messages saveMessageWithRecipients(IBaseMessageModel model);
}