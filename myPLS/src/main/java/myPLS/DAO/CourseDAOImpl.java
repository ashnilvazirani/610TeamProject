package myPLS.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupChat;
import myPLS.beans.Stream;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;
import myPLS.services.UserService;
import spark.Request;
import myPLS.beans.Stream;

public class CourseDAOImpl implements CourseDAO {
	private UserService userService;
	private StreamDAO streamDAO;
	private CourseService  courseService;
	public CourseDAOImpl() {
		this.streamDAO = new StreamDAOImpl();
		this.userService = new UserService();
		// this.courseService = new CourseServiceImpl();
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
	public Stream getStreamById(int streamId){
		final String GET_COURSES = "SELECT * FROM stream where streamId = ?";
		Stream stream = new Stream();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSES)) {
			preparedStatement.setInt(1, streamId);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				stream.setStreamDescription(result.getString("streamDescription"));
				stream.setStreamID(streamId);
				stream.setStreamDuration(result.getInt("streamDuration"));
				stream.setStreamName(result.getString("streamName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
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
	@Override
	public boolean createAGroupForCourse(int professorId, int courseId){
        Course c = getCourse(courseId);
		Stream s = getStreamById(c.getStreamID());
		CourseGroup isPresent = getCourseGroupByCourseId(courseId);
		boolean result = false;
		if(isPresent!=null){
			final String LC = "INSERT INTO courseGroup (userID, courseID, courseName, courseStream, flag) VALUES (?,?,?,?,?)";
			try (Connection conn = JDBCConnection.geConnection();
					PreparedStatement preparedStatement = conn.prepareStatement(LC)) {
				preparedStatement.setInt(1, professorId);
				preparedStatement.setInt(2, courseId);
				preparedStatement.setString(3, c.getCourseName());
				preparedStatement.setString(4, s.getStreamName());
				preparedStatement.setInt(5, 0);
				int row = preparedStatement.executeUpdate();
				result = row > 0 ? true : false;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("GROUP ALREADY EXISTS!!");
		}
		return result;
    }
	@Override
	public CourseGroup getCourseGroupByCourseId(int courseId){
		final String GET_COURSE_GROUP = "SELECT * FROM courseGroup where courseID = ?";
		List<CourseGroup> courses = new ArrayList<CourseGroup>();
		CourseGroup gc = new CourseGroup();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSE_GROUP)) {
			preparedStatement.setInt(1, courseId);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				gc.setCourseGroupID(result.getInt("courseGroupID"));
				gc.setFlag(result.getInt("flag"));
				gc.setCourseName(result.getString("courseName"));
				gc.setCourseStream(result.getString("courseStream"));
				gc.setUserID(result.getInt("userID"));
				gc.setCourseID(result.getInt("courseID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}
	@Override
	public List<CourseGroupChat> getCourseGroupChats(int courseGroupID){
		final String GET_COURSE_GROUP = "SELECT * FROM courseGroupChat where courseGroupID = ?";
		List<CourseGroupChat> groupChats = new ArrayList<CourseGroupChat>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSE_GROUP)) {
			preparedStatement.setInt(1, courseGroupID);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				CourseGroupChat chat = new CourseGroupChat();
				chat.setCourseGroupChatID(result.getInt("courseGroupChatID"));
				chat.setCourseGroupID(result.getInt("courseGroupID"));
				chat.setMessageContent(result.getString("messageContent"));
				chat.setUserID(result.getInt("userID"));
				chat.setUserName(this.userService.getUserByID(result.getInt("userID")).getName());
				groupChats.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupChats;
	}
	@Override
	public boolean postMessageInGroup(CourseGroupChat chat){
		boolean result = false;
		final String LC = "INSERT INTO courseGroupChat (userID, courseGroupID, messageContent, userName, flag) VALUES (?,?,?,?,?)";
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(LC)) {
			preparedStatement.setInt(1, chat.getUserID());
			preparedStatement.setInt(2, chat.getCourseGroupID());
			preparedStatement.setString(3, chat.getMessageContent());
			preparedStatement.setString(4, chat.getUserName());
			preparedStatement.setInt(5, 0);
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
