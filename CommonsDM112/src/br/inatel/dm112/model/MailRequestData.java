package br.inatel.dm112.model;

import java.util.Arrays;

public class MailRequestData {

	private String from;
	private String password;
	private String to;
	private byte[] content;
	private String message;

	public MailRequestData() {
	}

	public MailRequestData(String from, String password, String to, byte[] content) {
		super();
		this.from = from;
		this.password = password;
		this.to = to;
		this.content = content;
	}

	public MailRequestData(String from, String password, String to, String message) {
		this.from = from;
		this.password = password;
		this.to = to;
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MailRequestData{" +
				"from='" + from + '\'' +
				", password='" + password + '\'' +
				", to='" + to + '\'' +
				", content=" + Arrays.toString(content) +
				", message='" + message + '\'' +
				'}';
	}
}
