/* Version 7.0
 * @author: Harsh
 * Last Edit at: 31 October 2021 at 12.11 PM SGT
 * Last Edit by: Harsh*/
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class AdminMatters {

	static Scanner sc = new Scanner(System.in);
	public static void adminMatters()
	{
		
		int choice = 0;
		while(choice != 20)
		{
			System.out.println("Enter Your Choice: ");
			System.out.println("1)Display Menu\n2)Add Item to Menu\n3)Remove Item from Menu");
			System.out.println("4)Add Combo to Menu\n5)Remove Combo from Menu\n6)Save Menu\n7)Add staff member\n"
					+ "8)Remove staff member\n9)Show list of employees\n10)Show employee details\n11)Save staff details");
			System.out.println("12)Create a Table\n13)Change Table Capacity\n14)Display Tables\n15)Save Tables");
			System.out.println("16)Change Delay Time\n17)Change Overbooking Time\n18)Change Restaurant Name\n19)Daily Sales Report\n20)Quit");
			try
			{
				choice = sc.nextInt();
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Bad Input Quiting!");
				choice = 20;
				continue;
			}
			switch(choice)
			{
				case -2: TableList.loadTables();
					break;
				case -1: StaffList.loadStaffList();
					break;
				case 0: Menu.loadMenu();
					break;
				case 1: Menu.PrintStaffMenu();
					break;
				case 2: addItemToMenu();
					break;
				case 3: removeItemFromMenu();
					break;
				case 4: addComboToMenu();
					break;
				case 5: removeComboFromMenu();
					break;
				case 6: Menu.saveMenu();
					break;
				case 7: addstaffmember();
					break;
				case 8: removestaffmember();
			    	break;
				case 9: StaffList.dispStaff();
			    	break;
				case 10: getemployeedetails();
			    	break;
				case 11: StaffList.saveStaffList();
					break;
				case 12: try {
						 	System.out.println("Enter Table Capacity: ");
						 	TableList.createTable(sc.nextInt());
						 }catch(Exception e){
							 System.out.println("Bad Input, Try Again!");
						 }
					break;
				case 13: try {
					 	 	System.out.println("Enter Table No: ");
					 	 	int tableNo = sc.nextInt();
					 	 	System.out.println("Enter New Capacity: ");
					 	 	TableList.changeCapacity(tableNo, sc.nextInt());
					 	 }catch(Exception e){
					 		 System.out.println("Bad Input, Try Again!");
					 	 }
					break;
				case 14: TableList.displayTables();
					break;
				case 15: TableList.saveTables();
					break;
				case 16: System.out.println("Enter new Delay Time: ");
						 Reservation.delayTime = sc.nextInt();
					break;
				case 17: System.out.println("Enter new Overbooking Time: ");
						 Restaurant.overbookingTime = sc.nextInt();
					break;
				case 18: System.out.println("Enter new restaurant name: ");
						 sc.nextLine();
						 Restaurant.name = sc.nextLine();
					break;
				case 19: Menu.printSalesReport();
					break;
				case 20: System.out.println("Quitting Admin Matters");
						 Restaurant.saveRestDetails();
					break;
				default: System.out.println("Bad Input! Try again!");
			}
			
		}
	}
	public static void addItemToMenu()
	{
		if(Menu.isMenuFull())
		{
			System.out.println("Menu is full please remove an item and try again.");
			return;
		}
		String dish = "";
		String desp = "";
		String cat = "";
		float price;
		System.out.println("Enter Dish Name: ");
		sc.nextLine();
		dish = sc.nextLine();
		System.out.println("Enter Dish Description: ");
		desp = sc.nextLine();
		//sc.nextLine();
		System.out.println("Enter Dish Category: ");
		cat = sc.next();
		System.out.println("Enter Dish Price: ");
		price = sc.nextFloat();
		if(!(Menu.addItem(dish, price, desp, cat)))
			System.out.println("Sorry there was an error! Try Again.");
	}
	public static void removeItemFromMenu()
	{
		System.out.println("Enter index to remove: ");
		int index = sc.nextInt();
		if(!(Menu.removeItem(index)))
			System.out.println("Incorrect Input pls try again");
	}
	public static void addComboToMenu()
	{
		if(Menu.isComboFull())
		{
			System.out.println("Combo Menu is full please remove a combo and try again.");
			return;
		}
		int index = 0;
		int[] indexArr = new int[5];
		int noOfItems = 6;
		float oldPrice = (float) 0.0;
		float price;
		while(noOfItems > 5)
		{
			System.out.println("Enter No of Dishes in combo (Must be less than 5): ");
			noOfItems = sc.nextInt();
		}
		for(int i = 0; i < noOfItems; i++)
		{
			System.out.println("Enter index of Combo item "+(i+1)+" : ");
			index = sc.nextInt();
			if(index >= 50 || index < 0)
			{
				System.out.println("Must be between 0 and 49. Try Again!");
				i--;
			}
			else if(Menu.getPrice(index) == -1)
			{
				System.out.println("Menu Item does not exist. Try Again!");
				i--;
			}
			else
			{
				indexArr[i] = index;
				oldPrice += Menu.getPrice(index);
			}
		}
		System.out.print("Enter Price of Combo (Non-Combo Price is S$ " + oldPrice +"): ");
		price = sc.nextFloat();
		if(!(Menu.addCombo(indexArr, price, noOfItems)))
			System.out.println("Sorry there was an error! Try Again.");
	}
	public static void removeComboFromMenu()
	{
		int index = 0;
		while(1 < 2)
		{
			System.out.println("Enter index to remove: ");
			index = sc.nextInt();
			if(index >= 50 && index < 60)
				break;
			else if(index >= 0 && index < 50)
			{
				System.out.println("Combo Indexs start from 50. Would you like to remove Menu item indexed "+index+ "instead ?");
				System.out.println("Pls enter 'true' or 'false'");
				if(sc.nextBoolean())
					break;
			}
			else
				System.out.println("Incorrect Input! Try Again");
		}
		if(!(Menu.removeCombo(index)))
			System.out.println("Incorrect Input pls try again");
	}
	public static void addstaffmember()
	{
		//System.out.println("Enter the details of the staff member(Name,Gender,EmpID,Jobtitle: ");
		System.out.println("Enter Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter Gender: ");
		String gender = sc.next();
		System.out.println("Enter EmpId: ");
		int empId = sc.nextInt();
		System.out.println("Enter Job Title: ");
		sc.nextLine();
		String job_title = sc.nextLine();
		StaffList.Addstaff(name, gender, empId, job_title);
		System.out.println("Staff member added!");
	}
	public static void removestaffmember()
	{
		System.out.println("Enter the EmpID of the employee to be removed: ");
		int x=StaffList.Removestaff(sc.nextInt());
		if(x==0)
		System.out.println("Staff member removed!");
		if(x==1)
	    System.out.println("No such staff member!");
	}
	public static void getemployeedetails()
	{
		System.out.println("Enter the employee id: ");
		StaffList.getDetails(sc.nextInt());
	}

}
