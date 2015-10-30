package de.alpharogroup.message.system.application.models.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.message.system.application.models.send.api.ISendInformationModel;
import de.alpharogroup.message.system.application.models.send.api.ISendMessageModel;
import de.alpharogroup.xml.XmlUtils;
import de.alpharogroup.xml.api.Transformable;

/**
 * The Class SendMessageModel.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageModel implements ISendMessageModel, Transformable<SendMessageModel>  {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The data of sender, recipients and sent date. */
	private ISendInformationModel sendInformationModel;
	
	/** The data of message. */
	private IMessageContentModel messageContentModel;

	@Override
	public String toXml() {
		String xml = XmlUtils.toXmlWithXStream(this);
		return xml;
	}

	@Override
	public SendMessageModel toObject(String xml) {
		SendMessageModel model = XmlUtils.toObjectWithXStream(xml);
		return model;
	}

}
