package de.alpharogroup.message.system.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.message.system.entities.MessageRecipients;
import lombok.Getter;
import lombok.Setter;

@Repository("messageRecipientsDao")
public class MessageRecipientsDao extends JpaEntityManagerDao<MessageRecipients, Integer>{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The entity manager. */
	@PersistenceContext
	@Getter
	@Setter
	private EntityManager entityManager;
}
