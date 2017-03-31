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

	/**
	 * Sets the specific {@link MessagesMapper}.
	 *
	 * @param mapper
	 *            the new {@link MessagesMapper}.
	 */
	@Autowired
	public void setMessagesMapper(MessagesMapper mapper) {
		setMapper(mapper);
	}

}
