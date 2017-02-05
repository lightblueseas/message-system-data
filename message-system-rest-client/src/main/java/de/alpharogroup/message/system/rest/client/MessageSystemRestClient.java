package de.alpharogroup.message.system.rest.client;

import de.alpharogroup.cxf.rest.client.AbstractRestClient;
import de.alpharogroup.message.system.rest.api.MessageRecipientsResource;
import de.alpharogroup.message.system.rest.api.MessagesResource;
import lombok.Getter;

/**
 * The class {@link MessageSystemRestClient}.
 */
public class MessageSystemRestClient extends AbstractRestClient
{

	/**
	 * The {@link MessagesResource}.
	 */
	@Getter
	private final MessagesResource messagesResource;

	/**
	 * The {@link MessageRecipientsResource}.
	 */
	@Getter
	private final MessageRecipientsResource messageRecipientsResource;

	/**
	 * Instantiates a new {@link MessageSystemRestClient} with the default base url.
	 */
	public MessageSystemRestClient()
	{
		this(DEFAULT_BASE_HTTP_URL);
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
