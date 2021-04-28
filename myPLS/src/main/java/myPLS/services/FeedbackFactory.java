package myPLS.services;

import myPLS.beans.RoleType;

public class FeedbackFactory {
	
	private FeedbackFactory() {}
	
	public static FeedbackService feedback(RoleType role) {
		FeedbackService feedbackService = null;
		switch (role) {
		case STUDENT:
			feedbackService = new LearnerFeedbackService();
			break;

		case PROFESSOR:
			feedbackService = new ProfessorFeedbackService();
			break;
		case ADMIN:
			feedbackService = new AdminFeedbackService();
			break;
		}
		
		return feedbackService;
	}
	

}
