package com.room;
public class ExecutiveRoom extends Room {
	private static int price = 1000000;
	private String roomType = "Executive";
	public ExecutiveRoom(int no) {
		super(no);
	}
	
	public String getRoomType() {
		return this.roomType;
	}
	
	public int getPrice() {
		return price;
	}

}
