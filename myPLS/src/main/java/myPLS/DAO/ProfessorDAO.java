package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.Learner;
public class ProfessorDAO {
    public Map<Integer, Learner> getAllLearners(){
        final String selectQuery = "SELECT * FROM PROFESSOR";
	 try (Connection conn = JDBCConnection.geConnection();
        Statement stmt = conn.createStatement();) {
        ResultSet rs = stmt.executeQuery(selectQuery);
        Map<Integer, Learner> data = new HashMap<>();
        while(rs.next()){
            Learner u = new Learner();
            data.put(rs.getInt("learnerID"), u);
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
}
