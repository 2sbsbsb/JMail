package util.mail;

import java.util.ArrayList;
import java.util.List;

public class MessageContainer {

	private final String subject;
	private final String messageBody;
	private final List<String> attachementPaths;

	/**
	 * @param subject
	 * @param messageBody
	 */
	MessageContainer(String subject, String messageBody) {
		this.subject = subject;
		this.messageBody = messageBody;
		attachementPaths = new ArrayList<String>();
	}

	/**
	 * @param path
	 */
	void addAttachment(String path) {
		attachementPaths.add(path);
	}

	/**
	 * @param path
	 */
	void addAttachment(String... paths) {
		for (String path : paths) {
			attachementPaths.add(path);
		}
	}

	/**
	 * @return
	 */
	String getSubject() {
		return subject;
	}

	/**
	 * @return
	 */
	String getMessageBody() {
		return messageBody;
	}

	/**
	 * @return
	 */
	List<String> getAttachments() {
		return attachementPaths;
	}

}
