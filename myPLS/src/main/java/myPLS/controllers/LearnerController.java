package myPLS.controllers;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.CourseService;
import myPLS.services.LearnerService;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LearnerController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private LearnerService learnerService;
	private CourseService courseService;
    public LearnerController(){
        this.setConfiguration();
        this.learnerService = new LearnerService(); 
		this.courseService = new CourseComponentServiceImpl();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(LearnerController.class, "/");
	}
    
    public StringWriter getLearnerDashboard() {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	public StringWriter enrollLearnerForCourse(Request request, Response response) {
		if(learnerService.addLearnerForCourse(request)){
			response.redirect("/enrollForCourses");
		}else{
			System.out.println("ERROR");
		}
		return null;
	}
	public StringWriter getCourseListForLearners(Request request, Response response) {
		StringWriter writer = new StringWriter();
		List<Course> courses = courseService.getCourses();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courses", courses);
		map.put("userId", request.session().attribute("userID").toString());
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewCoursesForStudentToEnroll.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
}
