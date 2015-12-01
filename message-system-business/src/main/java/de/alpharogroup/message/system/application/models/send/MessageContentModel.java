package de.alpharogroup.message.system.application.models.send;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.xml.XmlExtensions;
import de.alpharogroup.xml.api.Transformable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageContentModel implements IMessageContentModel, Transformable<IMessageContentModel> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The subject. */
	private String subject;

	/** The message content. */
	private String content;

	/** The locale for the message content. */
	private Locale locale;

    /**
     * {@inheritDoc}
     */
	@Override
	public String toXml() {
		Map<String, Class<?>> aliases = new HashMap<String, Class<?>>();
		aliases.put("message", MessageContentModel.class);
		String xmlString = XmlExtensions.toXmlWithXStream(this, aliases);
		return xmlString;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public IMessageContentModel toObject(String xml) {
		Map<String, Class<?>> aliases = new HashMap<String, Class<?>>();
		aliases.put("message", MessageContentModel.class);
		return XmlExtensions.toObjectWithXStream(xml, aliases);
	}

}
