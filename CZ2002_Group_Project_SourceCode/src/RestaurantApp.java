import java.util.NoSuchElementException;
import java.util.Scanner;

public class RestaurantApp
{
	public static void main(String[] args) {
		// Loading data from storage
		TableList.loadTables();
		StaffList.loadStaffList();
		Menu.loadMenu();
		Restaurant.loadRestDetails();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice != 8) {
			System.out.println("Enter Your Choice: ");
			System.out.println(
					"1)Admin matters\n2)Take order\n3)printbill\n4)Make Reservation\n5)Service Reservation\n6)Cancel Reservation\n7)Display Reservations\n8)Quit");
			try {
				choice = sc.nextInt();
			} catch (NoSuchElementException e) {
				System.out.println("Bad Input Quiting!");
				choice = 8;
				continue;
			}
			switch (choice) {
			case 1:
				AdminMatters.adminMatters();
				break;
			case 2:
				Restaurant.takeOrder();
				break;
			case 3:
				Restaurant.printbill();
				break;
			case 4:
				Restaurant.addReservation();
				break;
			case 5:
				Restaurant.serviceReservation();
				break;
			case 6:
				Restaurant.cancelReservation();
				break;
			case 7:
				Restaurant.printReservations();
				break;
			case 8:
				System.out.println("Quitting....");
				break;
			default:
				System.out.println("Bad input! Try again");
			}
		}
	}
}
