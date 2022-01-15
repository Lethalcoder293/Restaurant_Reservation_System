/* Version 2.0
 * @author: Sanskar
 * Last Edit at: 31 October 2021 at 12.40 PM SGT
 * Last Edit by: Harsh*/


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Order {
	private ArrayList<Integer> itemlist=new ArrayList<Integer>();
	private ArrayList<Integer> combolist=new ArrayList<Integer>();
	private int empid;
	private int tableNo;
	public void AddItem(int index)
	{
		itemlist.add(index);
	}
	public void AddCombo(int index)
	{
		combolist.add(index);
	}
	public void Removeitem(int index)
	{
		itemlist.remove(index);
	}
	public void Removecombo(int index)
	{
		combolist.remove(index);
	}
	public void setstaffid(int index)
	{
		empid=index;
	}
	public void settableno(int index)
	{
		tableNo=index;
	}
	public int gettableNo()
	{return this.tableNo;}
	public void printBill(boolean M)
	{
		System.out.println("*****************************************************");
		System.out.println(Restaurant.name + "\n");
		System.out.println("Served by: " + StaffList.getName(this.empid));
		System.out.println("Table no: " + this.tableNo);
		System.out.println(LocalDate.now() + " " + LocalTime.now());
		System.out.println("");
		System.out.println("Item\t\t\t\t\tPrice");
		double sum=0;
		for(int i:itemlist)
		{
			System.out.println(Menu.getDish(i)+"\t\t\t\t"+Menu.getPrice(i)+" SGD");
			sum += Menu.getPrice(i);
			Menu.grossSales[i]++; // Updating the sales counter
		}
		if(M)
		{
			sum -= 0.15*sum;
			System.out.println("Price after membership discount(=15%): "+sum);
		}
		sum += Math.ceil(0.07*sum*100)/100;
		System.out.println("Price after (GST: 7%): "+sum);
		System.out.println("*****************************************************");
		System.out.println("Please visit us again");
		System.out.println("*****************************************************");
	}

}
