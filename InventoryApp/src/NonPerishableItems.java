import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
@XmlRootElement(name="NonPerishableItems")
public class NonPerishableItems {
	@XmlElement (name="NonPerishableItem")
	private List<NonPerishableItem> items = new ArrayList<>();
	
	public List<NonPerishableItem> getItems() {return items;}	
	
	public int getSize() {return items.size();}
	
	public NonPerishableItem getItem(int i) {return items.get(i);}
}
