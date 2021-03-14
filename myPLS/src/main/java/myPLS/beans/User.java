package myPLS.beans;


public class User {
	private int userID;
	private String name;
	private String email;
	private boolean authorized;
	private String role;
	private String password;
	
	public User(){}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getUserID() {
		return this.userID;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserNameByID(int userID){
		if(this.userID == userID)
			return this.getName();
		return "NULL";
	}
	public User(int userID, String name, String email, boolean authorized, String role, String password) {
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.authorized = authorized;
		this.role = role;
		this.password = password;
	}
	public User(String name, String email, boolean authorized, String role, String password) {
		this.name = name;
		this.email = email;
		this.authorized = authorized;
		this.role = role;
		this.password = password;
	}
	public User(int userID, String name, String email, String role){
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.role = role;
		
	}
}
