package tr.com.huseyinaydin.dtos;

import java.util.Objects;

public class HeaderData {
	private String title;
	private String welcomeMessage;

	public HeaderData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HeaderData(String title, String welcomeMessage) {
		super();
		this.title = title;
		this.welcomeMessage = welcomeMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, welcomeMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeaderData other = (HeaderData) obj;
		return Objects.equals(title, other.title) && Objects.equals(welcomeMessage, other.welcomeMessage);
	}

	@Override
	public String toString() {
		return "HeaderData [title=" + title + ", welcomeMessage=" + welcomeMessage + "]";
	}
}