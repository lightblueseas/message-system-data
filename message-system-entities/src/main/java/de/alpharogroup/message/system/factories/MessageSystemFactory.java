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
package de.alpharogroup.message.system.factories;

import java.io.Serializable;
import java.util.Date;

import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.user.management.entities.Contactmethods;
import de.alpharogroup.user.entities.Users;

/**
 * A factory for creating Domain objects for the message system.
 */
public class MessageSystemFactory implements Serializable {

	/** The Constant instance. */
	private static final MessageSystemFactory instance = new MessageSystemFactory();

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the single instance of MessageSystemFactory.
	 * 
	 * @return single instance of MessageSystemFactory
	 */
	public static MessageSystemFactory getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new MessageSystemFactory object.
	 */
	private MessageSystemFactory() {
		super();
	}
	

    /**
     * Data pool factory for Messages.
     *
     * @param deletedFlag the deleted flag
     * @param failed2sentemail the failed2sentemail
     * @param folder the folder
     * @param id the id
     * @param messageContent the message content
     * @param messagetype the messagetype
     * @param readFlag the read flag
     * @param sender the sender
     * @param senderEmail the senderEmail
     * @param sentDate the sent date
     * @param spamFlag the spam flag
     * @param state the state
     * @param subject the subject
     * @param parent the parent of the message to create.
     * @param recipientDeletedFlag the recipient deleted flag
     * @return the messages
     */
    public Messages newMessages( Boolean deletedFlag, Boolean failed2sentemail,
            String folder, Integer id, String messageContent,
            MessageType messagetype, Boolean readFlag,
            Users sender, Contactmethods senderEmail, Date sentDate, Boolean spamFlag,
            MessageState state, String subject, Messages parent, Boolean recipientDeletedFlag ) {    	
        Messages messages = new Messages();        
        messages.setSenderDeletedFlag( deletedFlag );
        messages.setFailed2sentemail( failed2sentemail );
        messages.setFolder( folder );
        messages.setId( id );
        messages.setMessageContent( messageContent );
        messages.setMessagetype( messagetype );
        messages.setReadFlag( readFlag );
        messages.setSender( sender );
        messages.setSenderEmail(senderEmail);
        messages.setSentDate( sentDate );
        messages.setSpamFlag( spamFlag );
        messages.setState( state );
        messages.setSubject( subject );
        messages.setParent(parent);
        messages.setRecipientDeletedFlag(recipientDeletedFlag);
        return messages;
    }
    
    /**
     * Data pool factory for Messages.
     *
     * @param deletedFlag the deleted flag
     * @param failed2sentemail the failed2sentemail
     * @param folder the folder
     * @param id the id
     * @param messageContent the message content
     * @param messagetype the messagetype
     * @param readFlag the read flag
     * @param sender the sender
     * @param sentDate the sent date
     * @param spamFlag the spam flag
     * @param state the state
     * @param subject the subject
     * @param parent the parent of the message to create.
     * @param recipientDeletedFlag the recipient deleted flag
     * @return the messages
     */
    public Messages newMessages( Boolean deletedFlag, Boolean failed2sentemail,
            String folder, Integer id, String messageContent,
            MessageType messagetype, Boolean readFlag,
            Users sender, Date sentDate, Boolean spamFlag,
            MessageState state, String subject, Messages parent, Boolean recipientDeletedFlag ) {
        return newMessages(deletedFlag, failed2sentemail, folder, id, messageContent, messagetype, readFlag, sender, null, sentDate, spamFlag, state, subject, parent, recipientDeletedFlag);
    }


    /**
     * Gets the messages.
     *
     * @param deletedFlag the deleted flag
     * @param failed2sentemail the failed2sentemail
     * @param folder the folder
     * @param messageContent the message content
     * @param messagetype the messagetype
     * @param readFlag the read flag
     * @param sender the sender
     * @param sentDate the sent date
     * @param spamFlag the spam flag
     * @param state the state
     * @param subject the subject
     * @param parent the parent of the message to create.
     * @return the messages
     */
    public Messages newMessages( final Boolean deletedFlag,
            final Boolean failed2sentemail, final String folder,
            final String messageContent,
            final MessageType messagetype, final Boolean readFlag,
            final Users sender, final Date sentDate, final Boolean spamFlag,
            final MessageState state, final String subject, Messages parent ) {
        return newMessages(deletedFlag, failed2sentemail, folder, null, messageContent, messagetype, readFlag, sender, sentDate, spamFlag, state, subject, parent, Boolean.FALSE);
    }


    /**
     * Gets the messages.
     *
     * @param deletedFlag the deleted flag
     * @param failed2sentemail the failed2sentemail
     * @param folder the folder
     * @param messageContent the message content
     * @param messagetype the messagetype
     * @param readFlag the read flag
     * @param sender the sender
     * @param sentDate the sent date
     * @param spamFlag the spam flag
     * @param state the state
     * @param subject the subject
     * @return the messages
     */
    public Messages newMessages( final Boolean deletedFlag,
            final Boolean failed2sentemail, final String folder,
            final String messageContent,
            final MessageType messagetype, final Boolean readFlag,
            final Users sender, final Date sentDate, final Boolean spamFlag,
            final MessageState state, final String subject ) {
        return newMessages( deletedFlag,
                failed2sentemail, folder, null, messageContent, messagetype, readFlag,
                sender, sentDate, spamFlag, state, subject, null, Boolean.FALSE );
    }
    
    /**
     * Data pool factory for MessageRecipients.
     *
     * @param id The id
     * @param message A valid Messages object
     * @param recipient A valid Users object
     * @return MessageRecipients A MessageRecipients object
     */
    public MessageRecipients newMessageRecipients( Integer id,
            Messages message, Users recipient ) {
        return newMessageRecipients(id, message, recipient, null);
    }
    
    /**
     * Data pool factory for MessageRecipients.
     *
     * @param id The id
     * @param message A valid Messages object
     * @param recipient A valid Users object
     * @param recipientEmail A valid Contactmethods object
     * @return MessageRecipients A MessageRecipients object
     */
    public MessageRecipients newMessageRecipients( Integer id,
            Messages message, Users recipient, Contactmethods recipientEmail ) {
        MessageRecipients messageRecipients = new MessageRecipients();
        messageRecipients.setId( id );
        messageRecipients.setMessage( message );
        messageRecipients.setRecipient( recipient );
        messageRecipients.setRecipientEmail(recipientEmail);
        return messageRecipients;
    }
    
    /**
     * Data pool factory for MessageRecipients.
     *
     * @param id The id
     * @param message A valid Messages object
     * @param recipientEmail A valid Contactmethods object
     * @return MessageRecipients A MessageRecipients object
     */
    public MessageRecipients newMessageRecipients( Integer id,
            Messages message, Contactmethods recipientEmail ) {
        return newMessageRecipients(id, message, null, recipientEmail);
    }

    /**
     * Gets the message recipients.
     * 
     * @param message the message
     * @param recipient the recipient
     * @return the message recipients
     */
    public MessageRecipients newMessageRecipients( final Messages message,
            final Users recipient ) {
        return newMessageRecipients(
                null, message,
                recipient );
    }

    /**
     * Gets the message recipients.
     * 
     * @param message the message
     * @param recipient the recipient
     * @param recipientEmail A valid Contactmethods object
     * @return the message recipients
     */
    public MessageRecipients newMessageRecipients( final Messages message,
            final Users recipient, Contactmethods recipientEmail ) {
        return newMessageRecipients(
                null, message,
                recipient, recipientEmail );
    }
}
