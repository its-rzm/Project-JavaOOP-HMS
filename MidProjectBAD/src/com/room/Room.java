package com.room;

public class Room {
	private static int price = 500000;
	private String roomType = "Standard";
	private int no;
	private boolean occupied;

	public Room(int no) {
		this.no = no;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getNo() {
		return this.no;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public String getRoomType() {
		return this.roomType;
	}
	
}
