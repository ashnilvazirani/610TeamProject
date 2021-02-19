package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class RegistrationDAO {

	public boolean saveUser(Map<String, Object> map) {
	 final String INSERT_USER = "INSERT INTO USER (name, e, authorized) VALUES (?,?,?)";
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

}
