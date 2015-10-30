package de.alpharogroup.message.system.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import de.alpharogroup.user.management.entities.Contactmethods;
import de.alpharogroup.user.management.entities.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Entity class {@link MessageRecipients} is keeping the
 * information which recipients received the message.
 */
@Entity
@Table(name = "message_recipients")
@Getter
@Setter
@NoArgsConstructor
public class MessageRecipients 
extends BaseEntity<Integer> 
implements Cloneable {

	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/**
	 * The message attribute that references to the Entity class {@link Messages}.
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "message_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MESSAGE_RECIPIENTS_MESSAGE_ID"))
	private Messages message;
	/**
	 * The recipient attribute that references to the Entity class {@link Users}.
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
