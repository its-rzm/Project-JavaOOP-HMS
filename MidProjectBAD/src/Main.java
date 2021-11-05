import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

//PACKAGE ROOM & USER
import com.room.ExecutiveRoom;
import com.room.Room;
import com.room.SuiteRoom;
import com.user.Manager;
import com.user.StaffAdmin;
import com.user.User;

public class Main {
	Scanner sc = new Scanner(System.in);
	ArrayList<Room> RoomArr = new ArrayList<>();
	ArrayList<Guest> guestPool = new ArrayList<>();
	ArrayList<Reservation> resPool = new ArrayList<>();
	ArrayList<Consumable> availableMenu = new ArrayList<>();
	Vector<User> userPool = new Vector<>();
	Vector<String> vUser = new Vector<String>();
	Vector<String> vPass = new Vector<String>();

	//OBJECT FOR DEFAULT USER
	StaffAdmin staffadmin1 = new StaffAdmin("staffadmindef", "12345", "Staff Admin");
	Manager manager1 = new Manager("managerdef", "12345", "Manager");

	public void adminPanel(String user) {
		goAway();
		int choice = 0;
		do {
			System.out.println("Server => Login success.");
			System.out.println("Welcome "+user+"\n");
			System.out.println("---------------------------------");
			System.out.println("       *Available Options*       ");
			System.out.println("---------------------------------");
			System.out.println(" - STAFF ADMIN OPTIONS - ");
			System.out.println(" 1. Add Reservation");
			System.out.println(" 2. Service Room");
			System.out.println(" 3. Shutdown");
			System.out.println("---------------------------------");
			System.out.println("Type the command required.");
			System.out.print("Enter Command:: ");
			choice = sc.nextInt(); sc.nextLine();
			System.out.println();
			switch (choice) {
			case 1:
				goAway();
				addReservation();
				goAway();
				break;

			case 2:
				goAway();
				serviceRoom();
				goAway();
				break;

			case 3:
				System.out.println("\nLog Out..");
				sc.nextLine();
				loginSystem();
				break;

			default:
				break;
			}
		} while (choice != 3);

	}

	public void managerPanel(String user) {
		goAway();
		int choice = 0;
		do {
			System.out.println("Server => Login success.");
			System.out.println("Welcome "+user+"\n");
			System.out.println("---------------------------------");
			System.out.println("       *Available Options*       ");
			System.out.println("---------------------------------");
			System.out.println(" - MANAGER OPTIONS - ");
			System.out.println(" 0. Add User\n 1. View All Users");
			System.out.println(" 2. Find User");
			System.out.println(" 3. Remove User\n 4. Update User");
			System.out.println(" 5. Shutdown");
			System.out.println("---------------------------------");
			System.out.println("Type the command required.");
			System.out.print("Enter Command:: ");
			choice = sc.nextInt(); sc.nextLine();
			System.out.println();
			switch (choice) {

			case 0:
				System.out.println("\n       -ADD USER-       ");
				System.out.println("===========================");
				String username, password, role;
				do {
					System.out.print("Choose Role [Manager | Staff Admin]: "); role = sc.nextLine();
				} while (!role.equals("Manager") && !role.equals("Staff Admin"));

				if (role.equals("Manager")) {
					do {
						System.out.print("Username [Must start with 'manager']: "); username = sc.nextLine();
						if (vUser.contains(username)) {
							System.err.println("Username telah digunakan!");
							username = "error";
						}
					} while (!username.startsWith("manager"));
				} else {
					do {
						System.out.print("Username [Must start with 'staffadmin']: "); username = sc.nextLine();
						if (vUser.contains(username)) {
							System.err.println("Username telah digunakan!");
							username = "error";
						}
					} while (!username.startsWith("staffadmin"));
				}

				System.out.print("Password [Automatically Encrypted]: "); password = sc.nextLine();
				
				if (role.equals("Manager")) {
					vUser.add(username);
					vPass.add(password);
				} else {
					vUser.add(username);
					vPass.add(password);
				}
				
				System.out.println("\nBERHASIL DITAMBAHKAN");
				sc.nextLine();
				goAway();
				
				break;

			case 1:
				System.out.println("\n       -ALL USERS-       ");
				System.out.println("===========================");
				if (vUser.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					sc.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vUser.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vUser.get(i), vPass.get(i));
						System.out.println();
					}
					sc.nextLine();
				}
				goAway();
				break;

			case 2:
				String cari;
				System.out.println("\n-CARI DATA USER-");
				System.out.print("Masukan username yang ingin dicari : "); cari = sc.nextLine();
				for (int i = 0; i < vUser.size(); i++) {
					if (vUser.contains(cari)) {
						int a = vUser.indexOf(cari);
						System.out.println("\nUsername "+cari+" ditemukan\n");
						System.out.println("| USERNAME\t\t | PASSWORD\t |");
						System.out.printf("| %-22s | %-13s |", vUser.get(a), vPass.get(a));
						break;
					}
				}
				if (!vUser.contains(cari)) {
					System.out.println("Tidak menemukan username yang dicari");
				}
				goAway();
				sc.nextLine();
				break;

			case 3:
				String hapusUser;
				System.out.println("\n-HAPUS DATA USER-");
				if (vUser.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					sc.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vUser.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vUser.get(i), vPass.get(i));
						System.out.println();
					}
					try {
						System.out.print("\nUser yang ingin dihapus: "); hapusUser = sc.nextLine();
						if(vUser.contains(hapusUser)) {
							int a = vUser.indexOf(hapusUser);
							vUser.remove(hapusUser);
							vPass.remove(a);
							System.out.println("\nBerhasil Hapus User " + (hapusUser));
							sc.nextLine();
						} else {
							System.err.println("Tidak Menemukan Data");
							sc.nextLine();
						}
					} catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						sc.nextLine();
					}
				}
				goAway();
				break;

			case 4:
				String updateUser, updateUsername, updatePassword;
				System.out.println("\n-PERBARUI DATA USER-");
				if (vUser.isEmpty()) {
					System.err.println("TIDAK ADA DATA");
					sc.nextLine();
				} else {
					System.out.println("NO.  | USERNAME\t\t\t\t | PASSWORD\t |");
					for (int i = 0; i < vUser.size(); i++) {
						System.out.printf("%-4d | %-33s | %-13s |", (i+1), vUser.get(i), vPass.get(i));
						System.out.println();
					}
					try {
						System.out.print("\nUser yang ingin diperbarui: "); updateUser = sc.nextLine();
						if(vUser.contains(updateUser)) {
							int a = vUser.indexOf(updateUser);
							System.out.print("Username : "); updateUsername = sc.nextLine();
							System.out.print("Password : "); updatePassword = sc.nextLine();
							vUser.set(a, updateUsername);
							vPass.set(a, updatePassword);
							System.out.println("\nBerhasil Perbarui User " + (updateUser));
							sc.nextLine();
						} else {
							System.err.println("\nTidak Menemukan user " + (updateUser));
							sc.nextLine();
						}
					}
					catch(Exception E) {
						System.err.println("Tidak Menemukan Data");
						sc.nextLine();
					}
				}
				goAway();
				break;
				
			case 5:
				System.out.println("\nLog Out..");
				sc.nextLine();
				loginSystem();
				break;

			default:
				break;
			}
		} while (choice != 5);
	}

	public void addReservation() {
		Calendar currentTime = Calendar.getInstance();
		Calendar tempCalCheckIn = Calendar.getInstance();
		Calendar tempCalCheckOut = Calendar.getInstance();
		String chosenRoomType = null, name, phonum = null;
		int chosenRoomNo, tahunCheck, bulanCheck, tanggalCheck;
		sc.nextLine();
		do {
			System.out.print("Input customer name : ");
			name = sc.nextLine();
		} while (name.isEmpty());

		do {
			System.out.print("Input customer phone number : ");
			phonum = sc.nextLine();
		} while (checkForNumber(phonum) == false);	
		Guest tempGuest = new Guest(name,phonum);
		guestPool.add(tempGuest);

		do {
			System.out.print("Choose room to book [Standard 500k/night, Suite 750k/night, Executive 1m/night] : ");
			chosenRoomType = sc.nextLine();
		} while (!chosenRoomType.equals("Standard") && !chosenRoomType.equals("Suite") && !chosenRoomType.equals("Executive") );
		System.out.println();
		for (Room room : RoomArr) {
			if (room.getRoomType().equals(chosenRoomType)) {
				System.out.println(room.getRoomType() + " Room " + room.getNo() );
			}
		}
		System.out.println();
		boolean skip = false;
		do {
			skip = false;
			System.out.print("Choose available room : ");
			chosenRoomNo = sc.nextInt();
			chosenRoomNo = chosenRoomNo - 1;
			if (RoomArr.get(chosenRoomNo).isOccupied() || !RoomArr.get(chosenRoomNo).getRoomType().equals(chosenRoomType)) {
				System.out.println("Type of the room doesnt match with ordered type");
				skip = true;
			}

		} while (skip == true || chosenRoomNo < 0 || chosenRoomNo > RoomArr.size());
		boolean giantSkip = false;
		int chosenRoomNoLoop = chosenRoomNo + 1;
		do {
			giantSkip = false;
			for (Reservation resList : resPool) {
				if (resList.getRoom().getNo() == chosenRoomNoLoop) {
					System.out.println("Ocuppied : " + "|Room " + resList.getRoom().getNo() + "| " + resList.getCheckIn().get(Calendar.DATE) + " " + monthToString(resList.getCheckIn().get(Calendar.MONTH)) + " " + resList.getCheckIn().get(Calendar.YEAR) 
							+ " Until " + resList.getCheckOut().get(Calendar.DATE) + " " + monthToString(resList.getCheckOut().get(Calendar.MONTH)) + " " + resList.getCheckOut().get(Calendar.YEAR));
				}
			}
			do {
				System.out.print("Input checkin year [Must be in the future] : ");
				tahunCheck = sc.nextInt();
				tempCalCheckIn.set(tahunCheck, currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DATE));
			} while (tempCalCheckIn.before(currentTime));

			do {
				System.out.print("Input checkin month [Must be in the future] : ");
				bulanCheck = sc.nextInt();
				bulanCheck = bulanCheck - 1;
				tempCalCheckIn.set(tahunCheck, bulanCheck, currentTime.get(Calendar.DATE));
			} while (tempCalCheckIn.before(currentTime));

			do {
				System.out.print("Input checkin date [Must be in the future] : ");
				tanggalCheck = sc.nextInt();
				tempCalCheckIn.set(tahunCheck, bulanCheck, tanggalCheck);
			} while (tempCalCheckIn.before(currentTime));
			System.out.println(tempCalCheckIn.getTime());
			do {
				System.out.print("Input checkout year [Must be in the future and before checkIn] : ");
				tahunCheck = sc.nextInt();
				tempCalCheckOut.set(tahunCheck, tempCalCheckIn.get(Calendar.MONTH), tempCalCheckIn.get(Calendar.DATE));
			} while (tempCalCheckOut.before(tempCalCheckIn));

			do {
				System.out.print("Input checkout month [Must be in the future and before checkIn] : ");
				bulanCheck = sc.nextInt();
				bulanCheck = bulanCheck - 1;
				tempCalCheckOut.set(tahunCheck, bulanCheck, tempCalCheckIn.get(Calendar.DATE));
			} while (tempCalCheckOut.before(tempCalCheckIn));

			do {
				System.out.print("Input checkout date [Must be in the future and before checkIn] : ");
				tanggalCheck = sc.nextInt();
				tempCalCheckOut.set(tahunCheck, bulanCheck, tanggalCheck);
			} while (tempCalCheckOut.before(tempCalCheckIn));
			System.out.println(tempCalCheckOut.getTime());

			for (Reservation dro : resPool) {
				if (dro.getRoom().getNo() == chosenRoomNoLoop) {
					if (!(dro.getAvailability(tempCalCheckIn.getTime()) && dro.getAvailability(tempCalCheckOut.getTime()))) {
						System.out.println();
						System.out.println("Date has been booked! please input different date");
						sc.nextLine();
						giantSkip = true;
					} 
				}
			}

			if (giantSkip == false) {
				Guest g1 = new Guest(name, phonum);
				resPool.add(new Reservation(RoomArr.get(chosenRoomNo), tempCalCheckIn, tempCalCheckOut, g1));
				System.out.println("Reservation successful!");
				System.out.println();
			}

		} while (giantSkip);

	}

	public void serviceRoom() {

		int subChoice = 0;
		if (resPool.isEmpty()) {
			System.out.println("No room to service, make a reservation first");
		} else {
			do {
				System.out.println("What service to offer?");
				System.out.println(" 1. Extend Stay");
				System.out.println(" 2. CheckOut");
				System.out.println(" 3. Order Menu");
				System.out.println(" 4. Go back");
				System.out.println("Type the command required.");
				System.out.print("Enter Command:: ");
				subChoice = sc.nextInt(); sc.nextLine();
				switch (subChoice) {
				case 1:
					int daysToExtend, roomToStay = 0;
					goAway();
					showRes();
					do {
						System.out.print("Choose which reservation to extend [1 - " + resPool.size() +"]" + " : ");
						roomToStay = sc.nextInt();
					} while (roomToStay < 1 || roomToStay > resPool.size());

					do {
						System.out.print("How many days to extend? [Minimal 1] : ");
						daysToExtend = sc.nextInt();
					} while (daysToExtend < 1);
					goAway();
					resPool.get(roomToStay - 1).extendStay(daysToExtend);
					System.out.println("Reservation has been extended!");
					break;

				case 2:
					int chosenRes;
					goAway();
					showRes();
					do {
						System.out.print("Choose which room to checkout [1 - " + resPool.size() +"]" + " : ");
						chosenRes = sc.nextInt();
					} while (chosenRes < 1 || chosenRes > resPool.size());
					resPool.remove(chosenRes-1);
					System.out.println("Checkout is successful");
					goAway();
					break;
				case 3:
					goAway();
					orderMenu();
					goAway();
					break;

				default:
					break;
				}
			} while (subChoice !=4);
		}
	}

	public void showRes() {
		for (int i = 0; i < resPool.size(); i++) {

			int roomNumber = i + 1;
			int countA = 0, countB = 0, countC = 0;
			ArrayList<Consumable> consume = resPool.get(i).getMenu();
			System.out.println("ReservationNo : " + roomNumber);
			System.out.println("RoomNo : " + resPool.get(i).getRoom().getNo());
			System.out.println("RoomType : " + resPool.get(i).getRoom().getRoomType());
			System.out.println("Guest : " + resPool.get(i).getGuest().getName());
			if (resPool.get(i).getMenu().isEmpty()) {
				System.out.println("Ordered item : None");
			} else {
				System.out.println("Ordered item : ");

				for (int j = 0; j < consume.size(); j++) {
					if (consume.get(j).getConsumableName().equals("Ayam goreng C")) {
						countA++;
					} else if(consume.get(j).getConsumableName().equals("Wine")) {
						countB++;
					} else if(consume.get(j).getConsumableName().equals("Nasi")) {
						countC++;
					}
					//System.out.println(" -" + " " + resPool.get(i).getMenu().get(j).getConsumableName() );
				}

				if(countA > 0) {
					System.out.println(" -" + countA + "x Ayam goreng C");}  
				if(countB > 0) {
					System.out.println(" -" + countB + "x Wine");}  
				if(countC > 0) {
					System.out.println(" -" + countC + "x Nasi");}

			}
			System.out.println("TotalBill to pay : " + "Rp. " + resPool.get(i).getBillTotal());
			System.out.println("From : " + resPool.get(i).getCheckIn().getTime() );
			System.out.println("Until : " + resPool.get(i).getCheckOut().getTime() );
			System.out.println();

		}
	}

	public void orderMenu() {
		int chosenRes, itemQuant;
		String orderNumber;
		showRes();
		do {
			System.out.print("Choose which room to input order to [1 - " + resPool.size() +"]" + " : ");
			chosenRes = sc.nextInt();
		} while (chosenRes < 1 || chosenRes > resPool.size());
		System.out.println();
		System.out.println("Choose the menu below");
		chosenRes = chosenRes - 1;
		for (int i = 0; i < availableMenu.size(); i++) {
			int counter = i + 1;
			System.out.println("=============================================================");
			System.out.println("No. " + counter + " " + availableMenu.get(i).getConsumableName());
			System.out.println("Price : Rp." + availableMenu.get(i).getPrice());
			System.out.println("=============================================================");
		}
		sc.nextLine();
		do {

			int parsedOrder;
			System.out.print("Input order number[type DONE to stop ordering] : ");
			orderNumber = sc.nextLine();
			if (orderNumber.equalsIgnoreCase("DONE")) {
				continue;
			} else {
				try {
					parsedOrder = Integer.parseInt(orderNumber);
					if (parsedOrder < 1 || parsedOrder > availableMenu.size()) {
						System.out.println("No menu found for that number");
						continue;
					}
					do {
						System.out.print("Input item quantity : ");
						itemQuant = sc.nextInt();
					} while (itemQuant < 1);
					parsedOrder = parsedOrder - 1;
					for (int i = 0; i < itemQuant; i++) {
						Consumable item = availableMenu.get(parsedOrder);
						int billTotal = resPool.get(chosenRes).getBillTotal();
						resPool.get(chosenRes).getMenu().add(item);
						resPool.get(chosenRes).setBillTotal(billTotal + item.getPrice());
					}
					System.out.println(itemQuant + " " + availableMenu.get(parsedOrder).getConsumableName() + " Has been successfully ordered!"  );
					System.out.println();
					sc.nextLine();
				} catch (Exception e) {
					System.out.println("Please only input numeric data");
				}
			}

		} while (!orderNumber.equalsIgnoreCase("DONE"));

	}

	public void loginSystem() {
		goAway();
		System.out.println("             -HMS Login System-              ");
		System.out.println("+===========================================+");
		int userLoginLimit = 5;
		int passLoginLimit = 5;
		String user, pass;

		do {
			System.out.print("| USERNAME | : "); user = sc.nextLine();
			if (!(vUser.contains(user))) {
				System.err.println("Username Salah! Sisa Percobaan = "+userLoginLimit);
				sc.nextLine();
			}
			userLoginLimit--;
			if (userLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL, TERMINATE APP, SAVED LOG!");
				sc.nextLine();
				return;
			}
		} while (!(vUser.contains(user)));

		int matchUser = vUser.indexOf(user);
		int matchPass = 0;

		do {
			System.out.print("| PASSWORD | : "); pass = sc.nextLine();
			matchPass = vPass.indexOf(pass, matchUser);
			if (!(vPass.contains(pass))) {
				System.err.println("Password Salah! Sisa Percobaan = "+passLoginLimit);
				sc.nextLine();
			}else if (matchUser != matchPass) {
				System.err.println("Password Salah! Sisa Percobaan = "+passLoginLimit);
				sc.nextLine();
			}
			passLoginLimit--;
			if (passLoginLimit == 0) {
				System.err.println("\nLOGIN GAGAL, TERMINATE APP, SAVED LOG!");
				sc.nextLine();
				return;
			}
		} while ((!vPass.contains(pass)) || matchUser != matchPass);

		System.out.println("\nBERHASIL LOGIN");
		if (vUser.get(matchUser).contains("manager")) {
			System.out.println("REDIRECT KE MANAGER PANEL");
			sc.nextLine();
			managerPanel(user);
		} else {
			System.out.println("REDIRECT KE ADMIN PANEL");
			sc.nextLine();
			adminPanel(user);
		}

	}

	public boolean checkForNumber(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public String monthToString(int month) {
		String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		return monthArray[month];
	}

	public void goAway() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				return;
			} else {
				System.out.print("\033\143");
			}
		} catch (Exception ex) {
			for (int i = 0; i < 49; i++) {
				System.out.println("\n");
			}
		}
	}

	public Main() {

		for (int i = 1; i <= 15 ; i++) {
			if (i <= 5) {
				RoomArr.add(new Room(i));
			} else if(i > 5 && i <= 10) {
				RoomArr.add(new ExecutiveRoom(i));
			} else {
				RoomArr.add(new SuiteRoom(i));
			}
		}

		userPool.add(staffadmin1);
		userPool.add(manager1);

		for (int i = 0; i < userPool.size(); i++) {
			User user1 = userPool.get(i);
			vUser.add(user1.getUsername());
			vPass.add(user1.getPassword());
		}

		availableMenu.add(new Consumable("Ayam goreng C", 7500));
		availableMenu.add(new Consumable("Wine", 500000));
		availableMenu.add(new Consumable("Nasi", 5000));

		Calendar calStart = Calendar.getInstance();
		calStart.set(2021, 10, 10);
		Calendar calAkhir = Calendar.getInstance();
		calAkhir.set(2021, 10 , 20);

		Calendar calStart1 = Calendar.getInstance();
		calStart1.set(2021, 11, 8);
		Calendar calAkhir1 = Calendar.getInstance();
		calAkhir1.set(2021, 11, 10 );
		resPool.add(new Reservation(RoomArr.get(0), calStart1, calAkhir1,new Guest("dummy1", "123")));
		resPool.add(new Reservation(RoomArr.get(0), calStart ,calAkhir, new Guest("dummy2", "123")));
		resPool.add(new Reservation(RoomArr.get(7), calStart ,calAkhir, new Guest("dummy2", "123")));

		//LOGIN PAGE
		loginSystem();

	}

	public static void main(String[] args) {
		new Main();
	}

}
