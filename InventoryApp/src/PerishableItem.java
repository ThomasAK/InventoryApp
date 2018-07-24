/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class PerishableItem extends Item {

	private String expirationDate;
	
	public PerishableItem()
	{
		super();
		expirationDate = "";
	}
	public PerishableItem(String name,String sku,double cost,double price,int qty, String dateRecieved,String expirationDate)
	{
		super(name,sku,cost,price,qty,dateRecieved);
		this.expirationDate = expirationDate;
	}
	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
}
