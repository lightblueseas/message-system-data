package de.alpharogroup.message.system.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.message.system.daos.MessagesDao;
import de.alpharogroup.message.system.entities.Messages;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class MessagesDaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private MessagesDao messagesDao;


	@Test
	public void testFindAll() {
		List<Messages> all = messagesDao.findAll();
		System.out.println(all);		
	}
}
