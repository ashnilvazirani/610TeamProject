package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
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
public class ProfessorFeedbackController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private FeedbackService feedbackService;
	
	public ProfessorFeedbackController() {
		setConfiguration();
		feedbackService = FeedbackFactory.feedback(RoleType.PROFESSOR);
	}

	 // method to call professorFeedback.ftl file 
	public StringWriter getProfessorFeedbackPage(Request request) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		Feedback feedback = this.getProfessorFeedback(request);
		map.put("feedback", feedback);
		try {
			Template formTemplate = configuration.getTemplate("templates/professorFeedback.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
		
	}
	
	public StringWriter addProfessorFeedback(Request request,Response response) {
		feedbackService.addFeedback(request);
		response.redirect("/studentDashboard");
		return null;
	}
	
	public Feedback getProfessorFeedback(Request request) {
		return (Feedback)feedbackService.getFeedback(request);
	}
	
	private void setConfiguration() {
		configuration.setClassForTemplateLoading(ProfessorFeedbackController.class, "/");
	}
}
