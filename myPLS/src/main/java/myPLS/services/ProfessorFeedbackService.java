package myPLS.services;

import java.util.Date;

import myPLS.DAO.ProfessorFeedbackDAO;
import myPLS.beans.Feedback;
import spark.Request;

public class ProfessorFeedbackService extends FeedbackService{
	
	private ProfessorFeedbackDAO professorFeedbackDAO;
	
	public ProfessorFeedbackService() {
		professorFeedbackDAO = new ProfessorFeedbackDAO();
		
	}
	
	@Override
	public boolean addFeedback(Request request) {
		Feedback feedback = new Feedback();
		feedback.setFeedbackEntityId(Integer.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "unknown"));
		int learnerId = request.session().attribute("userID");
		feedback.setFeedbackGiverId(learnerId);
		feedback.setRating(Double.parseDouble(request.queryParams("rating") != null ? request.queryParams("rating") : "unknown"));
		feedback.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		feedback.setComments((request.queryParams("comment") != null ? request.queryParams("comment") : "unknown").toString());
		feedback.setDate(new Date());
		if(professorFeedbackDAO.getProfessorFeedback(feedback) != null) {
			return professorFeedbackDAO.updateProfessorFeedback(feedback);
		} else {
			return professorFeedbackDAO.addProfessorFeedback(feedback);
		}
	}
	

	@Override
	public Object getFeedback(Request request) {
		Feedback feedback = new Feedback();
		feedback.setFeedbackEntityId(Integer.parseInt(request.queryParams("professorId") != null ? request.queryParams("professorId") : "unknown"));
		feedback.setFeedbackGiverId(request.session().attribute("userID"));
		feedback.setCourseId(Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown"));
		Feedback reponse = professorFeedbackDAO.getProfessorFeedback(feedback);
		if(reponse != null) {
			return reponse;
		} else {
			return feedback;
		}
	}
}
