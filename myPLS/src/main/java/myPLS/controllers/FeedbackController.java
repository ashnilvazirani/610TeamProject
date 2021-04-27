package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Feedback;
import myPLS.services.FeedbackService;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * The FeedbackController class to implement feedback functionality
 * @author ashnil
 *
 */
public class FeedbackController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private FeedbackService feedbackService;
	
	public FeedbackController() {
		setConfiguration();
		feedbackService = new FeedbackService();
	}

	// method to call studentFeedback.ftl file 
	public StringWriter getStudentFeedbackPage(Request request) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		Feedback feedback = this.getStudentFeedback(request);
		map.put("feedback", feedback);
		try {
			Template formTemplate = configuration.getTemplate("templates/studentFeedback.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
		
	}
	
	public StringWriter addStudentFeedback(Request request,Response response) {
		feedbackService.addStudentFeedback(request);
		response.redirect("/professorDashboard");
		return null;
	}
	
	public Feedback getStudentFeedback(Request request) {
		return feedbackService.getStudentFeedback(request);
	}
	
	private void setConfiguration() {
		configuration.setClassForTemplateLoading(CourseController.class, "/");
	}
}
