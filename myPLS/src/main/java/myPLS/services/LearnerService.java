package myPLS.services;

import java.util.Map;

import myPLS.DAO.LearnerDAO;
import myPLS.beans.Learner;
import spark.Request;

public class LearnerService {
    private LearnerDAO learnerDAO;

    public LearnerService() {
		this.learnerDAO = new LearnerDAO();
	}
    public Map<Integer, Learner> getUserDetails(){
        return this.learnerDAO.getAllLearners();
    }
	public boolean addLearnerDetails(Request request) {
		//Connect with UI
        return true;
	}
    public boolean addLearnerForCourse(Request request){
        int courseId = Integer.parseInt(request.params("courseId") != null ? request.params("courseId") : "-1");
		int userId = Integer.parseInt(request.params("userId") != null ? request.params("userId") : "-1");
        System.out.println(courseId);
        System.out.println(userId);
        return learnerDAO.enrollLearnerForCourse(userId, courseId);
    }
}
