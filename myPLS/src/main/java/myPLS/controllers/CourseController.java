package myPLS.controllers;
import freemarker.template.Configuration;
import myPLS.services.CourseService;
import freemarker.template.Version;

public class CourseController {
    private final Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static CourseService courseService;
    CourseController(){
        setConfiguration();
        courseService = new CourseService();
    }
    private void setConfiguration() {
        configuration.setClassForTemplateLoading(CourseController.class, "/");
	}
}
