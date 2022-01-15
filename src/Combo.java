/* Version 2.2
 * @author: Kshitij
 * Last Edit at: 29 October 2021 at 2.10 PM SGT
 * Last Edit by: Harsh*/
public class Combo 
{
	public int items[]; //stores the index value of each item in a combo
	private float price;
	public int NoOfItems;
	
	public Combo()
	{
		items = new int[5];
		price = (float) 0.0;
		NoOfItems = 0;
	}
	public Combo(int[] itemsIndexList, float price, int NoOfItems)
	{
		this.price = price;
		this.NoOfItems = NoOfItems;
		this.items = new int[5];
		for(int i = 0; i < 5; i++)
		{
			if(i < NoOfItems)
				this.items[i] = itemsIndexList[i];
			else
				this.items[i] = -1;
		}
	}
	
	
	public void setPrice(float price)
	{this.price = price;}
	public float getPrice()
	{return price;}
	public void printCombo() 
	{
		//System.out.println("The menu items in this combo are:");
		for(int i = 0; i < 5; i++)
		{
			if(items[i] != -1)
				System.out.print(Menu.getDish(items[i])+"\t");
			else
				System.out.print("(No Item)"+"\t");
		}
		System.out.println(this.price);	
	}
}
