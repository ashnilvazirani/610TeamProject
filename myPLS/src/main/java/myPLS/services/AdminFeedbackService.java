package myPLS.services;

import myPLS.DAO.AdminFeedbackDAO;
import spark.Request;

public class AdminFeedbackService  extends FeedbackService {
	private AdminFeedbackDAO adminFeedbackDAO;
	
	public AdminFeedbackService() {
		adminFeedbackDAO = new AdminFeedbackDAO();
	}

	@Override
	public Object getFeedback(Request request) {
		return adminFeedbackDAO.getFeedbacks();
	}

}
