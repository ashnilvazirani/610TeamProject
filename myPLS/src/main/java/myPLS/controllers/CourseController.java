package myPLS.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.beans.CourseGroupChat;
import myPLS.beans.Stream;
import myPLS.beans.User;
import myPLS.services.CourseService;
import myPLS.services.RegistrationService;
import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.StreamService;
import myPLS.services.StreamServiceImpl;
import spark.Request;
import spark.Response;
import spark.Spark;

public class CourseController {
	private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static CourseService courseService;
	private static StreamService streamService;
	private static RegistrationService registrationService;

	public CourseController() {
		setConfiguration();
		courseService = new CourseComponentServiceImpl();
		streamService = new StreamServiceImpl();
		registrationService = new RegistrationService(); 
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

	public StringWriter getCourseChat(Request request, Response response) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		int courseGroupID=-1;
		if(request.queryParams("courseGroupID") != null){
			courseGroupID = Integer.parseInt(request.queryParams("courseGroupID"));
		}else{
			courseGroupID = Integer.parseInt(request.params("courseGroupID"));
		}
		// int courseGroupID = Integer.parseInt(request.queryParams("courseGroupID") != null ? request.queryParams("courseGroupID") : "-1");
		List<CourseGroupChat> groupChats = courseService.getChatsForCourseGroup(courseGroupID);
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/viewCourseGroupChats.ftl");
			map.put("groupChats", groupChats);
			map.put("userID", request.session().attribute("userID"));
			map.put("courseGroupID", courseGroupID);
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
	public boolean postGroupMessage(Request request, Response response){
		int courseGroupID = courseService.addMessageToCourseGroup(request);
		if(courseGroupID!=-1){
			response.redirect("/courseGroupChat/"+courseGroupID);
			return true;
		}
		return false;
		
	}
	public boolean addRemoveMemberCourseGroup(Request request, Response response){
		int operation = -1;
		int courseId=Integer.parseInt(request.queryParams("courseId"));
		int userID=Integer.parseInt(request.queryParams("userID"));
		if(request.queryParams("addMember")!=null){
			operation=1;
		}else if(request.queryParams("removeMember")!=null){
			operation=2;
		}

		if(operation!=-1)
			if(courseService.addRemoveMemberCourseGroup(courseId, userID, operation))
				response.redirect("/enrolledLearners?courseId="+courseId);
		return true;
	}
	public void modifyCourse(Request request,Response response){
		int courseId = Integer.parseInt(request.queryParams("courseId"));
		String operation="";
		if(request.queryParams("deleteCourse")!=null){
			operation="DELETE";
		}else if(request.queryParams("updateCourse")!=null){
			operation="UPDATE";
			this.getAddCoursePage(operation, courseId);
		}
		if(this.courseService.modifyCourse(courseId, operation)){
			response.redirect("/courses");
		}else{
			System.out.println("ERROR");
		}
	}
	public StringWriter getAddCoursePage(String operation, int courseId) {
		StringWriter writer = new StringWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<Stream> streams = streamService.getStreams();
		List<User> professors = registrationService.getUsersByRole("professor");
		if(operation.equalsIgnoreCase("UPDATE") && courseId>0){
			Course c = this.courseService.getCourseByCourseId(courseId);
			map.put("courseToUpdate", c);
		}
		map.put("streams", streams);
		map.put("professors", professors);
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
