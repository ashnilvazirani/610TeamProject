package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.Stream;


public class StreamDAO {
    public Map<Integer, Stream> getAllStreams(){
        final String selectQuery = "SELECT * FROM STREAM";
	 try (Connection conn = JDBCConnection.geConnection();
        Statement stmt = conn.createStatement();) {
        ResultSet rs = stmt.executeQuery(selectQuery);
        Map<Integer, Stream> data = new HashMap<>();
        while(rs.next()){
            Stream u = new Stream(rs.getString("streamName"), rs.getInt("streamDuration"), rs.getString("streamDescription"));
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
    public boolean saveStreamDetails(Map<String, Object> map) {
        final String INSERT_USER = "INSERT INTO STREAM (streamName, streamDuration, streamDescription) VALUES (?,?,?)";
        boolean result = false;
        try (Connection conn = JDBCConnection.geConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, "TEMP"); //map.get("name").toString()
            preparedStatement.setInt(2, 1); //map.get("email").toString()
            preparedStatement.setString(3, "INSERT TEST, model design");
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
