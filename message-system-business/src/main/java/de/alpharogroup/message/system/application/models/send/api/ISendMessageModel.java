package de.alpharogroup.message.system.application.models.send.api;

import java.io.Serializable;


public interface ISendMessageModel extends Serializable {

	IMessageContentModel getMessageContentModel();

	ISendInformationModel getSendInformationModel();

	void setMessageContentModel(IMessageContentModel messageModel);

	void setSendInformationModel(
			ISendInformationModel sendInformationModel);

}