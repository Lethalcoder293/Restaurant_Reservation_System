import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/* Version 4.0
 * @author: Harsh
 * Last Edit at: 31 October 2021 at 11.50 AM SGT
 * Last Edit by: Harsh*/
public class Menu 
{
	private static Menu_Item[] items = new Menu_Item[50];
	private static int items_size = 0;
	private static Combo[] combos = new Combo[10];
	private static int combos_size = 0;
	static int[] grossSales = new int[60]; // To store the daily amounts of each item sold index > 50 => combo index of index-50
	
	public static String getDish(int index)
	{
		if(index >= 50 && index < 60 && combos[index-50] != null)
			return ("Combo No. " + index);
		else if(index < 50 && items[index] != null)
			return items[index].getDish();
		else
			return "Error - Item Not Found";
	}
	public static String getDescription(int index)
	{
		if(index >= 50 && index < 60 && combos[index] != null)
		{
			String result = "";
			for(int i = 0; i < combos[index - 50].NoOfItems; i++)
			{
				result = result + getDish(combos[index - 50].items[i]);
				if(i != combos[index - 50].NoOfItems - 1)
					result += ", ";
			}
			return result;
		}
		else if(index >= 50 || items[index] == null)
			return "Error - Item Not Found";
		else
			return items[index].getDescription();
	}
	public static float getPrice(int index)
	{
		if(index >= 50 && index < 60 && combos[index - 50] != null)
			return combos[index - 50].getPrice();
		else if(index >= 50 || items[index] == null)
			return -1;
		else
			return items[index].getPrice();
	}
	public static boolean addItem(String dish, float price, String description, String category)
	{
		// This Function adds an item to the menu and returns 1 if successful and 0 if menu is full
		if(items_size >= 50)
			return false; // Cant Add
		int index = findEmptyMenuIndex();
		if(index == -1)
			return false; // Cant Add
		items[index] = new Menu_Item(dish, price, description, category);
		items_size++;
		return true; // Add item successful
	}
	public static boolean removeItem(int index)
	{
		if(index >= 50 && index < 60)
			return removeCombo(index - 50);
		else if(index >= 60)
			return false; // Incorrect Index Entered
		else
		{
			items[index] = null;
			items_size--;
			return true;
		}
	}
	public static boolean removeCombo(int index)
	{
		if(index < 50)
			return removeItem(index);
		else if(index >= 60)
			return false; // Incorrect Index Entered
		else
		{
			combos[index - 50] = null;
			combos_size--;
			return true;
		}
	}
	public static boolean addCombo(int[] itemsIndexList, float price, int NoOfItems)
	{
		// This Function adds an item to the menu and returns 1 if successful and 0 if menu is full
		if(combos_size >= 10)
			return false; // Cant Add
		int index = findEmptyComboIndex();
		if(index == -1)
			return false; // Cant Add
		combos[index] = new Combo(itemsIndexList, price, NoOfItems);
		combos_size++;
		return true; // Add item successful
	}
	private static int findEmptyMenuIndex()
	{
		// This Function finds the nearest empty Menu Index and returns -1 if array is full
		for(int i = 0; i < 50; i++)
		{
			if(items[i] == null)
				return i;
		}
		return -1;
	}
	private static int findEmptyComboIndex()
	{
		// This Function finds the nearest empty Menu Index and returns -1 if array is full
		for(int i = 0; i < 10; i++)
		{
			if(combos[i] == null)
				return i;
		}
		return -1;
	}
	public static boolean isMenuFull() 
	{
		if(items_size == 50)
			return true;
		else
			return false;
	}
	public static boolean isComboFull() 
	{
		if(combos_size == 10)
			return true;
		else
			return false;
	}
	public static void PrintStaffMenu()
	{
		System.out.println("Ala Carte Menu");
		System.out.println("Index\tDish\t\t\tCategory\tPrice");
		for(int i = 0; i < 50; i++)
		{
			if(items[i] == null)
				continue;
			System.out.println(i+"\t"+items[i].getDish()+"\t\t"+items[i].getCategory()+"\t\t"+items[i].getPrice());
		}
		System.out.println("Combo Menu");
		System.out.println("Index\tDish1\t\tDish2\t\tDish3\t\tDish4\t\tDish5\t\tPrice");
		for(int i = 0; i < 10; i++)
		{
			if(combos[i] == null)
				continue;
			System.out.print((50 + i) + "\t");
			combos[i].printCombo();
		}
	}
	public static void saveMenu()
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("Menu.txt");
			PrintWriter pw = new PrintWriter(fos);
			/* Convention for Menu Items
			 * Index
			 * dish
			 * price
			 * description
			 * category*/
			for(int i = 0; i < 50; i++)
			{
				if(items[i] == null)
					continue;
				pw.println(i); // Index
				pw.println(items[i].getDish()); // Dish
				pw.println(items[i].getPrice()); // Price
				pw.println(items[i].getDescription()); // Description
				pw.println(items[i].getCategory()); // Category
			}
			pw.println("-1"); // To signify end of items menu
			/*Convention for Combo Items
			 * Index (Combo) (in array not that input by user(i.e > 50))
			 * ItemIndex1
			 * ItemIndex2
			 * ItemIndex3
			 * ItemIndex4
			 * ItemIndex5
			 * Price*/
			for(int i = 0; i < 10; i++)
			{
				if(combos[i] == null)
					continue;
				pw.println(i);
				for(int j = 0; j < 5; j++)
				{
					pw.println(combos[i].items[j]);
				}
				pw.println(combos[i].getPrice());
			}
			pw.println("-1"); // To signify end of combos menu
			
			pw.close(); //closes the resource - imp or you cant read the file
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	public static void loadMenu()
	{
		int index;
		int[] items_list = new int[5];
		String dish;
		float price;
		String description;
		String category;
		int NoOfItems = 0;
		try 
		{
			FileInputStream fis = new FileInputStream("Menu.txt");
			Scanner sc2 = new Scanner(fis); // Scanner obj to read tfrom the text file
			// Items Menu
			while(sc2.hasNext()) // While we haven't reached the end of the file
			{
				index = sc2.nextInt();
				if(index == -1)
					break;
				sc2.nextLine();
				dish = sc2.nextLine();
				price = sc2.nextFloat();
				sc2.nextLine();
				description = sc2.nextLine();
				category = sc2.next();
				items[index] = new Menu_Item(dish,price,description,category);
			}
			// Combo Menu
			while(sc2.hasNext())
			{
				index = sc2.nextInt();
				if(index == -1)
					break;
				NoOfItems = 0;
				for(int i = 0; i < 5; i++)
				{
					items_list[i] = sc2.nextInt();
					if(items_list[i] != -1)
						NoOfItems++;
				}
				price = sc2.nextFloat();
				combos[index] = new Combo(items_list,price,NoOfItems);
			}
			sc2.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Error - Menu File Corrupted");
		}
		
	}
	public static void printSalesReport()
	{
		float sum = 0;
		System.out.println("-------------------------------------------------------------");
		System.out.println(LocalDate.now() + " at " + LocalTime.now() + "\n");
		System.out.println("Dish\t\t\tAmt Sold\tSales");
		for(int i = 0; i < 60; i++)
		{
			if(i < 50 && items[i] == null)
				continue;
			if(i >= 50 && i < 60 && combos[i-50] == null)
				continue;
			if(i < 50 && items[i] != null)
			{
				System.out.println(items[i].getDish()+"\t\t"+ grossSales[i] + "\t\t S$ " + (items[i].getPrice()*grossSales[i]));
				sum += items[i].getPrice() * grossSales[i];
			}
			else if(i >= 50 && i < 60 && combos[i-50] != null)
			{
				System.out.println("Combo No "+ i +"\t\t"+ grossSales[i] + "\t\t S$ " + (combos[i-50].getPrice()*grossSales[i]));
				sum += combos[i-50].getPrice() * grossSales[i];
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("Total Sales: S$ " + sum);
		System.out.println("-------------------------------------------------------------");
	}
}
