import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.room.Room;

public class Reservation {
	private Room room;
	private Guest cust;
	private int billTotal;
	private int rentTotal;
	private Calendar checkIn = Calendar.getInstance();
	private Calendar checkOut = Calendar.getInstance();
	private ArrayList<Consumable> menu = new ArrayList<>();

	public Reservation(Room room, Calendar checkIn, Calendar checkOut, Guest cust) {
		this.room = room;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.cust = cust;
		long rentSub = this.checkOut.getTimeInMillis() - this.checkIn.getTimeInMillis();
		this.rentTotal = (this.room.getPrice() * ((int) rentSub / (1000*60*60*24)));
		this.billTotal = billTotal + rentTotal;
	}

	public boolean getAvailability(Date checkDate ) {
		return ((checkDate.equals(checkIn.getTime()) || checkDate.equals(checkOut.getTime())) || checkDate.before(checkIn.getTime()) || checkDate.after(checkOut.getTime()));
	} 

	public Room getRoom() {
		return this.room;
	}

	public Date getStartingDate() {
		return checkIn.getTime();
	}

	public Date getEndDate() {
		return checkOut.getTime();
	}

	public void extendStay(int day) {
		this.checkOut.add(Calendar.DATE, day);

		long msInterval = this.checkOut.getTimeInMillis() - this.checkIn.getTimeInMillis();
		@SuppressWarnings("unused")
		int daysInterval = (int) msInterval / (1000*60*60*24);
		System.out.println("Rent has been extended for " + day + " days");
		int newTotal = this.room.getPrice() * day;
		System.out.println("Added rent price Rp." + newTotal);
		this.rentTotal = newTotal;
		this.billTotal = billTotal + rentTotal;
	}

	public Calendar getCheckIn() {
		return checkIn;
	}

	public Calendar getCheckOut() {
		return checkOut;
	}

	public int getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(int billTotal) {
		this.billTotal = billTotal;
	}

	public Guest getGuest() {
		return cust;
	}

	public ArrayList<Consumable> getMenu(){
		return menu;
	}

}




