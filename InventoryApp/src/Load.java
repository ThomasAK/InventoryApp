import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class Load {

	public Users getUsers() {
		//Create file reader
		try (BufferedReader input = Files.newBufferedReader(Paths.get("./src/Users.xml"))) {
			//create Users
			Users users;
			try {
				//load xml file int users
				users = JAXB.unmarshal(input, Users.class);
				//if xml file is blank or broken create new users
			} catch (Exception e) {
				users = new Users();
			}
			//return users 
			return users;
		} catch (IOException ioException) {

		}
		return null;
	}

	public PerishableItems getPerishableItems() {
		try (BufferedReader input = Files.newBufferedReader(Paths.get("./src/PerishableItem.xml"))) {
			PerishableItems items;
			try {
			items = JAXB.unmarshal(input, PerishableItems.class);
			}catch(Exception e){
				items = new PerishableItems();
			}
			return items;
		} catch (IOException ioException) {

		}
		return null;
	}
	public NonPerishableItems getNonPerishableItems() {
		try (BufferedReader input = Files.newBufferedReader(Paths.get("./src/NonPerishableItem.xml"))) {
			NonPerishableItems items;
			try {
			items = JAXB.unmarshal(input, NonPerishableItems.class);
			}catch(Exception e){
				items = new NonPerishableItems();
			}
			return items;
		} catch (IOException ioException) {

		}
		return null;
	}
	
	public Users getActiveUser() {
		//Create file reader
		try (BufferedReader input = Files.newBufferedReader(Paths.get("./src/Active.xml"))) {
			//create Users
			Users users;
			try {
				//load xml file int users
				users = JAXB.unmarshal(input, Users.class);
				//if xml file is blank or broken create new users
			} catch (Exception e) {
				User user = new User("Admin","Admin","Admin");
				users = new Users();
				users.getUsers().add(user);
			}
			//return users 
			return users;
		} catch (IOException ioException) {

		}
		return null;
	}

}
