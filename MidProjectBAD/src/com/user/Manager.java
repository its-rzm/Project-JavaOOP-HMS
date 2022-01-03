package com.user;

public class Manager extends User implements Manageable{
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

	@Override
	public boolean access() {
		return false;
	}

	@Override
	public boolean manageUser() {
		return true;
	}
	
}
