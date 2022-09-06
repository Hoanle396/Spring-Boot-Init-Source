package com.courses.edu.controllers.auth.dto;

import lombok.Data;

@Data
public class RegisterDto {

	private String fullname;
	private String email;
	private String password;

	public RegisterDto(String fullname, String email, String password) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
