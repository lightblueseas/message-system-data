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
