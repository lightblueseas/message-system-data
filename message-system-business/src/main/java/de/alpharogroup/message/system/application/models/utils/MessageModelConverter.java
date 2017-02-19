package de.alpharogroup.message.system.application.models.utils;

import java.util.Date;

import de.alpharogroup.message.system.application.models.BaseMessageModel;
import de.alpharogroup.message.system.application.models.InfoMessageModel;
import de.alpharogroup.message.system.application.models.ReplyMessageModel;
import de.alpharogroup.message.system.application.models.send.MessageContentModel;
import de.alpharogroup.message.system.application.models.send.SendInformationModel;
import de.alpharogroup.message.system.application.models.send.SendMessagePanelModel;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.message.system.application.models.send.api.ISendInformationModel;
import de.alpharogroup.message.system.entities.MessageRecipients;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.message.system.factories.MessageSystemFactory;
import de.alpharogroup.user.entities.Users;

/**
 * The Class MessageModelConverter.
 */
public class MessageModelConverter {
	
	/**
	 * Convert.
	 *
	 * @param messageRecipients
	 *            the message recipients
	 * @return the reply message model
	 */
	public static ReplyMessageModel convert(
			final MessageRecipients messageRecipients) {
		final ReplyMessageModel replyMessageModel = convert(messageRecipients.getMessage());	
		replyMessageModel.getSendInformationModel().addRecipient(messageRecipients.getRecipient());
		return replyMessageModel;	
	}	
	
	/**
	 * Convert.
	 *
	 * @param message the message
	 * @return the reply message model
	 */
	public static ReplyMessageModel convert(
			final Messages message) {
		final ReplyMessageModel replyMessageModel = new ReplyMessageModel();
		replyMessageModel.setSendInformationModel(new SendInformationModel());
		replyMessageModel.setMessageContentModel(new MessageContentModel());
	
		final Users sender = message.getSender();
		replyMessageModel.getSendInformationModel().setSender(sender);
		replyMessageModel.getSendInformationModel().setSentDate(message
				.getSentDate());
		replyMessageModel.getMessageContentModel().setSubject(message
				.getSubject());
		replyMessageModel.getMessageContentModel().setContent(message
				.getMessageContent());
		replyMessageModel.setParent(message);
		return replyMessageModel;
	
	}
	
	/**
	 * Creates the message.
	 *
	 * @param model the model
	 * @return the messages
	 */
	public static Messages createMessage(IBaseMessageModel model){
		Date sentDate = model.getSendInformationModel().getSentDate();
		if(sentDate == null){
			sentDate = new Date(System.currentTimeMillis());
		}
		final Messages message = MessageSystemFactory.getInstance()
				.newMessages(false, false, "sent", model.getMessageContentModel().getContent(),
						model.getMessageType(), false,
						model.getSendInformationModel().getSender(), sentDate, false,
						model.getMessageState(), model.getMessageContentModel().getSubject());
		return message;
	}
	
	public static Messages createMessage(InfoMessageModel model, Users sender, Date sentDate) {
		// set recipient...
//		String senderEmail = model.getApplicationSenderAddress(); 
//		String senderPersonal = model.getApplicationDomainName(); 
//		String recipientEmail = model.getRecipientEmailContact(); 
//		String recipientPersonal = model.getRecipientFullName(); 
		String subject = model.getMessageContentModel().getSubject(); 
		String content = model.getMessageContentModel().getContent(); 
		final Messages message = MessageSystemFactory.getInstance()
				.newMessages(Boolean.FALSE, Boolean.FALSE, "sent", content,
						MessageType.MAIL, Boolean.FALSE,
						sender, sentDate, Boolean.FALSE,
						MessageState.UNREPLIED, subject);
		return message;
	}
	

	
	public static Messages createMessage(InfoMessageModel model, Users sender) {
		return createMessage(model, sender, new Date(System.currentTimeMillis()));
	}
	
	/**
	 * Creates the reply message.
	 *
	 * @param model the model
	 * @return the messages
	 */
	public static Messages createReplyMessage(ReplyMessageModel model){
		Date sentDate = model.getSendInformationModel().getSentDate();
		if(sentDate == null){
			sentDate = new Date(System.currentTimeMillis());
		}
		final Messages message = MessageSystemFactory.getInstance()
				.newMessages(false, false, "sent", model.getMessageContentModel().getContent(),
						model.getMessageType(), false,
						model.getSendInformationModel().getSender(), sentDate, false,
						model.getMessageState(), model.getMessageContentModel().getSubject(), model.getParent());
		return message;
	}
	
	/**
	 * synchronize the given model with given messages object.
	 *
	 * @param message the message
	 * @param model the model
	 */
	public static void synchronize(Messages message, BaseMessageModel model){
		message.setMessageContent(model.getMessageContentModel().getContent());
		message.setMessagetype(model.getMessageType());
		message.setSender(model.getSendInformationModel().getSender());
		message.setSentDate(model.getSendInformationModel().getSentDate());
		message.setState(model.getMessageState());
		message.setSubject(model.getMessageContentModel().getSubject());
	}
	

	/**
	 * Creates the base message model from the given parameters.
	 *
	 * @param model the model
	 * @param sender the sender
	 * @param recipient the recipient
	 * @return the base message model
	 */
	public static IBaseMessageModel createBaseMessageModel(
			final SendMessagePanelModel model, Users sender, final Users recipient) {
		BaseMessageModel baseMessageModel = new BaseMessageModel();
		IMessageContentModel messageModel = new MessageContentModel();
		messageModel.setContent(model.getMessageContent());
		messageModel.setSubject(model.getSubject());
		baseMessageModel.setMessageContentModel(messageModel);
		ISendInformationModel messageSendModel = new SendInformationModel();
		baseMessageModel.setSendInformationModel(messageSendModel);
		baseMessageModel.setMessageState(MessageState.UNREPLIED);
		baseMessageModel.setMessageType(MessageType.MAIL);
		baseMessageModel.getSendInformationModel().addRecipient(recipient);
		baseMessageModel.getSendInformationModel().setSender(sender);
		baseMessageModel.getSendInformationModel().setSentDate(
				new Date(System.currentTimeMillis()));
		return baseMessageModel;
	}
	
	

}
