package de.alpharogroup.message.system.domain;

import de.alpharogroup.domain.BaseDomainObject;
import de.alpharogroup.user.management.domain.Contactmethod;
import de.alpharogroup.user.management.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MessageRecipient extends BaseDomainObject<Integer> {
	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/**
	 * The message attribute that references to the Entity class {@link de.alpharogroup.message.system.entities.Messages}.
	 */
	private Message message;
	/**
	 * The recipient attribute that references to the Entity class {@link de.alpharogroup.user.management.entities.Users}.
	 */
	private User recipient;	
	/**
	 * The recipient email as a String object that can be indicate that the
	 * recipient is a user outside from the system. For instance a user from the
	 * system makes a recommendation to a friend.
	 */
	private Contactmethod recipientEmail;
}
