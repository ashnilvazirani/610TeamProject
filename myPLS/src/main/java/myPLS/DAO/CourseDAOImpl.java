package myPLS.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Course;

public class CourseDAOImpl implements CourseDAO {
	
	private StreamDAO streamDAO;
	
	public CourseDAOImpl() {
		this.streamDAO = new StreamDAOImpl();
	}

	@Override
	public boolean addCourse(Course course) {
		final String COURSE = "INSERT INTO Course (streamId, courseName, courseDescription, courseDuration,professorId) VALUES (?,?,?,?,?)";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(COURSE)) {
			preparedStatement.setInt(1, course.getStreamId());
			preparedStatement.setString(2, course.getCourseName());
			preparedStatement.setString(3, course.getCourseDescription());
			preparedStatement.setInt(4, course.getCourseDuration());
			preparedStatement.setInt(5, course.getProfessorId());
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
	public boolean updateCourse(Course course) {
		final String UPDATE_STREAM = "UPDATE Course SET streamId = ?, courseName = ? , courseDescription = ?, courseDuration = ? where courseId = ?";
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_STREAM)) {
			preparedStatement.setInt(1, course.getStreamId());
			preparedStatement.setString(2, course.getCourseName());
			preparedStatement.setString(3, course.getCourseDescription());
			preparedStatement.setInt(4, course.getCourseDuration());
			preparedStatement.setInt(5, course.getCourseId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Course> getCourses() {
		final String GET_COURSES = "SELECT * FROM Course";
		List<Course> courses = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
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

	@Override
	public boolean deleteCourse(int id) {
		final String DELETE_COURSE = "DELETE from Course where courseId = ?";
		boolean result = false;
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COURSE)) {
			preparedStatement.setInt(1, id);
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
	public Course getCourse(int id) {
		final String GET_COURSES = "SELECT * FROM Course where courseId = ?";
		Course course = new Course();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				course.setStreamID(result.getInt("streamId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseDuration(result.getInt("courseDuration"));
				course.setCourseDescription(result.getString("courseDescription"));
				course.setCourseId(result.getInt("courseId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}
	
	@Override
	public List<Course> getPreReqOfCourse(int id) {
		final String GET_COURSES_LIST = "select c.streamId,c.courseName,c.courseDuration,,c.courseDuration,p.preReqCourse \n"
				+ "from preRequisiteCourse p, course c where c.courseId = p.preReqCourse and p.courseId = ?";
		List<Course> courses = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES_LIST)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Course course = new Course();
				course.setStreamID(result.getInt("streamId"));
				course.setCourseName(result.getString("courseName"));
				course.setCourseDuration(result.getInt("courseDuration"));
				course.setCourseDescription(result.getString("courseDescription"));
				course.setCourseId(result.getInt("preReqCourse"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> getCourseById(int id) {
		final String GET_COURSES = "SELECT * FROM Course where professorId = ?";
		List<Course> courses = new ArrayList<Course>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
			preparedStatement.setInt(1, id);
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
    
}
