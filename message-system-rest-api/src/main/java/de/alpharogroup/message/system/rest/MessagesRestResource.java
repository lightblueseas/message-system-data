package de.alpharogroup.message.system.rest;

import de.alpharogroup.message.system.domain.Message;
import de.alpharogroup.message.system.rest.api.MessagesResource;
import de.alpharogroup.message.system.service.api.MessageService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

public class MessagesRestResource
 extends AbstractRestfulResource<Integer, Message, MessageService>
	implements
		MessagesResource
{

}
