package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myPLS.beans.Course;
import myPLS.beans.Learner;
import myPLS.beans.User;

public class LearnerDAO {


	private StreamDAO streamDAO;

	public LearnerDAO() {
		this.streamDAO = new StreamDAOImpl();
	}

	public Map<Integer, Learner> getAllLearners() {
		final String selectQuery = "SELECT * FROM LEARNER WHERE isDeleted=0";
		try (Connection conn = JDBCConnection.geConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(selectQuery);
			Map<Integer, Learner> data = new HashMap<>();
			while (rs.next()) {
				Learner u = new Learner();
				data.put(rs.getInt("learnerID"), u);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean enrollCourse(Learner learner) {
		final String LEARNER_EROLLMENT = "INSERT INTO LEARNER (userId, courseId, streamId, isDeleted) VALUES (?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(LEARNER_EROLLMENT)) {
			preparedStatement.setInt(1, learner.getUserID());
			preparedStatement.setInt(2, learner.getCourseID());
			preparedStatement.setInt(3, learner.getStreamID());
			preparedStatement.setInt(4, 0);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

    // public Map<Integer, Learner> getAllLearners(){
    //     final String selectQuery = "SELECT * FROM LEARNER";
	//  try (Connection conn = JDBCConnection.geConnection();
    //     Statement stmt = conn.createStatement();) {
    //     ResultSet rs = stmt.executeQuery(selectQuery);
    //     Map<Integer, Learner> data = new HashMap<>();
    //     while(rs.next()){
    //         Learner u = new Learner();
    //         data.put(rs.getInt("learnerID"), u);
    //     }
    //     return data;
    //     }catch (SQLException e) {
    //         e.printStackTrace();
    //         return null;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }    
    // }
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

	public List<Course> getEnrolledCourses(int userId) {
		final String ENROLLED_COURSES = "select * from course where courseId in (select courseId from learner where userId = ? AND isDeleted=0)";
		List<Course> courses = new ArrayList<Course>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(ENROLLED_COURSES)) {
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Course course = new Course();
				course.setStreamID(result.getInt("streamId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseDuration(result.getInt("courseDuration"));
				course.setCourseDescription(result.getString("courseDescription"));
				course.setCourseId(result.getInt("courseId"));
				course.setStreamName(streamDAO.getStream(result.getInt("streamId")).getStreamName());
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;

	}
	
	public List<Course> getUnEnrolledCourses(int userId) {
		final String ENROLLED_COURSES = "select * from course where courseId not in (select courseId from learner where userId = ? AND isDeleted=0)";
		List<Course> courses = new ArrayList<Course>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(ENROLLED_COURSES)) {
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Course course = new Course();
				course.setStreamID(result.getInt("streamId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseDuration(result.getInt("courseDuration"));
				course.setCourseDescription(result.getString("courseDescription"));
				course.setCourseId(result.getInt("courseId"));
				course.setStreamName(streamDAO.getStream(result.getInt("streamId")).getStreamName());
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;

	}
	
	public List<User> getLearnersEnrolledList(int courseId) {
        System.out.println(courseId);
		final String ENROLLED_STUDENTS = "select * from user where userId in (select userId from learner where courseId = ? AND isDeleted=0)";
		List<User> users = new ArrayList<User>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(ENROLLED_STUDENTS)) {
			preparedStatement.setInt(1, courseId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role"));
				user.setName(result.getString("name"));
				user.setAuthorized(result.getBoolean("authorized"));
				user.setUserID(result.getInt("userId"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}

