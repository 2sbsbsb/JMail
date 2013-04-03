package util.mail;

import java.io.File;
import java.io.FileNotFoundException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import util.exception.UtilityException;

public class EmailServices {

    public static final String GOOGLE_MAIL_HOST = "smtp.gmail.com";
    public static final int GOOGLE_MAIL_PORT = 587;
    private final Authentication authentication;

    /**
     * @param host
     * @param port
     * @param fromAddress
     * @param fromPassword
     */
    public EmailServices(String host, int port, String fromAddress, String fromPassword) {
	authentication = new Authentication(host, port, fromAddress, fromPassword);
    }

    /**
     * Mail with no ccAddress and no attachment
     * 
     * @param toAddresses
     * @param msgSubject
     * @param messageText
     * @param attachmentPaths
     * @throws Exception
     * @throws FileNotFoundException
     * @throws MessagingException
     * @throws AddressException
     */
    public void sendMail(String[] toAddresses, String msgSubject, String messageText) throws UtilityException,
	    FileNotFoundException {
	sendMail(toAddresses, null, msgSubject, messageText);
    }

    /**
     * @param toAddresses
     * @param msgSubject
     * @param messageBody
     * @param attachmentPaths
     * @throws FileNotFoundException
     * @throws Exception
     * @throws MessagingException
     * @throws AddressException
     */
    public void sendMail(String[] toAddresses, String[] ccAddresses, String msgSubject, String messageBody,
	    String... attachmentPaths) throws UtilityException, FileNotFoundException {
	authentication.addTos(toAddresses);
	if (ccAddresses != null) {
	    authentication.addCcs(ccAddresses);
	}
	MessageContainer msgC = new MessageContainer(msgSubject, messageBody);
	if (attachmentPaths != null) {
	    for (String attachment : attachmentPaths) {
		File file = new File(attachment);
		if (file.exists()) {
		    msgC.addAttachment(attachment);
		} else {
		    throw new FileNotFoundException(this.getClass().getSimpleName() + ": file " + attachment
			    + " not found");
		}
	    }
	}
	Email email = new Email(authentication, msgC);
	try {
	    email.send();
	} catch (MessagingException e) {
	    throw new UtilityException(e.getMessage());
	}
    }   
    
    
}
