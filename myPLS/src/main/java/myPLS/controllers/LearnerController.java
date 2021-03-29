package myPLS.controllers;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.beans.CourseGroupMembers;
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
		List<Course> courses = learnerService.getEnrolledCourses(request);
		System.out.println("USER ID: "+request.session().attribute("userID"));
		List<CourseGroup> myCourseGroups = this.courseService.getCourseGroupForUserByUserId(request.session().attribute("userID"));
		System.out.println("MY H+GRP:"+myCourseGroups.size());
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/studentDashboard.ftl");
			map.put("courses", courses);
			map.put("userID", request.session().attribute("userID"));
			map.put("myCourseGroups", myCourseGroups);
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
		int courseId= Integer.parseInt(request.queryParams("courseId") != null ? request.queryParams("courseId") : "-1");
		CourseGroup group =  this.courseService.getCourseGroupByCourseId(courseId);
		List<CourseGroupMembers> groupMembers = this.courseService.getCourseGroupMemberFromCourseGroupId(group.getCourseGroupID());
		try {
			resultTemplate = configuration.getTemplate("templates/enrolledLearners.ftl");
			map.put("users", users);
			map.put("courseId", courseId);
			map.put("groupMembers", groupMembers);
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
