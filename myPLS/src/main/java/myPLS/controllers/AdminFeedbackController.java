package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Feedback;
import myPLS.beans.RoleType;
import myPLS.services.FeedbackFactory;
import myPLS.services.FeedbackService;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 *  The ProfessorFeedbackController class to implement prof feedback functionality
 * @author sandeep
 *
 */
public class AdminFeedbackController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private FeedbackService feedbackService;
	
	public AdminFeedbackController() {
		setConfiguration();
		feedbackService = FeedbackFactory.feedback(RoleType.ADMIN);
	}

	 // method to call professorFeedback.ftl file 
	public StringWriter getAdminFeedbackPage(Request request) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<Feedback> feedback = this.getProfessorFeedback(request);
		map.put("feedbacks", feedback);
		try {
			Template formTemplate = configuration.getTemplate("templates/adminViewFeedback.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
		
	}
	
	public List<Feedback> getProfessorFeedback(Request request) {
		return (List<Feedback>)feedbackService.getFeedback(request);
	}
	
	private void setConfiguration() {
		configuration.setClassForTemplateLoading(AdminFeedbackController.class, "/");
	}
}
