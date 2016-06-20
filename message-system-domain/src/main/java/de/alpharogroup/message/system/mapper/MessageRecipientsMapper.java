package de.alpharogroup.message.system.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.domain.MessageRecipient;

/**
 * The class {@link MessageRecipientsMapper}.
 */
@Component
public class MessageRecipientsMapper extends AbstractEntityDOMapper<MessageRecipients, MessageRecipient> {

}
