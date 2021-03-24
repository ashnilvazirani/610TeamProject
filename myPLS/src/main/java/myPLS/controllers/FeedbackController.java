package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.services.FeedbackService;
import spark.Request;
import spark.Response;
import spark.Spark;

public class FeedbackController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private FeedbackService feedbackService;
	
	public FeedbackController() {
		setConfiguration();
		feedbackService = new FeedbackService();
	}

	
	public StringWriter getStudentFeedbackPage(Request request) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		int learnerId = Integer.parseInt(request.queryParams("learnerId") != null ? request.queryParams("learnerId") : "unknown");
		int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
		map.put("learnerId", learnerId);
		map.put("courseId", courseId);
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
	
	
	private void setConfiguration() {
		configuration.setClassForTemplateLoading(CourseController.class, "/");
	}
}
