package de.alpharogroup.message.system.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.auth.models.UsernameSignUpModel;
import de.alpharogroup.auth.models.ValidationErrors;
import de.alpharogroup.date.CreateDateUtils;
import de.alpharogroup.message.system.application.models.BaseMessageModel;
import de.alpharogroup.message.system.application.models.send.MessageContentModel;
import de.alpharogroup.message.system.application.models.send.SendInformationModel;
import de.alpharogroup.message.system.application.models.send.api.IBaseMessageModel;
import de.alpharogroup.message.system.application.models.send.api.IMessageContentModel;
import de.alpharogroup.message.system.application.models.send.api.ISendInformationModel;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.message.system.enums.MessageType;
import de.alpharogroup.message.system.service.api.MessagesService;
import de.alpharogroup.user.management.entities.Roles;
import de.alpharogroup.user.management.entities.Users;
import de.alpharogroup.user.management.enums.Gender;
import de.alpharogroup.user.management.factories.UserManagementModelFactory;
import de.alpharogroup.user.management.service.api.RolesService;
import de.alpharogroup.user.management.service.api.UserManagementService;
import de.alpharogroup.user.management.sign.up.SignUpUserResult;
import de.alpharogroup.user.management.sign.up.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class MessagesBusinessServiceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private UserManagementService userManagementService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private MessagesService messagesService;
	@Autowired
	private AddressesService addressesService;
	
	@Test
	public void testSaveMessageWithRecipients() {
		IMessageContentModel messageModel = new MessageContentModel();
		ISendInformationModel sendInformationModel = new SendInformationModel();		
		messageModel.setContent("Hello guys,\n\nhow are you?\n\nCheers\n\nMichael");
		messageModel.setSubject("Hi guys");
		IBaseMessageModel model = new BaseMessageModel();
		model.setMessageContentModel(messageModel);
		model.setSendInformationModel(sendInformationModel);
		model.setMessageState(MessageState.UNREPLIED);
		model.setMessageType(MessageType.MAIL);

		Users sender = getUser("Michael", "Knight", "michael.knight@gmail.com", "knight");
		Set<Users> recipients = new HashSet<Users>();
		
		Users recipient = getUser("Anton", "Einstein", "anton.einstein@gmail.com", "einstein");
		recipients.add(recipient);
		model.getSendInformationModel().setRecipients(recipients);
		model.getSendInformationModel().setSender(sender);
		model.getSendInformationModel().setSentDate(new Date(System.currentTimeMillis()));
		Messages message = messagesService.saveMessageWithRecipients(model);
		AssertJUnit.assertTrue(messagesService.exists(message.getId()));
		Set<Users> r = messagesService.getRecipients(message);
		AssertJUnit.assertTrue(r != null && !r.isEmpty());
		AssertJUnit.assertTrue(r.iterator().next().equals(recipient));	
		
		// Test the find reply messages...
		// Create a reply message...
		messageModel = new MessageContentModel();
		sendInformationModel = new SendInformationModel();		
		messageModel.setContent("Hello Michael,\n\nim fine and you?\n\nCheers\n\nAnton");
		messageModel.setSubject("Re:Hi guys");
		model = new BaseMessageModel();
		model.setMessageContentModel(messageModel);
		model.setSendInformationModel(sendInformationModel);
		model.setMessageState(MessageState.UNREPLIED);
		model.setMessageType(MessageType.REPLY);
		// clear recipients
		recipients.clear();
		// its a reply so the sender is now the recipient...
		recipients.add(sender);
		model.getSendInformationModel().setRecipients(recipients);
		model.getSendInformationModel().setSender(recipient);
		model.getSendInformationModel().setSentDate(new Date(System.currentTimeMillis()));
		Messages replyMessage = messagesService.saveMessageWithRecipients(model);
		replyMessage.setParent(message);
		messagesService.merge(replyMessage);
		List<Messages> replies = messagesService.findReplyMessages(recipient);
		System.out.println(replies);
	}
	

	public Set<Roles> createRolesSet() {
		List<Roles> r = rolesService.findAll();
		Set<Roles> roles = new HashSet<Roles>();
		if(r != null && !r.isEmpty()) {
			roles.add(r.get(0));
		} else {
			Roles role = rolesService.createAndSaveRole("ADMIN", "The admin role");
			roles.add(role);
		}
		return roles;
	}
	
	public Users getUser(String firstname, String lastname, String email, String username) {
	
		UserManagementModelFactory userManagementModelFactory = UserManagementModelFactory.getInstance();
		UserModel userModel = userManagementModelFactory.newUserModel(
				lastname,
				CreateDateUtils.newDate(1974, 8, 28),
				firstname,
				Gender.MALE,
				"127.0.0.1",
				lastname,
				Locale.GERMAN,
				"01721745676",
				"032325444444",
				"032325444445",
				addressesService.get(30224)); // Ludwigsburg
		
		UsernameSignUpModel usernameSignUpModel = userManagementModelFactory.newUsernameSignupModel(
				email,
				"xxx",
				"xxx",
				Boolean.TRUE,
				username);
		
		Set<Roles> roles = createRolesSet();
		SignUpUserResult result = userManagementService.signUpUser(usernameSignUpModel, roles, userModel);
		if(result.getUser() == null){
			ValidationErrors errors = result.getValidationErrors();
			if(errors.equals(ValidationErrors.EMAIL_EXISTS_ERROR)){
				return userManagementService.findUserWithEmailOrUsername(email);
			}
			if(errors.equals(ValidationErrors.USERNAME_EXISTS_ERROR)){
				return userManagementService.findUserWithEmailOrUsername(username);
			}
		}
		return result.getUser();
	}

}
