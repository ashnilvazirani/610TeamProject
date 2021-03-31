package myPLS.controllers;
import java.io.StringWriter;
import java.security.DrbgParameters.Reseed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import myPLS.beans.Course;
import myPLS.beans.CourseGroup;
import myPLS.services.CourseComponentServiceImpl;
import myPLS.services.CourseService;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ProfessorController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private CourseService courseService;
    public ProfessorController(){
        this.setConfiguration();
        this.courseService = new CourseComponentServiceImpl();
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
		List<Course> courses =  courseService.getCourseByProfessorId(request);
		ArrayList<CourseGroup> courseGroups =new ArrayList<>();
		for(Course c:courses){
			CourseGroup gc = courseService.getCourseGroupByCourseId(c.getCourseId());
			if(gc.getCourseName()!=null)
				courseGroups.add(gc);
		}
		map.put("courses", courses);
		map.put("courseGroups", courseGroups);
		map.put("userId", request.session().attribute("userID"));
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
