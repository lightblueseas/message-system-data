package de.alpharogroup.db.init;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import de.alpharogroup.lang.PropertiesUtils;
import de.alpharogroup.message.system.db.init.DatabaseInitialization;

public class InitializeDatabase {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		Properties dbProperties = PropertiesUtils.loadProperties("jdbc.properties");
		new DatabaseInitialization(dbProperties).initializeDatabase();
	}

}