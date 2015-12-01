package de.alpharogroup.message.system.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import de.alpharogroup.domain.BaseDomainObject;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.resource.system.domain.Resource;
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
public class Message extends BaseDomainObject<Integer> {

	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/**
	 * The parent of this message can be null if its the root message.
	 **/
	private Message parent;
	/**
	 * A flag that indicates that the message is deleted from the sender but will not really deleted
	 * because of references to other messages.
	 */
	private Boolean senderDeletedFlag;
	/**
	 *  A flag that indicates that the message is deleted from the recipient but will not really deleted
	 * because of references to other messages.
	 */
	private Boolean recipientDeletedFlag;
	/** A flag that indicates that the message could not be sent. */
	private Boolean failed2sentemail;
	/** The folder of the message. */
	private String folder;
	/** The content of the message. */
	private String messageContent;
	/** An enum for the message type. */
	private MessageType messagetype;
	/**
	 * A flag that indicates if the message is readed(at least opened) from the
	 * recipient(s).
	 */
	private Boolean readFlag;
	/** The sender of the message. */
	private User sender;
	/** The email address from the sender of this message. */
	private Contactmethod senderEmail;
	/** The sent date of the message. */
	private Date sentDate;
	/** A flag that indicates if the message is a spam message. */
	private Boolean spamFlag;
	/** An enum for the state from the message. */
	private MessageState state;
	/** The subject of the message. */
	private String subject;
	/** The attachments of the message. */
	private Set<Resource> attachments = new HashSet<>();

}
