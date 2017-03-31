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
package de.alpharogroup.message.system.application.models.send;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import de.alpharogroup.message.system.application.models.send.api.ISendInformationModel;
import de.alpharogroup.user.entities.Users;
import de.alpharogroup.xml.XmlExtensions;
import de.alpharogroup.xml.api.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

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
	public ISendInformationModel toObject(String xml) {
		ISendInformationModel model = XmlExtensions.toObjectWithXStream(xml);
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toXml() {
		String xml = XmlExtensions.toXmlWithXStream(this);
		return xml;
	}

}
