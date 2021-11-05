package com.user;

public class Manager extends User {
	private String mRole = "Manager";

	public Manager(String username, String password, String mrole) {
		super(username, password);
		mRole = mrole;
	}

	public String getmRole() {
		return mRole;
	}

	public void setmRole(String mRole) {
		this.mRole = mRole;
	}
	
}
