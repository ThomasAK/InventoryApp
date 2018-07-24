/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class ValidateInput {
	/****************************************************
	 * Method     : validateSKU
	 *
	 * Purpose    : Checks whether a SKU matches the rule.
	 *
	 * Parameters : sku - String representation of a SKU
	 *
	 * Returns    : boolean - Says whether the String matches
	 * 							the expected format.
	 *
	 ****************************************************/
	public boolean validateSKU(String sku)
	{
		return sku.matches("\\d{3}-\\d{4}");
		//###-####
	}
	
	/****************************************************
	 * Method     : validateDate
	 *
	 * Purpose    : Checks whether a date matches the rule
	 *
	 * Parameters : date - String representation of date.
	 *
	 * Returns    : boolean - Says whether the String matches
	 * 							the expected format.
	 *
	 ****************************************************/
	public boolean validateDate(String date)
	{
		return date.matches("\\d{1,2}-\\d{1,2}-\\d{2,4}");
		//##-##-#### #-#-## any combo
	}
	
	/****************************************************
	 * Method     : validateMoney
	 *
	 * Purpose    : Checks whether a money value matches the rule.
	 *
	 * Parameters : money - String representation of money
	 *
	 * Returns    : boolean - Says whether the String matches
	 * 							the expected format.
	 *
	 ****************************************************/
	public boolean validateMoney(String money)
	{
		return money.matches("\\d+.\\d{0,2}");
		//(at least 1 number).##
	}
	
	/****************************************************
	 * Method     : validateQuantity
	 *
	 * Purpose    : Checks whether a quantity value matches the rule.
	 *
	 * Parameters : quantity - String representation of a quantity.
	 *
	 * Returns    : boolean - Says whether the String matches
	 * 							the expected format.
	 *
	 ****************************************************/
	public boolean validateQuantity(String quantity)
	{
		return quantity.matches("\\d+");
		//(Any number of numbers)
	}
}
