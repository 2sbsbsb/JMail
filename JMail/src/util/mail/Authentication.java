/**
 *  Created on Jan 4, 2012
 *  @author S Bhushan
 */
package util.mail;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Authentication {

    private final String host;
    private final int port;
    private final String user;
    private final String pass;
    private List<String> tos = new ArrayList<String>();
    private List<String> ccs = new ArrayList<String>();

    /**
     * @param host
     * @param port
     * @param user
     * @param pass
     */
    Authentication(String host, int port, String user, String pass) {
	super();
	this.host = host;
	this.port = port;
	this.user = user;
	this.pass = pass;
    }

    /**
     * @param to
     */
    void addTos(String... tos) {
	for (String to : tos) {
	    if (isValidAddress(to)) {
		this.tos.add(to);
	    } else {
		throw new IllegalArgumentException("The address is not valid" + to);
	    }
	}
    }

    /**
     * @param to
     */
    void addCcs(String... ccs) {
	for (String cc : ccs) {
	    if (isValidAddress(cc)) {
		this.ccs.add(cc);
	    } else {
		throw new IllegalArgumentException("The address is not valid" + cc);
	    }
	}
    }

    int getPort() {
	return port;
    }

    String getUser() {
	return user;
    }

    String getHost() {
	return host;
    }

    String getPass() {
	return pass;
    }

    List<String> getTos() {
	return tos;
    }

    List<String> getCcs() {
	return ccs;
    }

    /**
     * @param emailAddress
     * @return
     */
    boolean isValidAddress(String emailAddress) {
	String[] tokens = emailAddress.split("@");
	return tokens.length == 2 && tokens[0].length() > 0 && tokens[1].length() > 0;
    }

}
