package myPLS.services;

import java.util.List;

import myPLS.DAO.CourseDAO;
import myPLS.DAO.CourseDAOImpl;
import myPLS.DAO.LearnerDAO;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupChat;
import myPLS.beans.CourseGroupMembers;
import spark.Request;

public class CourseComponentServiceImpl implements CourseService {
    private CourseDAO courseDao;
	private UserService userService;
	private LearnerDAO learnerDAO;
    public CourseComponentServiceImpl() {
		this.courseDao = new CourseDAOImpl();
		this.userService = new UserService();
		this.learnerDAO = new LearnerDAO();
	}
	@Override
	public List<CourseGroupMembers> getCourseGroupMemberFromCourseGroupId(int courseGroupId){
		return this.courseDao.getCourseGroupMembersFromCourse(courseGroupId);
	}
	@Override
	public boolean addCourse(Request request) {
		Course course = new Course();
		course.setCourseName(request.queryParams("courseName") != null ? request.queryParams("courseName") : "unknown");
		course.setCourseDescription(request.queryParams("courseDescription") != null ? request.queryParams("courseDescription") : "unknown");
		course.setCourseDuration(Integer.parseInt(request.queryParams("courseDuration") != null ? request.queryParams("courseDuration") : "unknown"));
		course.setProfessorId(Integer.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "unknown"));
		int streamId = Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown");
		course.setStreamId(streamId);
		
		return courseDao.addCourse(course);
	}

	@Override
	public boolean updateCourse(Request request) {
		Course course = new Course();
		course.setCourseName(request.queryParams("courseName") != null ? request.queryParams("courseName") : "unknown");
		course.setCourseDescription(request.queryParams("courseDescription") != null ? request.queryParams("courseDescription") : "unknown");
		course.setCourseDuration(Integer.parseInt(request.queryParams("courseDuration") != null ? request.queryParams("courseDuration") : "unknown"));
		course.setStreamId(Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown"));
		course.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		return courseDao.updateCourse(course);
	}

	@Override
	public Object getCourses() {
		return courseDao.getCourses();
	}

	@Override
	public boolean deleteCourse(Request request) {
		return courseDao.deleteCourse(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
	}

	@Override
	public Course getCourseByCourseId(Request request) {
		return courseDao.getCourse(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
	}

	@Override
	public Course getCourseByCourseId(int courseId) {
		return courseDao.getCourse(courseId);
	}
	@Override
	public List<Course> getCoursesById(Request request) {
		int id = request.session().attribute("userID");
		return courseDao.getCourseById(id);
	}

	@Override
	public boolean createACourseGroup(Request request) {
		int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "-1");
		int professorId = Integer.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "-1");
		return courseDao.createAGroupForCourse(professorId, courseId);
	}

	@Override
	public CourseGroup getCourseGroupByCourseId(int courseId){
		return this.courseDao.getCourseGroupByCourseId(courseId);
	}
	@Override
	public List<CourseGroupChat> getChatsForCourseGroup(int courseGroupID){
		return this.courseDao.getCourseGroupChats(courseGroupID);
	}
	
	@Override
	public int addMessageToCourseGroup(Request request){
		String messageContent = request.queryParams("messageContent") != null ? request.queryParams("messageContent") : "NONE";
		int userID = Integer.parseInt(request.queryParams("userID") != null ? request.queryParams("userID") : "-1");
		String userName = this.userService.getUserByID(userID).getName();
		int courseGroupID = Integer.parseInt(request.queryParams("courseGroupID") != null ? request.queryParams("courseGroupID") : "-1");
		CourseGroupChat chat = new CourseGroupChat(courseGroupID, userID, userName, messageContent);
		if(this.courseDao.postMessageInGroup(chat))
			return courseGroupID;
		else
			return -1;
	}
	@Override
	public boolean addRemoveMemberCourseGroup(int courseId, int userId, int operation){
		return this.courseDao.addRemoveMemberCourseGroup(courseId, userId, operation);
	}
	@Override
	public List<CourseGroup> getCourseGroupByUserId(int userId){
		return this.courseDao.getCourseGroupByUserId(userId);
	}
	@Override
	public List<CourseGroup> getCourseGroupForUserByUserId(int userId){
		return this.courseDao.getCourseGroupsForUser(userId);
	}
	@Override
	public boolean modifyCourse(int courseId, String operation){
		Course c = getCourseByCourseId(courseId);
		return this.courseDao.modifyCourse(c, operation);
	}
	@Override
	public boolean leaveCourseForStudent(int courseId,int userId){
		return this.learnerDAO.leaveCourseForStudent(courseId, userId);
	}
}
