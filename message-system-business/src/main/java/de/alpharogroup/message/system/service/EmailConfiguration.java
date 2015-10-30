package de.alpharogroup.message.system.service;

public class EmailConfiguration {
    /**
     * The hostname of the mail server with which to connect. If null will try
     * to get property from system.properties. If still null, quit.
     */
    private String hostName;
    

    /**
     * The port number of the mail server to connect to.
     * Defaults to the standard port ( 25 ).
     */
    private int smtpPort = 25;

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
    
}
