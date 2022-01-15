
/* Version: 6.1
 * @author: Sanskar
 * Last edit at: 6 November 2021 at 12.51PM SGT
 * Last Edit by: Harsh*/
import java.util.*;
import java.time.LocalTime;
import java.util.Scanner;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Restaurant {
	static String name = ""; // name of restaurant
	private static Reservation[] reservation = new Reservation[50];
	private static ArrayList<Order> orders = new ArrayList<Order>(); // ArrayList of orders
	static Scanner sc = new Scanner(System.in); // scanner object for this class
	static int overbookingTime = 60; // Minimum time separation between 2 reservations on same table

	/** Saves the Restaurant Details in a text file */
	public static void saveRestDetails() {

		try {
			FileOutputStream fos = new FileOutputStream("MainDetails.txt");
			PrintWriter pw = new PrintWriter(fos);
			pw.println(name);
			pw.println(overbookingTime);
			pw.println(Reservation.delayTime);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** Loads the Restaurant Details from a text file */
	public static void loadRestDetails() {
		try {
			FileInputStream fis = new FileInputStream("MainDetails.txt");
			Scanner sc2 = new Scanner(fis);
			name = sc2.nextLine();
			overbookingTime = sc2.nextInt();
			Reservation.delayTime = sc2.nextInt();
			sc2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error - Main Details Not Found");
		} catch (Exception e) {
			System.out.println("Error - Main Details File Corrupted");
		}
	}

	/**
	 * Goes through all reservations and cancels those in which XX mins from time
	 * has passed and customer hasn't arrived XX mins is decided by variable
	 * delayTime in the Reservation class
	 */
	public static void cleanReservation() {

		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i] == null) // To prevent NullPointer Exception
				continue;
			if (reservation[i].autoCancelReservation())
				reservation[i] = null;
		}
	}

	/**
	 * Takes customer details and adds a new reservation (if table is available at
	 * requested time) Also calls cleanReservation() - to remove any reservations
	 * that might be occupying a table
	 */
	public static void addReservation() {

		cleanReservation();
		int pax = 0;
		LocalTime timeOfReservation = null;
		try {
			System.out.println("Enter the number of persons: ");
			pax = sc.nextInt();
			System.out.println("Enter time of reservation (hh:mm:ss) : ");
			timeOfReservation = LocalTime.parse(sc.next());
		} catch (Exception e) {
			System.out.println("Bad input try again!");
			return; // Otherwise it will run the rest of the function with faulty values
		}
		int tableNo = -1;
		boolean conflict;
		for (int i = 0; i < TableList.tables.length; i++) {
			if (TableList.tables[i] == null) // To avoid Null pointer error
				continue;
			if ((TableList.tables[i].capacity() >= pax)) {
				conflict = false;
				for (int j = 0; j < reservation.length; j++) {
					if (reservation[j] == null) // To avoid Null pointer error
						continue;
					if (reservation[j].getTableNo() == i
							&& MINUTES.between(reservation[j].getTimeReserved(), timeOfReservation) < overbookingTime) {
						// If the same table is already booked within 60 mins of the reservation there
						// is a conflict
						conflict = true;
						break;
					}
				}
				if (conflict == false) {
					tableNo = i;
					break;
				}
			}
		}

		if (tableNo == -1) {
			System.out.println("No tables are currently available");
			return;
		}
		try {
			// Table found at required time so now get details
			System.out.println("Enter the Name : ");
			sc.nextLine();
			String name = sc.nextLine(); // added to fix java bug
			System.out.println("Enter the contact number: ");
			long mobnum = sc.nextInt();

			for (int i = 0; i < reservation.length; i++) {
				if (reservation[i] == null) {
					reservation[i] = new Reservation(tableNo, timeOfReservation, false, name, mobnum, pax);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Bad Input! Try Again");
			addReservation();
			return;
		}
	}

	/**
	 * @param num Mobile Number of customer
	 * @return index of reservation in reservation array (if found)
	 * @return -1 (if reservation not found)
	 */
	public static int findReservation(long num) {

		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i] == null)
				continue;
			if (reservation[i].contactNo() == num) {
				return i;
			}

		}
		return -1;
	}

	/**
	 * @param name Name of customer
	 * @return int index of reservation in reservation array (if found)
	 * @return -1 (if reservation not found)
	 */
	public static int findReservation(String name) {

		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i].getName() == name) {
				return i;
			}

		}
		return -1;
	}

	/**
	 * Takes customer's mobile number (from I/O) to find reservation and notes that
	 * the customer has arrived (This is to prevent reservation from expiring and
	 * then being deleted by cleanReservation() when customer is seated) reservation
	 * will not be serviced is customer is too early (such that there is no conflict
	 * with another customer who reserved earlier)
	 */
	public static void serviceReservation() {
		System.out.println("Enter the mobile no.");
		int index = findReservation(sc.nextInt());
		if (index == -1) {
			System.out.println("No reservation found!");
			return;
		}
		if (MINUTES.between(LocalTime.now(), reservation[index].getTimeReserved()) > overbookingTime) {
			System.out.println("Too early! Please wait for some time");
			return;
		}
		reservation[index].setHasArrived(true);
	}

	/**
	 * Takes customer's contact number or name from I/O to manually cancel the
	 * reservation
	 */
	public static void cancelReservation() {
		System.out.println("Enter the contact number: ");
		long mobnum = sc.nextInt();

		int rnum = findReservation(mobnum);
		if (rnum != -1) {
			reservation[rnum] = null;
			return;
		}

		// if number is not available, we check with name
		System.out.println("Enter the Name : ");
		String name = sc.nextLine();
		rnum = findReservation(name);
		if (rnum != -1) {
			reservation[rnum] = null;
			return;
		}
		System.out.println("Reservation not available... Would you like to try again (y/n)?");
		if (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y') {
			cancelReservation();
		}
		return;
	}

	/** Prints all reservations for staff to see */
	public static void printReservations() {
		System.out.println("Name\t\tContact no.\tpax\ttable no.\ttime");
		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i] == null)
				continue;
			System.out.println(
					reservation[i].getName() + "\t\t" + reservation[i].contactNo() + "\t" + reservation[i].getPax()
							+ "\t" + reservation[i].getTableNo() + "\t\t" + reservation[i].getTimeReserved());
		}
	}

	/**
	 * Takes table no & employee ID (from I/O) and then takes order using indexes of
	 * items in the Menu. This is then stored in the ArrayList orders
	 */
	public static void takeOrder() {
		int x = -1;
		Order o = new Order();
		System.out.println("Please enter employee id ");
		x = sc.nextInt();
		System.out.println("Enter the table no: ");
		int table_no = sc.nextInt();
		o.setstaffid(x);
		o.settableno(table_no);
		System.out.println("Please enter the indexes of the  items that you want to add to the order!When you are done"
				+ " please enter -1 ");
		do {
			x = sc.nextInt();
			if (x == -1)
				break;
			o.AddItem(x);
		} while (x != -1);
		orders.add(o);

	}

	/**
	 * Takes Table No (from I/O) and prints bill Also deletes the corresponding
	 * order from the orders ArrayList Also cancels the corresponding reservation
	 * (and frees up the table)
	 */
	public static void printbill() {
		System.out.println("Enter tableno: ");
		int tableNo = sc.nextInt();
		System.out.println("Is membership valid?(true/false)");
		boolean b = sc.nextBoolean();
		for (Order o : orders) {
			if (o.gettableNo() == tableNo) {
				o.printBill(b);
				orders.remove(o);// Removing an order from the list once the bill has been printed
				break;
			}
		}

		// Now to cancel the reservation
		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i] == null) // To avoid null pointer exception
				continue;
			if (reservation[i].getTableNo() == tableNo && reservation[i].getTimeReserved().isBefore(LocalTime.now())) {
				reservation[i] = null;
				break;
			}
		}
	}
}
