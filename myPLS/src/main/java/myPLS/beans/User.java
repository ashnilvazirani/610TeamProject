package myPLS.beans;
/**
 * 
 * @author Sandeep kaur
 * @version 1.0
 * myPLS users
 *
 */
public class User {
	private String name;
	private String email;
	private boolean authorized;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAuthorized() {
		return authorized;
	}
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	
	
}
