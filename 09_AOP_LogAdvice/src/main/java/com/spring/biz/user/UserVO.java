package com.spring.biz.user;

public class UserVO {
	
	private String id;
	private String passward;
	private String name;
	private String role;
	
	public UserVO() {
		System.out.println(">> UserVO() 객체 생성");
	}
	
	public UserVO(String id, String passward, String name, String role) {
		super();
		this.id = id;
		this.passward = passward;
		this.name = name;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", passward=" + passward + ", name=" + name + ", role=" + role + "]";
	}
	
	
}
