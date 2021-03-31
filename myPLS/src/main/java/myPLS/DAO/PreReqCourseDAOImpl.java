package myPLS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myPLS.beans.Course;

public class PreReqCourseDAOImpl implements PreReqCourseDAO{
	
	private CourseDAO courseDAO; 
	
	public PreReqCourseDAOImpl() {
		courseDAO = new CourseDAOImpl();
	}
	
	@Override
	public boolean addCourse(int courseId, int preReqCourseId) {
		
		final String COURSE = "INSERT INTO PreRequisiteCourse (courseId,preReqCourse) VALUES (?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(COURSE)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, preReqCourseId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteCourse(int courseId, int preReqCourseId) {
		final String DELETE_COURSE = "DELETE from PreRequisiteCourse where courseId = ? and preReqCourse= ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COURSE)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, preReqCourseId);
			int row = preparedStatement.executeUpdate();
			result = row > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Map<Integer,List<Integer>> getCourses() {
		final String GET_COURSES = "SELECT * FROM PreRequisiteCourse";
		Map<Integer,List<Integer>> courses = new HashMap<Integer, List<Integer>>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				if(courses.get(result.getInt("courseId")) != null ) {
					courses.get(result.getInt("courseId")).add(result.getInt("preReqCourse"));
				} else {
					courses.put(result.getInt("courseId"),new ArrayList<Integer>());
					courses.get(result.getInt("courseId")).add(result.getInt("preReqCourse"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	@Override
	public List<Course> getPreReqCoursesOfCourse(int courseId) {
		final String GET_COURSES = "SELECT * FROM PreRequisiteCourse where courseId=?";
		List<Course> courses = new ArrayList<Course>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
			preparedStatement.setInt(1, courseId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				courses.add(courseDAO.getCourse(result.getInt("preReqCourse")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}
}
