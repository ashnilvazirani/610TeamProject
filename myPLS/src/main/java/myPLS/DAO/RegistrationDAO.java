package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import myPLS.beans.User;

public class RegistrationDAO {

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
	
	public User getUser(String email) {
		final String GET_USER = "SELECT email, role, authorized, password FROM user where email = ?";
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
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		return user;
	}

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


}
