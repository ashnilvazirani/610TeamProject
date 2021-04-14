package myPLS.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myPLS.DAO.CourseDAO;
import myPLS.DAO.CourseDAOImpl;
import myPLS.DAO.PreReqCourseDAO;
import myPLS.DAO.PreReqCourseDAOImpl;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupChat;
import myPLS.beans.CourseGroupMembers;
import spark.Request;

public class CourseServiceImpl implements CourseService {

	private CourseDAO courseDao;
	private Map<Integer, List<Integer>> preReqs;
	private PreReqCourseDAO preReqCourseDao;
	private UserService userService;

	public CourseServiceImpl() {
		this.courseDao = new CourseDAOImpl();
		this.preReqs = new HashMap<Integer, List<Integer>>();
		this.preReqCourseDao = new PreReqCourseDAOImpl();
		this.userService = new UserService();
	}

	/**
	 * @param request This method adds preReq of a course
	 */
	@Override
	public boolean addCourse(Request request) {
		int courseId = Integer
				.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		int preReqCourseId = Integer
				.parseInt(request.queryParams("preReqId") != null ? request.queryParams("preReqId") : "unknown");
		this.getCourses();
		if(!detectCycle(courseId, preReqCourseId)) {
			System.out.println("added prereq");
			return preReqCourseDao.addCourse(courseId, preReqCourseId);
		}
		else {
			System.out.println("Cycle detected");
			return false;
		}
	}

	/**
	 * This method returns all the preReqs
	 */
	@Override
	public Object getCourses() {
		preReqs = preReqCourseDao.getCourses();
		return preReqs;
	}

	/**
	 * @param courseId This method delete preReq of a Course
	 */
	@Override
	public boolean deleteCourse(Request request) {
		int courseId = Integer
				.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		int preReqCourseId = Integer
				.parseInt(request.queryParams("preReqId") != null ? request.queryParams("preReqId") : "unknown");
		return preReqCourseDao.deleteCourse(courseId, preReqCourseId);
	}

	/**
	 * @param courseId This method get the courseDeatils and set the preReq of a
	 *                 Course
	 */
	@Override
	public Course getCourseByCourseId(Request request) {
		// todo
		int id = Integer
				.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		// preReqs.addAll(courseDao.getPreReqOfCourse(id));
		return courseDao.getCourse(id);
	}

	@Override
	public Course getCourseByCourseId(int courseId) {
		return courseDao.getCourse(courseId);
	}

	/**
	 * @param courseId This method get the prereq's of a particular course
	 */
	@Override
	public List<Course> getCoursesById(Request request) {
		Object i = request.attribute("courseId");
		
		return preReqCourseDao.getPreReqCoursesOfCourse(Integer.parseInt(i.toString()));
	}

	@Override
	public boolean createACourseGroup(Request request) {
		int courseId = Integer
				.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "-1");
		int professorId = Integer
				.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "-1");
		return courseDao.createAGroupForCourse(professorId, courseId);
	}

	@Override
	public CourseGroup getCourseGroupByCourseId(int courseId) {
		return this.courseDao.getCourseGroupByCourseId(courseId);
	}

	@Override
	public List<CourseGroupChat> getChatsForCourseGroup(int courseGroupID) {
		return this.courseDao.getCourseGroupChats(courseGroupID);
	}

	@Override
	public int addMessageToCourseGroup(Request request) {
		String messageContent = request.queryParams("messageContent") != null ? request.queryParams("messageContent")
				: "NONE";
		int userID = Integer.parseInt(request.queryParams("userID") != null ? request.queryParams("userID") : "-1");
		String userName = this.userService.getUserByID(userID).getName();
		int courseGroupID = Integer
				.parseInt(request.queryParams("courseGroupID") != null ? request.queryParams("courseGroupID") : "-1");
		CourseGroupChat chat = new CourseGroupChat(courseGroupID, userID, userName, messageContent);
		if (this.courseDao.postMessageInGroup(chat))
			return courseGroupID;
		else
			return -1;
	}

	@Override
	public List<CourseGroupMembers> getCourseGroupMemberFromCourseGroupId(int courseGroupId) {
		return this.courseDao.getCourseGroupMembersFromCourse(courseGroupId);
	}

	@Override
	public boolean addRemoveMemberCourseGroup(int courseId, int userId, int operation) {
		return this.courseDao.addRemoveMemberCourseGroup(courseId, userId, operation);
	}

	@Override
	public List<CourseGroup> getCourseGroupByUserId(int userId) {
		return this.courseDao.getCourseGroupByUserId(userId);
	}

	@Override
	public List<CourseGroup> getCourseGroupForUserByUserId(int userId) {
		return this.courseDao.getCourseGroupsForUser(userId);
	}

	@Override
	public boolean modifyCourse(int courseId, String operation){
		Course c = getCourseByCourseId(courseId);
		return this.courseDao.modifyCourse(c, operation);
	}


	private boolean detectCycle(int courseId, int preReqCourseId) {
		final Map<Integer, List<Integer>> adj = this.preReqs;
		if (adj.get(courseId) != null) {
			adj.get(courseId).add(preReqCourseId);
		} else {
			adj.put(courseId, new ArrayList<Integer>());
			adj.get(courseId).add(preReqCourseId);
		}
		Map<Integer, Boolean> visited = new HashMap<>();
		adj.forEach((k, v) -> {
			visited.put(k, false);
			v.forEach(value -> {
				visited.put(value, false);

			});
		});
		Map<Integer, Boolean> recStack = new HashMap<>();
		adj.forEach((k, v) -> {
			recStack.put(k, false);
			v.forEach(value -> {
				recStack.put(value, false);

			});
		});
		for (Map.Entry value: adj.entrySet())
            if (isCyclicUtil(Integer.parseInt(value.getKey().toString()), visited, recStack,adj))
                return true;

		return false;

	}

	private static boolean isCyclicUtil(Integer i, Map<Integer, Boolean> visited, Map<Integer, Boolean> recStack, Map<Integer, List<Integer>> adj) {
		if (recStack.get(i))
			return true;

		if (visited.get(i))
			return false;

		visited.put(i, true);

		recStack.put(i, true);
		List<Integer> children = adj.get(i);
		if(children != null) {
			for (Integer c : children)
				if (isCyclicUtil(c, visited, recStack,adj))
					return true;
		}
		recStack.put(i, false);
		return false;
	}

}
