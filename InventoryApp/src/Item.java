/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public abstract class Item 
{

	private String name;
	private String sku;
	private double cost;
	private double price;
	private int qty;
	private String dateRecieved;
	
	public Item() 
	{
		this("","",0,0,0,"");
	}
	public Item(String name,String sku,double cost,double price,int qty, String dateRecieved) 
	{
		this.name = name;
		this.sku = sku;
		this.cost = cost;
		this.price = price;
		this.qty = qty;
		this.dateRecieved = dateRecieved;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
	/**
	 * @return the dateRecieved
	 */
	public String getDateRecieved() {
		return dateRecieved;
	}
	/**
	 * @param dateRecieved the dateRecieved to set
	 */
	public void setDateRecieved(String dateRecieved) {
		this.dateRecieved = dateRecieved;
	}
	
	
}
