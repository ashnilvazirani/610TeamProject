package myPLS.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myPLS.DAO.LearnerDAO;
import myPLS.beans.Course;
import myPLS.beans.Learner;
import myPLS.beans.User;
import spark.Request;

public class LearnerService {
    private LearnerDAO learnerDAO;

    public LearnerService() {
		this.learnerDAO = new LearnerDAO();
	}
	
	public Map<String, String> enrollCourse(Request request) {
		Learner learner = new Learner();
		Map<String, String> map = new HashMap<>();
		learner.setCourseID(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		learner.setStreamID(Integer.parseInt(request.queryParams("streamId") != null ? request.queryParams("streamId") : "unknown"));
		learner.setUserID(request.session().attribute("userID"));
		// todo check for prereq courses 
		if(learnerDAO.enrollCourse(learner)) {
			map.put("message", "Course enrolled successfully");
		} else {
			// put appropriate value in map
		}
		return map;
	}

	
	public List<Course> getEnrolledCourses(Request request) {
		int userId = request.session().attribute("userID");
		return learnerDAO.getEnrolledCourses(userId);
	}
	
	public List<Course> getUnEnrolledCourses(Request request) {
		int userId = request.session().attribute("userID");
		return learnerDAO.getUnEnrolledCourses(userId);
	}
	
	public List<User> getLearnersEnrolledList(Request request) {
		int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		return learnerDAO.getLearnersEnrolledList(courseId);
	}
	public List<User> getLearnersEnrolledList(int courseId) {
		return learnerDAO.getLearnersEnrolledList(courseId);
	}
	
    public boolean addLearnerForCourse(Request request){
        int courseId = Integer.parseInt(request.params("courseId") != null ? request.params("courseId") : "-1");
		int userId = Integer.parseInt(request.params("userId") != null ? request.params("userId") : "-1");
        System.out.println(courseId);
        System.out.println(userId);
        return learnerDAO.enrollLearnerForCourse(userId, courseId);
    }

}
