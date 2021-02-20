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
	public User(){}
	public User(String name, String email, boolean authorized){
		this.name = name;
		this.email = email;
		this.authorized=authorized;
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
	
	@Override
	public String toString(){
		return this.getName()+", "+this.getEmail()+", "+this.isAuthorized();
	}
}
/**
 * SQL Statements: 
 CREATE TABLE user (
    userID int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255),
    authorized boolean,
    PRIMARY KEY (userID)
);

select * from user;

 */