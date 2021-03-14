package myPLS.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.User;
public class UserDAO {
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

	public User getUserByID(int userID){
		final String GET_USER = "SELECT * FROM user WHERE userID = "+userID;
		User user = new User();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_USER)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role"));
				user.setName(result.getString("name"));
				user.setAuthorized(result.getBoolean("authorized"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
    public List<User> getUsers() {
		String sql = "SELECT * FROM user";
		List<User> users = new ArrayList<User>();
		Connection conn = JDBCConnection.geConnection();
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
                String name = result.getString("name");
                String email = result.getString("email");
                int userID = result.getInt("userID");
                String role  = result.getString("role");
				User u = new User(userID, name, email, role);
			    users.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
