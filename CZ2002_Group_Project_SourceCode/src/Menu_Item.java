/* Version 1.1
 * @author: Kshitij
 * Last Edit at: 11 October 2021 at 7.30 PM SGT
 * Last Edit by: Harsh*/
public class Menu_Item 
{
	private	String dish;
	private float price;
	private String description;
	private String category;
	
	public Menu_Item(String dish, float price, String description, String category) 
	{
		this.dish = dish;
		this.price = price;
		this.description = description;
		this.category = category;
	}
	public String getDish() 
	{
		return dish;
	}
	public float getPrice() 
	{
		return price;
	}
	public String getDescription() 
	{
		return description;
	}
	public String getCategory() 
	{
		return category;
	}
	public void setDish(String a)
	{
		dish = a;
	}
	public void setPrice(float p)
	{
		price = p;
	}
	public void setDescription(String a)
	{
		description = a;
	}
	public void setCategory(String a)
	{
		category = a;
	}
}
