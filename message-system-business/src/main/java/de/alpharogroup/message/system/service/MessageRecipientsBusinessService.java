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
import de.alpharogroup.user.entities.Users;

@Transactional
@Service("messageRecipientsService")
public class MessageRecipientsBusinessService extends
		AbstractBusinessService<MessageRecipients, Integer, MessageRecipientsDao> implements MessageRecipientsService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public boolean deleteMessageRecipient(final Users recipient, final Messages message) {
		final String hqlSelectString = "select distinct mr from " + MessageRecipients.class.getSimpleName() + " mr "
				+ "where mr.recipient=:recipient " + "and mr.message=:message";

		final Query query = getQuery(hqlSelectString);
		query.setParameter("recipient", recipient);
		query.setParameter("message", message);
		final List<MessageRecipients> toDel = query.getResultList();
		if (toDel != null && !toDel.isEmpty()) {
			for (final Iterator<MessageRecipients> iterator = toDel.iterator(); iterator.hasNext();) {
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

	@Autowired
	public void setMessageRecipientsDao(final MessageRecipientsDao messageRecipientsDao) {
		setDao(messageRecipientsDao);
	}
}