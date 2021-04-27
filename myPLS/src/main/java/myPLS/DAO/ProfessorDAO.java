package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import myPLS.beans.Course;
import myPLS.beans.Learner;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;

/**
 *  This ProfessorDAO class to get details of the professor
 * @author ashnil
 *
 */
public class ProfessorDAO {
    private CourseService courseService;
    public ProfessorDAO(){
        this.courseService = new CourseServiceImpl();
    }

    /**
     * This getAllLearners method will get all learners assigned to a professor
     * @return all the learners assigned to professor
     */
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
