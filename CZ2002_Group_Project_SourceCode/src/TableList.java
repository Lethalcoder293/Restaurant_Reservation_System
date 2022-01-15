import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/* Version 1.2
 * @author: Harsh
 * Last Edit at: 29 October 2021 at 6.42 PM SGT
 * Last Edit by: Harsh*/

public class TableList 
{
	static Table[] tables = new Table[25];
	public static void createTable(int capacity)
	{
		for(int i = 0; i < 25; i++)
		{
			if(tables[i] == null)
			{
				tables[i] = new Table(capacity);
				break;
			}
		}
	}
	public static boolean changeCapacity(int tableNo, int capacity) // returns true if successful and false if not
	{
		if(tables[tableNo] == null)
			return false;
		tables[tableNo].setCapacity(capacity);
		return true;
	}
	public static void displayTables()
	{
		System.out.println("Table No\tCapacity");
		for(int i = 0; i < 25; i++)
		{
			if(tables[i] == null)
				continue;
			System.out.println(i + "\t\t" + tables[i].capacity());
		}
	}
	public static void saveTables()
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("TableList.txt");
			PrintWriter pw = new PrintWriter(fos);
			/* Convention for Table
			 * Index
			 * Capacity */
			for(int i = 0; i < 25; i++)
			{
				if(tables[i] == null)
					continue;
				pw.println(i);
				pw.println(tables[i].capacity());
			}
			pw.println("-1");
			pw.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	public static void loadTables()
	{
		int index;
		int capacity;
		boolean reserved;
		try 
		{
			FileInputStream fis = new FileInputStream("TableList.txt");
			Scanner sc2 = new Scanner(fis);
			while(sc2.hasNext())
			{
				index = sc2.nextInt();
				if(index == -1)
					break;
				capacity = sc2.nextInt();
				createTable(capacity);
			}
			sc2.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error - Table File Not Found");
		}
		catch (Exception e)
		{
			System.out.println("Error - Table File Corrupted");
		}
	}
}
