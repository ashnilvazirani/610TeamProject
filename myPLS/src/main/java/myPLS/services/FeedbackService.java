package myPLS.services;

import java.util.Date;

import myPLS.DAO.FeedbackDAO;
import myPLS.beans.Feedback;
import spark.Request;

public class FeedbackService {
	
	private FeedbackDAO feedbackDAO;
	
	public FeedbackService() {
		feedbackDAO = new FeedbackDAO();
		
	}
	
	public boolean addStudentFeedback(Request request) {
		Feedback feedback = new Feedback();
		feedback.setFeedbackEntityId(Integer.parseInt(request.queryParams("learnerId") != null ? request.queryParams("learnerId") : "unknown"));
		int professorId = request.session().attribute("userID");
		feedback.setFeedbackGiverId(professorId);
		feedback.setRating(Double.parseDouble(request.queryParams("rating") != null ? request.queryParams("rating") : "unknown"));
		feedback.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));

		feedback.setComments((request.queryParams("comment") != null ? request.queryParams("comment") : "unknown").toString());
		feedback.setDate(new Date());
		return feedbackDAO.addStudentFeedback(feedback);
	}

}
