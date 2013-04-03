package util.mail;

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

    private final Authentication authentication;
    private final MessageContainer myMessage;

    /**
     * @param authentication
     * @param message
     */
    Email(Authentication authentication, MessageContainer myMessage) {
	this.authentication = authentication;
	this.myMessage = myMessage;
    }

    /**
     * @param host
     * @param user
     * @param pass
     * @param port
     * @return
     */
    private Session createSession() {
	Properties props = System.getProperties();
	String host = authentication.getHost();
	String user = authentication.getUser();
	String pass = authentication.getPass();
	int port = authentication.getPort();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.user", user);
	props.put("mail.smtp.password", pass);
	props.put("mail.smtp.port", port);
	props.put("mail.smtp.auth", "true");
	return Session.getDefaultInstance(props, null);
    }

    /**
     * @param multipart
     * @param filePath
     * @throws MessagingException
     */
    private void addAttachment(Multipart multipart, String filePath) throws MessagingException {
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	DataSource source = new FileDataSource(filePath);
	messageBodyPart.setDataHandler(new DataHandler(source));
	messageBodyPart.setFileName(source.getName());
	multipart.addBodyPart(messageBodyPart);
    }

    /**
     * @throws AddressException
     * @throws MessagingException
     */
    void send() throws AddressException, MessagingException {
	Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
	String host = authentication.getHost();
	String user = authentication.getUser();
	String pass = authentication.getPass();
	List<String> toAddresses = authentication.getTos();
	List<String> ccAddresses = authentication.getCcs();
	String subject = myMessage.getSubject();
	String messageBody = myMessage.getMessageBody();
	List<String> attachements = myMessage.getAttachments();
	//
	Session session = createSession();
	MimeMessage message = new MimeMessage(session);
	for (int i = 0; i < toAddresses.size(); i++) {
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddresses.get(i)));
	}
	for (int i = 0; i < ccAddresses.size(); i++) {
	    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddresses.get(i)));
	}
	message.setSubject(subject);
	//
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setContent(messageBody, "text/html");
	//
	Multipart multipart = new MimeMultipart("mixed");
	multipart.addBodyPart(messageBodyPart);
	//
	for (String filePath : attachements) {
	    addAttachment(multipart, filePath);
	}
	message.setContent(multipart);
	Transport transport = session.getTransport("smtp");
	transport.connect(host, user, pass);
	transport.sendMessage(message, message.getAllRecipients());
	transport.close();
	System.out.println("Email sent successfully");
    }

}
