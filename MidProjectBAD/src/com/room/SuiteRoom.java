package com.room;
public class SuiteRoom extends Room {
	private static int price = 750000;
	private String roomType = "Suite";
	
	public SuiteRoom(int no) {
		super(no);
	}
	
	public String getRoomType() {
		return this.roomType;
	}
	
	@Override
	public int getPrice() {
		return price;
	}

}
