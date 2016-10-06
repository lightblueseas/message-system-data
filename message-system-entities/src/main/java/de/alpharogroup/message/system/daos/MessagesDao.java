package de.alpharogroup.message.system.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.message.system.entities.Messages;
import lombok.Getter;
import lombok.Setter;

@Repository("messagesDao")
public class MessagesDao extends JpaEntityManagerDao<Messages, Integer>{

	@Autowired
	DataSource dataSource;
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
