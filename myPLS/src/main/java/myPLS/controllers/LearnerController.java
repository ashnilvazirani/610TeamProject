package myPLS.controllers;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;

import myPLS.beans.User;

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
    
    public StringWriter getLearnerDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
			map.put("courses", learnerService.getEnrolledCourses(request));
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    
    public StringWriter getAllCourses(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
		List<Course> courses = learnerService.getUnEnrolledCourses(request);
		try {
			resultTemplate = configuration.getTemplate("templates/enrollCourses.ftl");
			map.put("courses", courses);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
    
    public StringWriter enrollCourse(Request request, Response response) {
    	StringWriter writer = new StringWriter();
		Template resultTemplate;
		Map<String,String> map = learnerService.enrollCourse(request);
		response.redirect("/studentDashboard");
//		try {
//			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
//			resultTemplate.process(map, writer);
//		} catch (Exception e) {
//			Spark.halt(500);
//		}
		return writer;
    }
    
    public StringWriter getLearnersEnrolledList(Request request) {
    	StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		Template resultTemplate;
    	List<User> users= learnerService.getLearnersEnrolledList(request);
		try {
			resultTemplate = configuration.getTemplate("templates/enrolledLearners.ftl");
			map.put("users", users);
			int courseId = Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "unknown");
			map.put("courseId", courseId);
			resultTemplate.process(map, writer);

		}catch(Exception e){
			e.printStackTrace();
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
