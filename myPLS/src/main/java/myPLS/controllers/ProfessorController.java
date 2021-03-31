package myPLS.controllers;
import java.io.StringWriter;
import java.security.DrbgParameters.Reseed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.CourseService;
import myPLS.services.CourseServiceImpl;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ProfessorController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private CourseService courseService;
    private CourseService preReqService;
    public ProfessorController(){
        this.setConfiguration();
        this.courseService = new CourseComponentServiceImpl();
        this.preReqService = new CourseServiceImpl();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(ProfessorController.class, "/");
	}
    public boolean createACourseGroup(Request request, Response response){
		return this.courseService.createACourseGroup(request);
	}
    public StringWriter getProfessorDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses =  courseService.getCoursesById(request);
		ArrayList<CourseGroup> courseGroups =new ArrayList<>();
		Map<Integer, List<Course>> prereqCourses = new HashMap<Integer, List<Course>>();
		for(Course c:courses){
			CourseGroup gc = courseService.getCourseGroupByCourseId(c.getCourseId());
			if(gc.getCourseName()!=null)
				courseGroups.add(gc);
			prereqCourses.put(c.getCourseId(), new ArrayList<Course>());
			request.attribute("courseId", c.getCourseId());
			List<Course> preReqs = preReqService.getCoursesById(request);
			Iterator<Course> itr = preReqs.iterator();
			while(itr.hasNext()) {
				prereqCourses.get(c.getCourseId()).add(itr.next());
			}
		}
		map.put("courses", courses);
		map.put("courseGroups", courseGroups);
		map.put("userId", request.session().attribute("userID"));
		map.put("preReqs", prereqCourses);
		Template resultTemplate;
		try {
			resultTemplate = configuration.getTemplate("templates/professorDashboard.ftl");
			resultTemplate.process(map, writer);
		} catch (Exception e) {
			Spark.halt(500);
		}
		return writer;
	}
}
