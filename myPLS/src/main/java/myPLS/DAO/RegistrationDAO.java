package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.User;


public class RegistrationDAO {

    public Map<Integer, User> getRegisteredUserDetails(){
        final String selectQuery = "SELECT * FROM USER";
	 try (Connection conn = JDBCConnection.geConnection();
        Statement stmt = conn.createStatement();) {
        ResultSet rs = stmt.executeQuery(selectQuery);
        Map<Integer, User> data = new HashMap<>();
        while(rs.next()){
            User u = new User(rs.getString("name"), rs.getString("email"), rs.getBoolean("authorized"));
            data.put(rs.getInt("userID"), u);
        }
        return data;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }
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

}
