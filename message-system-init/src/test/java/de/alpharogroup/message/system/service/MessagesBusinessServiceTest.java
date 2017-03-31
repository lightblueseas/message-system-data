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
package de.alpharogroup.message.system.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.auth.models.UsernameSignUpModel;
import de.alpharogroup.auth.models.ValidationErrors;
import de.alpharogroup.date.CreateDateExtensions;
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
import de.alpharogroup.user.entities.Roles;
import de.alpharogroup.user.entities.Users;
import de.alpharogroup.user.management.enums.GenderType;
import de.alpharogroup.user.management.factories.UserManagementModelFactory;
import de.alpharogroup.user.management.service.api.UsersManagementService;
import de.alpharogroup.user.management.sign.up.SignUpUserResult;
import de.alpharogroup.user.management.sign.up.UserModel;
import de.alpharogroup.user.service.api.RolesService;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class MessagesBusinessServiceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private UsersManagementService userManagementService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private MessagesService messagesService;
	@Autowired
	private AddressesService addressesService;

	@Test(enabled = false)
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

		final Users sender = getUser("Michael", "Knight", "michael.knight@gmail.com", "knight");
		final Set<Users> recipients = new HashSet<>();

		final Users recipient = getUser("Anton", "Einstein", "anton.einstein@gmail.com", "einstein");
		recipients.add(recipient);
		model.getSendInformationModel().setRecipients(recipients);
		model.getSendInformationModel().setSender(sender);
		model.getSendInformationModel().setSentDate(new Date(System.currentTimeMillis()));
		final Messages message = messagesService.saveMessageWithRecipients(model);
		AssertJUnit.assertTrue(messagesService.exists(message.getId()));
		final Set<Users> r = messagesService.getRecipients(message);
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
		final Messages replyMessage = messagesService.saveMessageWithRecipients(model);
		replyMessage.setParent(message);
		messagesService.merge(replyMessage);
		final List<Messages> replies = messagesService.findReplyMessages(recipient);
		System.out.println(replies);
	}

	public Set<Roles> createRolesSet() {
		final List<Roles> r = rolesService.findAll();
		final Set<Roles> roles = new HashSet<>();
		if (r != null && !r.isEmpty()) {
			roles.add(r.get(0));
		} else {
			final Roles role = rolesService.createAndSaveRole("ADMIN", "The admin role");
			roles.add(role);
		}
		return roles;
	}

	public Users getUser(final String firstname, final String lastname, final String email, final String username) {

		final UserManagementModelFactory userManagementModelFactory = UserManagementModelFactory.getInstance();
		final UserModel userModel = userManagementModelFactory.newUserModel(lastname,
				CreateDateExtensions.newDate(1974, 8, 28), firstname, GenderType.MALE, "127.0.0.1", lastname,
				Locale.GERMAN, "01721745676", "032325444444", "032325444445", addressesService.get(30224)); // Ludwigsburg

		final UsernameSignUpModel usernameSignUpModel = userManagementModelFactory.newUsernameSignupModel(email, "xxx",
				"xxx", Boolean.TRUE, username);

		final Set<Roles> roles = createRolesSet();
		final SignUpUserResult result = userManagementService.signUpUser(usernameSignUpModel, roles, userModel);
		if (result.getUser() == null) {
			final ValidationErrors errors = result.getValidationErrors();
			if (errors.equals(ValidationErrors.EMAIL_EXISTS_ERROR)) {
				return userManagementService.findUserWithEmailOrUsername(email);
			}
			if (errors.equals(ValidationErrors.USERNAME_EXISTS_ERROR)) {
				return userManagementService.findUserWithEmailOrUsername(username);
			}
		}
		return result.getUser();
	}

}
