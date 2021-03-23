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
import spark.Request;
import spark.Spark;

public class ProfessorController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
    private CourseService courseService;
    public ProfessorController(){
        this.setConfiguration();
        courseService = new CourseComponentServiceImpl();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(ProfessorController.class, "/");
	}
    
    public StringWriter getProfessorDashboard(Request request) {
		StringWriter writer = new StringWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> courses =  courseService.getCourseByProfessorId(request);
		map.put("courses", courses);
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
