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
package de.alpharogroup.message.system.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import de.alpharogroup.user.entities.Users;
import de.alpharogroup.user.management.entities.Contactmethods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity class {@link MessageRecipients} is keeping the information which
 * recipients received the message.
 */
@Entity
@Table(name = "message_recipients")
@Getter
@Setter
@NoArgsConstructor
public class MessageRecipients extends BaseEntity<Integer> implements Cloneable {

	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/**
	 * The message attribute that references to the Entity class
	 * {@link Messages}.
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "message_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MESSAGE_RECIPIENTS_MESSAGE_ID"))
	private Messages message;
	/**
	 * The recipient attribute that references to the Entity class
	 * {@link Users}.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipient_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MESSAGE_RECIPIENTS_RECIPIENT_ID"))
	private Users recipient;
	/**
	 * The recipient email as a String object that can be indicate that the
	 * recipient is a user outside from the system. For instance a user from the
	 * system makes a recommendation to a friend.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipient_email", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MESSAGE_RECIPIENTS_RECIPIENT_EMAIL"))
	private Contactmethods recipientEmail;
}
