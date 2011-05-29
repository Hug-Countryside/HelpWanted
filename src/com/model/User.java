package com.model;

public class User {
	private String id;
	private String username;
	private String phone;

	// 设置
	public void setId(String id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// 提取
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public String getId() {
		return id;
	}

}
