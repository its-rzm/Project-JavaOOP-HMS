package com.user;

public class StaffAdmin extends User {
	private String roleAcc = "Staff Admin";

	public StaffAdmin(String username, String password, String roleAcc) {
		super(username, password);
		this.roleAcc = roleAcc;
	}

	public String getRoleAcc() {
		return roleAcc;
	}

	public void setRoleAcc(String roleAcc) {
		this.roleAcc = roleAcc;
	}
	
}
