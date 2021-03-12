package myPLS.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import myPLS.controllers.CourseController;
import myPLS.controllers.LearnerController;
import myPLS.controllers.RegistrationController;

public class mainApp {
	
	private final static RegistrationController registraionController = new RegistrationController();
	private final static CourseController courseController = new CourseController();
	private final static LearnerController learnerController = new LearnerController();

	public static int fileCount=0;
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		get("/registerUser", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
		
		post("/registerUser", (request, response) -> {
           return registraionController.registerUser(request);
        });
		
		post("/mailVerified",(request,response) -> {
			return registraionController.authoriseUser(request);
		});
		
		post("/resetPassword",(request,response) -> {
			registraionController.resetPassword(request, response);
			return 0;
		});
		
		get("/loginPage", (request, response) -> {
            return registraionController.getLoginPage();
        });
        get("/index", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        get("/", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        post("/logIn",(request,response) -> {
            return registraionController.logIn(request, response);
        });

        get("/addCourse", (request, response) -> {
        	return courseController.getAddCoursePage();
        });
        
        get("/courses",(request,response) -> {
        	return courseController.getCourses();
        });
        
        get("/studentDashboard",(request,response) -> {
        	return learnerController.getLearnerDashboard();
        });
        
        post("/addCourse", (request, response) -> {
        	courseController.addCourse(request,response);
    		response.redirect("/courses");
    		return 0;
        });
	}
}
