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
@XmlRootElement(name="PerishableItems")
public class PerishableItems {
	@XmlElement (name="PerishableItem")
	private List<PerishableItem> items = new ArrayList<>();
	
	public List<PerishableItem> getItems() {return items;}	
	
	public int getSize() {return items.size();}
	
	public PerishableItem getItem(int i) {return items.get(i);}
}
