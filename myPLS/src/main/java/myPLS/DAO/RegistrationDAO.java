package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import myPLS.beans.User;

/**
 *  The RegistrationDAO class to add, update user details into and from database
 * @author abriti
 *
 */

public class RegistrationDAO {

	/**
	 *  This saveUser method will save the user details in database
	 * @param map user details
	 * @return the user is authorized or not
	 */
	public boolean saveUser(Map<String, Object> map) {
	 final String INSERT_USER = "INSERT INTO USER (name, email, authorized) VALUES (?,?,?)";
	 boolean result = false;
	 try (Connection conn = JDBCConnection.geConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USER)) {
         preparedStatement.setString(1, map.get("name").toString());
         preparedStatement.setString(2, map.get("email").toString());
         preparedStatement.setBoolean(3, (Boolean)map.get("authorized"));
         int row = preparedStatement.executeUpdate();
         result = row>0 ? true : false;
     } catch (SQLException e) {
    	 e.printStackTrace();
     } catch (Exception e) {
         e.printStackTrace();
     }
	 return result;

	}
	
	/**
	 * This updateUser method update the user details in database
	 * @param email the email address of the user
	 * @param role the role of the user (learner, professor, admin)
	 */
	public void updateUser(String email,String role) {
		final String INSERT_USER = "UPDATE USER SET authorized = ?, role = ? where email = ?";
		 try (Connection conn = JDBCConnection.geConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USER)) {
	         preparedStatement.setBoolean(1, true);
	         preparedStatement.setString(2, role);
	         preparedStatement.setString(3, email);
	         preparedStatement.executeUpdate();
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	}
	
	/**
	 * This getUser method will get user details from DB
	 * @param email the email address of the user
	 * @return details of the user (email, role, authorized, password, userID)
	 */
	public User getUser(String email) {
		final String GET_USER = "SELECT  email, role, authorized, password, userID FROM user where email = ?";
        User user = new User();
		 try (Connection conn = JDBCConnection.geConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(GET_USER)) {
	         preparedStatement.setString(1, email);
	         ResultSet result = preparedStatement.executeQuery();
	         while(result.next()) {
	        	 user.setEmail(result.getString(1));
	        	 user.setRole(result.getString(2));
	        	 user.setAuthorized(result.getBoolean(3));
	        	 user.setPassword(result.getString(4));
	        	 user.setUserID(result.getInt(5));
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		return user;
	}

	/**
	 * This updatePassword method will save details related to password of user
	 * @param email the email address of the user
	 * @param password the password of the user
	 * @return the password is set or not
	 */
	public boolean updatePassword(String email, String password) {
		final String INSERT_USER = "UPDATE USER SET password = ? where email = ?";
		 try (Connection conn = JDBCConnection.geConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USER)) {
	         preparedStatement.setString(1, password);
	         preparedStatement.setString(2, email);
	         preparedStatement.executeUpdate();
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		 return true;
	}
	
	/**
	 * This getUsersByRole method will fetch user details from database
	 * @param role the role of the user (learner, professor, admin)
	 * @return details regarding the user
	 */
	public List<User> getUsersByRole(String role) {
		final String GET_USER = "SELECT  email, role, authorized, password, userID FROM user where role = ?";
        List<User> users = new ArrayList<>();
		 try (Connection conn = JDBCConnection.geConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(GET_USER)) {
	         preparedStatement.setString(1, role);
	         ResultSet result = preparedStatement.executeQuery();
	         while(result.next()) {
	        	 User user = new User();
	        	 user.setEmail(result.getString(1));
	        	 user.setRole(result.getString(2));
	        	 user.setAuthorized(result.getBoolean(3));
	        	 user.setPassword(result.getString(4));
	        	 user.setUserID(result.getInt(5));
	        	 users.add(user);
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		return users;
	}


}
