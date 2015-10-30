package de.alpharogroup.message.system.service.api;

import de.alpharogroup.db.service.jpa.BusinessService;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.user.management.entities.Users;

public interface MessageRecipientsService extends BusinessService<MessageRecipients, Integer>{
	

	boolean deleteMessageRecipient(final Users recipient, final Messages message);
}