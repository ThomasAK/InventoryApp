/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class User {
	
	private String id;
	private String password;
	private String accessLevel;
	
	public User()
	{
		this("","","");
	}
	public User(String id,String password,String accessLevel)
	{
		this.id = id;
		this.password = password;
		this.accessLevel = accessLevel;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the accessLevel
	 */
	public String getAccessLevel() {
		return accessLevel;
	}
	/**
	 * @param accessLevel the accessLevel to set
	 */
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	

}
