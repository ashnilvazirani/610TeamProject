package myPLS.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.User;

/**
 *   The RITUserDAO class to get RIT users from database
 * @author abriti
 *
 */
public class RITUserDAO {
	
	/**
	 * This getRITUsers method will get user details from database
	 * @return list of all RIT users 
	 */
	public List<User> getRITUsers() {
		String sql = "SELECT * FROM rit_users";
		List<User> users = new ArrayList<User>();
		Connection conn = JDBCConnection.geConnection();
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				User user = new User();
			    user.setEmail(result.getString(2));;
			    user.setRole(result.getString(3));
			    users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
