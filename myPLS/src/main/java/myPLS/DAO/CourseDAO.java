package myPLS.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.Course;
import myPLS.beans.Stream;

public class CourseDAO {
    public Map<Integer, Course> getAllCourses(){
        final String selectQuery = "SELECT * FROM course";
	 try (Connection conn = JDBCConnection.geConnection();
        Statement stmt = conn.createStatement();) {
        ResultSet rs = stmt.executeQuery(selectQuery);
        Map<Integer, Course> data = new HashMap<>();
        while(rs.next()){
            Course u = new Course();
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
}
