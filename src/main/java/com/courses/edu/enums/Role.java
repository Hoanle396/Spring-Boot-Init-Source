package com.courses.edu.enums;

public enum Role {
	ADMIN("ADMIN"), USER("USER"), GUEST("GUEST");

	private String name;

	private Role(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
