package no.koteng.awesomeapp;

import java.io.Serializable;

public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String username;
	private final String realName;
	private final String email;
	private String selectedImageUrl;

	public UserData(String username, String realName, String email) {
		this.username = username;
		this.realName = realName;
		this.email = email;
	}

	public String getSelectedImageUrl() {
		return selectedImageUrl;
	}

	public void setSelectedImageUrl(String selectedImageUrl) {
		this.selectedImageUrl = selectedImageUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getRealName() {
		return realName;
	}

	public String getEmail() {
		return email;
	}
}
