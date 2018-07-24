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
@XmlRootElement(name="Users")
public class Users {
	@XmlElement (name="User")
	private List<User> users = new ArrayList<>();
	
	public List<User> getUsers() {return users;}	
	
	public int getSize() {return users.size();}
	
	public User getUser(int i) {return users.get(i);}
}

