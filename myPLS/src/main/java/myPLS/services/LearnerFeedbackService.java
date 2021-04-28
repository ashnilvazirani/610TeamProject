package myPLS.services;

import java.util.Date;

import myPLS.DAO.LearnerFeedbackDAO;
import myPLS.beans.Feedback;
import spark.Request;

public class LearnerFeedbackService extends FeedbackService {
	
	private LearnerFeedbackDAO feedbackDAO;
	
	public LearnerFeedbackService() {
		feedbackDAO = new LearnerFeedbackDAO();
		
	}
	@Override
	public boolean addFeedback(Request request) {
		Feedback feedback = new Feedback();
		feedback.setFeedbackEntityId(Integer.parseInt(request.queryParams("learnerId") != null ? request.queryParams("learnerId") : "unknown"));
		int professorId = request.session().attribute("userID");
		feedback.setFeedbackGiverId(professorId);
		feedback.setRating(Double.parseDouble(request.queryParams("rating") != null ? request.queryParams("rating") : "unknown"));
		feedback.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		feedback.setComments((request.queryParams("comment") != null ? request.queryParams("comment") : "unknown").toString());
		feedback.setDate(new Date());
		if(feedbackDAO.getStudentFeedback(feedback) != null) {
			return feedbackDAO.updateStudentFeedback(feedback);
		} else {
			return feedbackDAO.addStudentFeedback(feedback);
		}
	}
	
	@Override
	public Object getFeedback(Request request) {
		Feedback feedback = new Feedback();
		feedback.setFeedbackEntityId(Integer.parseInt(request.queryParams("learnerId") != null ? request.queryParams("learnerId") : "unknown"));
		int professorId = request.session().attribute("userID");
		feedback.setFeedbackGiverId(professorId);
		feedback.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		Feedback reponse = feedbackDAO.getStudentFeedback(feedback);
		if(reponse != null) {
			return reponse;
		} else {
			return feedback;
		}
	}
}
