package de.alpharogroup.message.system.application.models.send;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import de.alpharogroup.message.system.application.models.send.api.ISendInformationModel;
import de.alpharogroup.user.management.entities.Users;
import de.alpharogroup.xml.XmlExtensions;
import de.alpharogroup.xml.api.Transformable;

/**
 * The Class SendInformationModel captures information about the sender and the
 * recipients of a message.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendInformationModel implements ISendInformationModel, Transformable<ISendInformationModel> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The recipients of this message. */
	@Singular
	private Set<Users> recipients = new HashSet<Users>();

	/** The sender of this message. */
	private Users sender;

	/** The sent date of this message. */
	private Date sentDate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addRecipient(Users recipient) {
		if (recipients == null) {
			recipients = new HashSet<Users>();
		}
		recipients.add(recipient);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean removeRecipient(Users recipient) {
		return recipients.remove(recipient);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public String toXml() {
		String xml = XmlExtensions.toXmlWithXStream(this);
		return xml;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public ISendInformationModel toObject(String xml) {
		ISendInformationModel model = XmlExtensions.toObjectWithXStream(xml);
		return model;
	}

}
