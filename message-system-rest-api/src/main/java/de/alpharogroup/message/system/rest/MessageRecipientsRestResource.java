package de.alpharogroup.message.system.rest;

import de.alpharogroup.message.system.domain.MessageRecipient;
import de.alpharogroup.message.system.rest.api.MessageRecipientsResource;
import de.alpharogroup.message.system.service.api.MessageRecipientService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

public class MessageRecipientsRestResource
	extends
		AbstractRestfulResource<Integer, MessageRecipient, MessageRecipientService>
	implements
		MessageRecipientsResource
{

}
