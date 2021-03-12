package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.beans.Stream;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;
import myPLS.services.StreamService;
import myPLS.services.StreamServiceImpl;
import spark.Request;
import spark.Response;
import spark.Spark;

public class CourseController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static CourseService courseService;
	private static StreamService streamService;

	public CourseController() {
		setConfiguration();
		courseService = new CourseServiceImpl();
		streamService = new StreamServiceImpl();
	}

	public StringWriter getCourses() {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		List<Course> courses = courseService.getCourses();
		try {
			resultTemplate = configuration.getTemplate("templates/adminDashboard.ftl");
			map.put("courses", courses);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}

	public StringWriter getAddCoursePage() {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<Stream> streams = streamService.getStreams();
		map.put("streams", streams);
		try {
			Template formTemplate = configuration.getTemplate("templates/course.ftl");
			formTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}

		return writer;
	}

	public void addCourse(Request request, Response response) {
		courseService.addCourse(request);
	}

	private void setConfiguration() {
		configuration.setClassForTemplateLoading(CourseController.class, "/");
	}
}
