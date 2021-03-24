package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.Learner;
public class LearnerDAO {
    public Map<Integer, Learner> getAllLearners(){
        final String selectQuery = "SELECT * FROM LEARNER";
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
    public boolean enrollLearnerForCourse(int learnerID, int courseID){
        final String LC = "INSERT INTO learner_course (learnerID, courseID) VALUES (?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(LC)) {
			preparedStatement.setInt(1, learnerID);
			preparedStatement.setInt(2, courseID);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
}
