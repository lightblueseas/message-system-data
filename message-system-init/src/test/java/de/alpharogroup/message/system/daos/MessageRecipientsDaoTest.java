package de.alpharogroup.message.system.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.message.system.daos.MessageRecipientsDao;
import de.alpharogroup.message.system.entities.MessageRecipients;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class MessageRecipientsDaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	MessageRecipientsDao messageRecipientsDao;

	@Test
	public void testFindAll() {
		List<MessageRecipients> all = messageRecipientsDao.findAll();
		System.out.println(all);
	}
}
