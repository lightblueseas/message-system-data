package de.alpharogroup.message.system.rest.client;

import de.alpharogroup.cxf.rest.client.AbstractRestClient;
import de.alpharogroup.message.system.rest.api.MessageRecipientsResource;
import de.alpharogroup.message.system.rest.api.MessagesResource;

/**
 * The class {@link MessageSystemRestClient}.
 */
public class MessageSystemRestClient extends AbstractRestClient
{

	/** The messages resource. */
	MessagesResource messagesResource;

	/** The message recipients resource. */
	MessageRecipientsResource messageRecipientsResource;

	/**
	 * Instantiates a new {@link MessageSystemRestClient} with the default base url.
	 */
	public MessageSystemRestClient()
	{
		this(DEFAULT_BASE_URL);
	}

	/**
	 * Instantiates a new {@link MessageSystemRestClient} with the given base url.
	 *
	 * @param baseUrl the base url
	 */
	public MessageSystemRestClient(final String baseUrl)
	{
		super(baseUrl);
		messagesResource = newResource(MessagesResource.class);
		messageRecipientsResource = newResource(MessageRecipientsResource.class);
	}

}
