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
import myPLS.beans.CourseGroupMembers;
import myPLS.beans.Stream;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;
import myPLS.services.LearnerService;
import myPLS.services.UserService;
import spark.Request;
import myPLS.beans.User;

/**
 * The CourseDAOImpl class to get details of course
 * @author sandeep
 *
 */
public class CourseDAOImpl implements CourseDAO {
	private UserService userService;
	private StreamDAO streamDAO;
	private CourseService  courseService;
	private LearnerService learnerService;
	public CourseDAOImpl() {
		this.streamDAO = new StreamDAOImpl();
		this.userService = new UserService();
		this.learnerService = new LearnerService();
	}

	@Override
	/**
	 * This addCourse method will add course 
	 * @param course details of course added by the admin
	 * @return true  if course is added in the system
	 */
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
	public boolean modifyCourse(Course c, String operation){
//		if(operation.equalsIgnoreCase("DELETE")){
////			final String DELETE_COURSE = "UPDATE Course SET isDeleted = 1 WHERE courseId = ?";
////			final String REMOVE_LEARNERS = "UPDATE learner SET isDeleted=1 WHERE courseId=?";
//			try (Connection conn = JDBCConnection.geConnection();
//			PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COURSE)) {
//				preparedStatement.setInt(1, c.getCourseId());
//				preparedStatement.executeUpdate();
//				PreparedStatement ps = conn.prepareStatement(REMOVE_LEARNERS);
//				ps.setInt(1, c.getCourseId());
//				ps.executeUpdate();
//				return true;
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}else if(operation.equalsIgnoreCase("UPDATE")){
//			
//		}
		return false;
	}
	@Override
	/**
	 * This updateCourse method will update course details in system
	 * @param course details of course
	 * @return true if course is updated in the system by admin
	 */
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
	/**
	 * This getCourses method will get course details 
	 * @return list of all courses added by admin
	 */
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
//		final String DELETE_COURSE = "DELETE from Course where courseId = ? and isDeleted=0";
		boolean result = false;
//		try (Connection conn = JDBCConnection.geConnection();
//				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COURSE)) {
//			preparedStatement.setInt(1, id);
//			int row = preparedStatement.executeUpdate();
//			result = row > 0 ? true : false;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}

	@Override
	/**
	 * This getCourse method will get course details from system
	 * @param id id of course
	 * @return course details from the system 
	 */
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
	
	
	/**
	 * This getStreamById method will get stream details from system
	 * @param id id of stream
	 * @return stream details from the system based on stream id 
	 */
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
	/**
	 * This createAGroupForCourse method will create a discussion group for a course
	 * @param courseId id of course
	 * @param professorId id of professor
	 * @return true if discussion group is created for a course
	 */
	public boolean createAGroupForCourse(int professorId, int courseId){
        Course c = getCourse(courseId);
		Stream s = getStreamById(c.getStreamID());
		CourseGroup isPresent = getCourseGroupByCourseId(courseId);
		List<User> getUsersForTheCourse = this.learnerService.getLearnersEnrolledList(courseId);
		boolean result = false;
		if(!(isPresent.getCourseGroupID()>0)){
			final String LC = "INSERT INTO courseGroup (userID, courseID, courseName, courseStream, flag) VALUES (?,?,?,?,?)";
			try (Connection conn = JDBCConnection.geConnection();
					PreparedStatement preparedStatement = conn.prepareStatement(LC, PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setInt(1, professorId);
				preparedStatement.setInt(2, courseId);
				preparedStatement.setString(3, c.getCourseName());
				preparedStatement.setString(4, s.getStreamName());
				preparedStatement.setInt(5, 0);
				int row = preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if(rs.next()){
					int courseGroupID = Integer.parseInt(rs.getLong(1)+"");
					for(User u:getUsersForTheCourse){
						final String LEARNERS_IN_GROUP = "INSERT INTO courseGroupMembers (userID, courseGroupID) VALUES (?,?)";
						PreparedStatement ps = conn.prepareStatement(LEARNERS_IN_GROUP, PreparedStatement.RETURN_GENERATED_KEYS);
						ps.setInt(1, u.getUserID());
						ps.setInt(2, courseGroupID);
						row = ps.executeUpdate();
					}
				}
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
	public List<CourseGroupMembers> getCourseGroupMembersFromCourse(int courseGroupID){
		final String GET_COURSE_GROUP = "SELECT * FROM courseGroupMembers where courseGroupID = ?";
		List<CourseGroupMembers> courseGroupMembers = new ArrayList<CourseGroupMembers>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSE_GROUP)) {
			preparedStatement.setInt(1, courseGroupID);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				CourseGroupMembers m = new CourseGroupMembers(result.getInt("courseGroupMembersID"), result.getInt("userID"), result.getInt("courseGroupID"));
				courseGroupMembers.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseGroupMembers;
	}
	@Override
	public CourseGroup getCourseGroupByCourseId(int courseId){
		final String GET_COURSE_GROUP = "SELECT * FROM courseGroup where courseID = ?";
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
	/**
	 * This getCourseGroupsForUser method will get details of a discussion group for a user
	 * @param userId id of user
	 * @return list of all group for a course for a student id
	 */
	public List<CourseGroup> getCourseGroupsForUser(int userId){
		final String GET_GROUPS_FOR_USER = "SELECT * FROM courseGroupMembers where userID = ?";
		List<CourseGroup> userGroups = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_GROUPS_FOR_USER)) {
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				final String GET_GROUP_INFO = "SELECT * FROM courseGroup where courseGroupID = ?";
				PreparedStatement ps = conn.prepareStatement(GET_GROUP_INFO);
				ps.setInt(1, result.getInt("courseGroupID"));
				ResultSet RS = ps.executeQuery();
				while(RS.next()){
					CourseGroup gc = new CourseGroup();
					gc.setCourseGroupID(RS.getInt("courseGroupID"));
					gc.setFlag(RS.getInt("flag"));
					gc.setCourseName(RS.getString("courseName"));
					gc.setCourseStream(RS.getString("courseStream"));
					gc.setUserID(RS.getInt("userID"));
					gc.setCourseID(RS.getInt("courseID"));
					userGroups.add(gc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroups;
	}
	@Override
	public List<CourseGroup> getCourseGroupByUserId(int userId){
		final String GET_COURSE_GROUP = "SELECT * FROM courseGroup where userID = ?";
		List<CourseGroup> userGroups = new ArrayList<>();
		try (Connection conn = JDBCConnection.geConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(GET_COURSE_GROUP)) {
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				CourseGroup gc = new CourseGroup();
				gc.setCourseGroupID(result.getInt("courseGroupID"));
				gc.setFlag(result.getInt("flag"));
				gc.setCourseName(result.getString("courseName"));
				gc.setCourseStream(result.getString("courseStream"));
				gc.setUserID(result.getInt("userID"));
				gc.setCourseID(result.getInt("courseID"));
				userGroups.add(gc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroups;
	}
	@Override
	/**
	 * This getCourseGroupChats method will get details of a discussion group chats 
	 * @param courseGroupID id of course group
	 * @return list of all group chats for a course for a course id
	 */
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
	/**
	 * This postMessageInGroup method will post messages in discussion group chats 
	 * @param chat chat details for a course group
	 * @return true if message is posted in group chat 
	 */
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
	@Override
	/**
	 * This addRemoveMemberCourseGroup method will add or remove members from discussion group for a given course
	 * @param courseId id of course
	 * @param userID id of user
	 * @param operation add or remove operation
	 * @return true if member is added or removed from course group 
	 */
	public boolean addRemoveMemberCourseGroup(int courseId, int userID, int operation){
		CourseGroup group = getCourseGroupByCourseId(courseId);
		if(operation==1){
			//ADD
			final String LEARNERS_IN_GROUP = "INSERT INTO courseGroupMembers (userID, courseGroupID) VALUES (?,?)";
			try (Connection conn = JDBCConnection.geConnection();
					PreparedStatement ps = conn.prepareStatement(LEARNERS_IN_GROUP, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, userID);
			ps.setInt(2, group.getCourseGroupID());
			ps.executeUpdate();
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(operation==2){
			//REMOVE
			final String REMOVE_MEMBER = "DELETE FROM courseGroupMembers WHERE userID = ? and courseGroupID = ?";
			try (Connection conn = JDBCConnection.geConnection();
					PreparedStatement ps = conn.prepareStatement(REMOVE_MEMBER, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, userID);
			ps.setInt(2, group.getCourseGroupID());
			ps.executeUpdate();
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}
}
